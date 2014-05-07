package com.example.awesomeplaylists.UI;

import java.util.ArrayList;

import com.example.awesomeplaylists.Services.MusicService;
import com.example.awesomeplaylists.Services.Song;
import com.example.awesomeplaylists.Services.MusicService.MusicBinder;

import com.example.awesomeplaylists.R;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.MediaController.MediaPlayerControl;
import android.widget.TextView;
import android.os.IBinder;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;

public class PlayItActivity extends Activity implements MediaPlayerControl
{

	private MusicController controller;
	private MusicService musicSrv;
	private Intent playIntent;
	private boolean musicBound = false;
	private ArrayList<Song> songList;
	private boolean paused = false, playbackPaused = false;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_it);

		setController();

		// ////////////////////////////////////////////////////////////////////////////
		// Temporary code
		ContentResolver musicResolver = getContentResolver();
		Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
		Cursor musicCursor = musicResolver.query(musicUri, null, null, null,
				null);
		songList = new ArrayList<Song>();
		if (musicCursor != null && musicCursor.moveToFirst())
		{
			// get columns
			int titleColumn = musicCursor
					.getColumnIndex(android.provider.MediaStore.Audio.Media.TITLE);
			int idColumn = musicCursor
					.getColumnIndex(android.provider.MediaStore.Audio.Media._ID);
			int artistColumn = musicCursor
					.getColumnIndex(android.provider.MediaStore.Audio.Media.ARTIST);
			// add songs to list
			do
			{
				long thisId = musicCursor.getLong(idColumn);
				String thisTitle = musicCursor.getString(titleColumn);
				String thisArtist = musicCursor.getString(artistColumn);
				songList.add(new Song(thisId, thisTitle, thisArtist));
			} while (musicCursor.moveToNext());
		}
		// ////////////////////////////////////////////////////////////////////////////

		// Register to receive messages.
		// We are registering an observer (mMessageReceiver) to receive Intents
		// with actions named "custom-event-name".
		LocalBroadcastManager.getInstance(this).registerReceiver(
				mMessageReceiver, new IntentFilter("songChanged"));
	}

	// Our handler for received Intents. This will be called whenever an Intent
	// with an action named "custom-event-name" is broadcasted.
	private BroadcastReceiver mMessageReceiver = new BroadcastReceiver()
	{
		@Override
		public void onReceive(Context context, Intent intent)
		{
			updateLabels();
		}
	};

	public void updateLabels()
	{
		TextView artistNameView = (TextView) findViewById(R.id.artistText);
		artistNameView.setText(musicSrv.getBandTitle());

		TextView songNameView = (TextView) findViewById(R.id.songNameText);
		songNameView.setText(musicSrv.getSongTitle());
	}

	// connect to the service
	private ServiceConnection musicConnection = new ServiceConnection()
	{

		@Override
		public void onServiceConnected(ComponentName name, IBinder service)
		{
			MusicBinder binder = (MusicBinder) service;
			// get service
			musicSrv = binder.getService();
			// pass list
			musicSrv.setList(songList);
			musicBound = true;

			musicSrv.setSong(0);
			musicSrv.playSong();
		}

		@Override
		public void onServiceDisconnected(ComponentName name)
		{
			musicBound = false;
		}
	};

	@Override
	protected void onStart()
	{
		if (playIntent == null)
		{
			playIntent = new Intent(this, MusicService.class);
			bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
			startService(playIntent);
		}

		super.onStart();
	}

	@Override
	protected void onDestroy()
	{
		stopService(playIntent);
		musicSrv = null;

		// Unregister since the activity is about to be closed.
		LocalBroadcastManager.getInstance(this).unregisterReceiver(
				mMessageReceiver);

		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.first, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void setController()
	{
		controller = new MusicController(this);

		controller.setMediaPlayer(this);
		controller.setAnchorView(findViewById(R.id.playItLayout));
		controller.setEnabled(true);

		controller.setPrevNextListeners(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				playNext();
			}
		}, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				playPrev();
			}
		});
	}

	// play next
	private void playNext()
	{
		musicSrv.playNext();
		if (playbackPaused)
		{
			setController();
			playbackPaused = false;
		}
		controller.show(0);
	}

	// play previous
	private void playPrev()
	{
		musicSrv.playPrev();
		if (playbackPaused)
		{
			setController();
			playbackPaused = false;
		}
		controller.show(0);
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		paused = true;
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		if (paused)
		{
			setController();
			paused = false;
		}
	}

	@Override
	protected void onStop()
	{
		controller.hide();
		super.onStop();
	}

	@Override
	public void start()
	{
		musicSrv.go();
	}

	@Override
	public void pause()
	{
		playbackPaused = true;
		musicSrv.pausePlayer();
	}

	@Override
	public int getDuration()
	{
		if (musicSrv != null && musicBound && musicSrv.isPng())
			return musicSrv.getDur();
		else
			return 0;
	}

	@Override
	public int getCurrentPosition()
	{
		if (musicSrv != null && musicBound && musicSrv.isPng())
			return musicSrv.getPosn();
		else
			return 0;
	}

	@Override
	public void seekTo(int pos)
	{
		musicSrv.seek(pos);
	}

	@Override
	public boolean isPlaying()
	{
		if (musicSrv != null && musicBound)
			return musicSrv.isPng();
		return false;
	}

	@Override
	public int getBufferPercentage()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean canPause()
	{
		return true;
	}

	@Override
	public boolean canSeekBackward()
	{
		return true;
	}

	@Override
	public boolean canSeekForward()
	{
		return true;
	}

	@Override
	public int getAudioSessionId()
	{		
		// TODO Auto-generated method stub
		return 0;
	}
}
