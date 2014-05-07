package com.example.awesomeplaylists.Activities;

import com.example.awesomeplaylists.R;
import com.example.awesomeplaylists.BL.MusicController;
import com.example.awesomeplaylists.R.id;
import com.example.awesomeplaylists.R.layout;
import com.example.awesomeplaylists.R.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController.MediaPlayerControl;
import android.widget.TextView;

public class PlayItActivity extends Activity implements MediaPlayerControl {

	private MusicController controller;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_it);
		
		setController();
			 	
		TextView artistNameView = (TextView) findViewById(R.id.artistText);
		artistNameView.setText("Metallica");
		
		TextView songNameView = (TextView) findViewById(R.id.songNameText);
		songNameView.setText("Nothing Else Matters");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present. 
		getMenuInflater().inflate(R.menu.first, menu);
	 	return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
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

	}

	@Override
	public void start() {
		// TODO Auto-generated method stub		
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getDuration() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCurrentPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void seekTo(int pos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isPlaying() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getBufferPercentage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean canPause() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canSeekBackward() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canSeekForward() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getAudioSessionId() {
		// TODO Auto-generated method stub
		return 0;
	}
}
