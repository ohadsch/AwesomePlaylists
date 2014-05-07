package com.example.awesomeplaylists.UI;

import java.util.regex.Pattern;

import com.example.awesomeplaylists.R;
import com.example.awesomeplaylists.R.id;
import com.example.awesomeplaylists.R.layout;
import com.example.awesomeplaylists.R.menu;
import com.example.awesomeplaylists.BL.UserData;
import com.example.awesomeplaylists.DAL.PlaylistRemoteDataAccess;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class UserSettingsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_settings);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_settings, menu);
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
	
	public void save(View view)
	{
		Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
		Account[] accounts = AccountManager.get(this).getAccounts();
		for (Account account : accounts) {
		    if (emailPattern.matcher(account.name).matches()) {
		        String possibleEmail = account.name;	
		        
		        PlaylistRemoteDataAccess.instance(this).saveUserData(new UserData(possibleEmail));
		    }
		}
		
		try{
			PlaylistRemoteDataAccess.instance(this).saveUserData(new UserData("asva"));
			PlaylistRemoteDataAccess.instance(this).saveUserData(new UserData("bbb@gmail.com"));
			PlaylistRemoteDataAccess.instance(this).saveUserData(new UserData("CCC@gmail.com"));
		}
		catch(Exception e){
			Log.d("FAIL PARSE", e.getMessage());
		}
		
	}
}
