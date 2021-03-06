package com.example.awesomeplaylists.UI;

import com.example.awesomeplaylists.R;
import com.example.awesomeplaylists.R.id;
import com.example.awesomeplaylists.R.layout;
import com.example.awesomeplaylists.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

// moo

// moo2

// moo3

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
	
	public void goToLibrary(View view){
		Intent intent = new Intent(this,LibraryActivity.class);
		startActivity(intent);
	}
	
	public void goToPlaylists(View view){
		Intent intent = new Intent(this,PlaylistsActivity.class);
		startActivity(intent);
	}
	
	public void goToPlayIt(View view){
		Intent intent = new Intent(this,PlayItActivity.class);
		startActivity(intent);
	}
	
	public void goToSettings(View view){
		Intent intent = new Intent(this,UserSettingsActivity.class);
		startActivity(intent);
	}
}
