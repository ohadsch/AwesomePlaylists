package com.example.awesomeplaylists.Activities;


import java.util.ArrayList;

import com.example.awesomeplaylists.R;
import com.example.awesomeplaylists.Adapters.PlaylistsArrayAdapter;
import com.example.awesomeplaylists.BL.GenericPlaylist;
import com.example.awesomeplaylists.R.id;
import com.example.awesomeplaylists.R.layout;
import com.example.awesomeplaylists.R.menu;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class PlaylistsActivity extends ListActivity {
	
	public static ArrayList<GenericPlaylist> playlists = new ArrayList<GenericPlaylist>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_playlists);
		
		if (playlists.isEmpty()) {
			LoadDummyPlaylists();
		}
		
		PlaylistsArrayAdapter adapter = 
				new PlaylistsArrayAdapter(this, playlists);
	    setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.library_playlists, menu);
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
		
	private static void LoadDummyPlaylists() {
		playlists.add(
				new GenericPlaylist("Running in gym", false, true, false, 
						true, 1));
		playlists.add(
				new GenericPlaylist("Riding a bus", false, false, true, 
						true, 2));
		playlists.add(
				new GenericPlaylist("Driving", true, true, false, 
						false, 0));
		playlists.add(
				new GenericPlaylist("Studying...", true, false, false, 
						false, 1));
    }
	
	public void editPlaylist(View view) {
		Intent intent = new Intent(this,AddEditPlaylistActivity.class);
		intent.putExtra("PlaylistId", (Integer) view.getTag());
		startActivity(intent);
	}
	
	public void sharePlaylist(View view) {
		// Do something...
	}
	
	public void deletePlaylist(View view) {
		// Do something...
	}
	
	public void newPlaylist(View view) {
		Intent intent = new Intent(this,AddEditPlaylistActivity.class);
		startActivity(intent);
	}
}
