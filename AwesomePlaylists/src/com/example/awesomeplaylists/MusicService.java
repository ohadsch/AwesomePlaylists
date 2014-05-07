package com.example.awesomeplaylists;

import java.util.ArrayList;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.content.ContentUris;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.PowerManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import java.util.Random;
import android.app.Notification;
import android.app.PendingIntent;

public class MusicService extends Service implements
		MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener,
		MediaPlayer.OnCompletionListener
{

	// media player
	private MediaPlayer player;
	// song list
	private ArrayList<Song> songs;
	// current position
	private int songPosn;

	private String songTitle = "";
	private String bandTitle = "";
	private static final int NOTIFY_ID = 1;

	private final IBinder musicBind = new MusicBinder();

	public MusicService()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate()
	{
		// initialize position
		songPosn = 0;
		// create player
		player = new MediaPlayer();

		initMusicPlayer();

		super.onCreate();
	}

	@Override
	public void onDestroy()
	{
		stopForeground(true);

		super.onDestroy();
	}

	private void initMusicPlayer()
	{
		// set player properties
		player.setWakeMode(getApplicationContext(),
				PowerManager.PARTIAL_WAKE_LOCK);
		player.setAudioStreamType(AudioManager.STREAM_MUSIC);

		player.setOnPreparedListener(this);
		player.setOnCompletionListener(this);
		player.setOnErrorListener(this);
	}

	public void setList(ArrayList<Song> theSongs)
	{
		songs = theSongs;
	}

	@Override
	public IBinder onBind(Intent intent)
	{
		return musicBind;
	}

	@Override
	public boolean onUnbind(Intent intent)
	{
		player.stop();
		player.release();

		return super.onUnbind(intent);
	}

	@Override
	public void onCompletion(MediaPlayer mp)
	{
		if (player.getCurrentPosition() > 0)
		{
			mp.reset();
			playNext();
		}
	}

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra)
	{
		mp.reset();

		return false;
	}

	@Override
	public void onPrepared(MediaPlayer mp)
	{
		mp.start();

		Intent notIntent = new Intent(this, PlayItActivity.class);
		notIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		PendingIntent pendInt = PendingIntent.getActivity(this, 0, notIntent,
				PendingIntent.FLAG_UPDATE_CURRENT);

		Notification.Builder builder = new Notification.Builder(this);

		builder.setContentIntent(pendInt).setSmallIcon(R.drawable.play)
				.setTicker(songTitle).setOngoing(true)
				.setContentTitle("Playing").setContentText(songTitle);
		Notification not = builder.build();

		startForeground(NOTIFY_ID, not);
	}

	public void playSong()
	{
		player.reset();

		// get song
		Song playSong = songs.get(songPosn);

		songTitle = playSong.getTitle();
		bandTitle = playSong.getArtist();

		Intent intent = new Intent("songChanged");
		// You can also include some extra data.
		LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

		// get id
		long currSong = playSong.getID();
		// set uri
		Uri trackUri = ContentUris.withAppendedId(
				android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
				currSong);

		try
		{
			player.setDataSource(getApplicationContext(), trackUri);
		} catch (Exception e)
		{
			Log.e("MUSIC SERVICE", "Error setting data source", e);
		}

		player.prepareAsync();
	}

	public void setSong(int songIndex)
	{
		songPosn = songIndex;
	}

	public class MusicBinder extends Binder
	{
		MusicService getService()
		{
			return MusicService.this;
		}
	}

	// skip to next
	public void playNext()
	{
		songPosn++;
		if (songPosn >= songs.size())
			songPosn = 0;
		playSong();
	}

	public void playPrev()
	{
		songPosn--;
		if (songPosn < 0)
			songPosn = songs.size() - 1;
		playSong();
	}

	public int getPosn()
	{
		return player.getCurrentPosition();
	}

	public int getDur()
	{
		return player.getDuration();
	}

	public boolean isPng()
	{
		return player.isPlaying();
	}

	public void pausePlayer()
	{
		player.pause();
	}

	public void seek(int posn)
	{
		player.seekTo(posn);
	}

	public void go()
	{
		player.start();
	}

	public String getBandTitle()
	{
		return bandTitle;
	}

	public String getSongTitle()
	{
		return songTitle;
	}
}
