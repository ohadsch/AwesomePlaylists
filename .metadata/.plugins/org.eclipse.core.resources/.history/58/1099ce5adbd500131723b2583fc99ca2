package com.example.awesomeplaylists;

import com.example.awesomeplaylists.PlaylistsActivity.GenericPlaylistClass;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

public class AddEditPlaylistActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_edit_playlist);
		
		int playlistId = -1;
		
		try {
			playlistId = Integer.parseInt(this.getIntent().getExtras().get("PlaylistId").toString());
		}
		catch (NumberFormatException ex) { }
		
		LoadExistingPlaylist(playlistId);
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
	
	private void LoadExistingPlaylist(int playlistId) {
		if (playlistId != -1) {
			GenericPlaylistClass currPlaylist = PlaylistsActivity.playlists.get(playlistId);
			TextView textPlaylistName = (TextView) findViewById(R.id.add_edit_name_input);
			textPlaylistName.setText(currPlaylist.playlistName);
			
			CheckBox checkClassic = (CheckBox) findViewById(R.id.add_edit_check_classic);
			checkClassic.setChecked(currPlaylist.isClassic);
			
			CheckBox checkElectronic = (CheckBox) findViewById(R.id.add_edit_check_electronic);
			checkElectronic.setChecked(currPlaylist.isElectronic);
			
			CheckBox checkRap = (CheckBox) findViewById(R.id.add_edit_check_rap);
			checkRap.setChecked(currPlaylist.isRap);
			
			CheckBox checkHiphop = (CheckBox) findViewById(R.id.add_edit_check_hiphop);
			checkHiphop.setChecked(currPlaylist.isHiphop);
			
			RadioGroup radioLoudness = (RadioGroup) findViewById(R.id.add_edit_radio);
			 // 0 = quiet, 1 = loud, 2 = both
			if (currPlaylist.loudness == 0) {
				radioLoudness.check(R.id.add_edit_radio_quiet);
			} else {
				if (currPlaylist.loudness == 1) {
					radioLoudness.check(R.id.add_edit_radio_loud);
				} else {
					radioLoudness.check(R.id.add_edit_radio_all);
				}
			}
		}
	}
}
