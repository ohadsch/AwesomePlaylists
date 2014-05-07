package com.example.awesomeplaylists.Services;

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
import com.example.awesomeplaylists.BL.GenericSong;

import com.example.awesomeplaylists.R;
import com.example.awesomeplaylists.UI.PlayItActivity;

import android.app.Notification;
import android.app.PendingIntent;

public class MusicService extends Service implements
		MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener,
		MediaPlayer.OnCompletionListener
{

	private MediaPlayer player;
	private ArrayList<GenericSong> songs;
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
		// create player
		player = new MediaPlayer();

		songPosn = 0;
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

	public void setList(ArrayList<GenericSong> theSongs)
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

		// Show service data on notification bar and enable return to activity
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
		
		if (songs.size() == 0)
			return;

		// get song from list
		GenericSong playSong = songs.get(songPosn);

		songTitle = playSong.songTitle;
		bandTitle = playSong.songArtist;

		sendSongChangedAlert();

		// get id
		long currSong = playSong.ID;
		// set URI
		Uri trackUri = ContentUris.withAppendedId(
				android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
				currSong);

		// Start the player
		try
		{
			player.setDataSource(getApplicationContext(), trackUri);
		} catch (Exception e)
		{
			Log.e("MUSIC SERVICE", "Error setting data source", e);
		}
		player.prepareAsync();
	}
	
	private void sendSongChangedAlert()
	{
		Intent intent = new Intent("songChanged");
		LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
	}

	public void setSong(int songIndex)
	{
		songPosn = songIndex;
	}

	public class MusicBinder extends Binder
	{
		public MusicService getService()
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
