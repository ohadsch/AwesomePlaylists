package com.example.awesomeplaylists.Activities;

import com.example.awesomeplaylists.R;
import com.example.awesomeplaylists.BL.GenericPlaylist;
import com.example.awesomeplaylists.R.id;
import com.example.awesomeplaylists.R.layout;
import com.example.awesomeplaylists.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
		catch (NullPointerException ex) { }
		
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
			GenericPlaylist currPlaylist = PlaylistsActivity.playlists.get(playlistId);
			
			findViewById(R.id.add_edit_main_layout).setTag(playlistId);
			
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

	public void savePlaylist(View view) {
		GenericPlaylist currPlaylist;
		Object playlistIdObj = ((View) findViewById(R.id.add_edit_main_layout)).getTag();
		int playlistId = -1;
		if (playlistIdObj != null) {
			playlistId = (Integer) playlistIdObj;
			currPlaylist = PlaylistsActivity.playlists.get(playlistId);
		} else {
			currPlaylist = new GenericPlaylist();
		}
				
		TextView textPlaylistName = (TextView) findViewById(R.id.add_edit_name_input);
		currPlaylist.playlistName = textPlaylistName.getText().toString();
		
		CheckBox checkClassic = (CheckBox) findViewById(R.id.add_edit_check_classic);
		currPlaylist.isClassic = checkClassic.isChecked();
		
		CheckBox checkElectronic = (CheckBox) findViewById(R.id.add_edit_check_electronic);
		currPlaylist.isElectronic = checkElectronic.isChecked();
		
		CheckBox checkRap = (CheckBox) findViewById(R.id.add_edit_check_rap);
		currPlaylist.isRap = checkRap.isChecked();
		
		CheckBox checkHiphop = (CheckBox) findViewById(R.id.add_edit_check_hiphop);
		currPlaylist.isHiphop = checkHiphop.isChecked();
		
		RadioGroup radioLoudness = (RadioGroup) findViewById(R.id.add_edit_radio);
		 // 0 = quiet, 1 = loud, 2 = both
		if (radioLoudness.getCheckedRadioButtonId() == R.id.add_edit_radio_quiet) {
			currPlaylist.loudness = 0;
		} else {
			if (radioLoudness.getCheckedRadioButtonId() == R.id.add_edit_radio_loud) {
				currPlaylist.loudness = 1;
			} else {
				currPlaylist.loudness = 2;
			}
		}
		
		if (playlistId == -1) {
			PlaylistsActivity.playlists.add(currPlaylist);
		} else {
			PlaylistsActivity.playlists.set(playlistId, currPlaylist);
		}
		
		Intent intent = new Intent(this, PlaylistsActivity.class);
		startActivity(intent);
	}
}