package com.example.awesomeplaylists.UI;


import com.example.awesomeplaylists.R;
import com.example.awesomeplaylists.Adapters.LibrarySongsArrayAdapter;
import com.example.awesomeplaylists.BL.GenericSong;
import com.example.awesomeplaylists.R.id;
import com.example.awesomeplaylists.R.layout;
import com.example.awesomeplaylists.R.menu;

import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;

public class LibraryActivity extends ListActivity {
	
	private static GenericSong[] songs = {};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_library);
		
		LoadSongsFromCard();
		
		LibrarySongsArrayAdapter adapter = 
				new LibrarySongsArrayAdapter(this, songs);
	    setListAdapter(adapter);
	    
	    ListView list = (ListView) findViewById(android.R.id.list);
	    list.setOnItemClickListener(new OnItemClickListener() {
	    	@Override
	    	public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) {
	    		
	    		LinearLayout libraryGenres = (LinearLayout) view.findViewById(R.id.library_genres_info);
	    		
	    		if (libraryGenres.getVisibility() == LinearLayout.VISIBLE) {
	    			libraryGenres.setVisibility(LinearLayout.GONE);
	    		} else {
	    			libraryGenres.setVisibility(LinearLayout.VISIBLE);
	    		}
	    	}
		});
	    
	    list.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
	    		LinearLayout libraryGenres = (LinearLayout) view.findViewById(R.id.library_genres_info);
	    		
	    		if (libraryGenres.getVisibility() == LinearLayout.VISIBLE) {
	    			libraryGenres.setVisibility(LinearLayout.GONE);
	    		} else {
	    			libraryGenres.setVisibility(LinearLayout.VISIBLE);
	    		}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.library_activity, menu);
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
	
	public void genreClicked(View view){
		int checkedId = Integer.parseInt(view.getTag().toString());
		int rowId = Integer.parseInt(((View) view.getParent().getParent()).getTag().toString());
		
		songs[rowId].genres[checkedId] = ((CheckBox) view).isChecked();
	}
	
	private void LoadSongsFromCard() {		
        /** Making custom drawable */
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        /* AND " 
        		+ MediaStore.Audio.Media.MIME_TYPE + "= 'audio/mpeg3' AND "
        		+ MediaStore.Audio.Media.DURATION + " > 60000";*/
        final String[] projection = new String[] {
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.ARTIST
        };
        
        final String sortOrder = MediaStore.Audio.AudioColumns.TITLE
                + " COLLATE LOCALIZED ASC";

        Cursor cursor = null;
        
        try {
            // the uri of the table that we want to query
            Uri uri = MediaStore.Audio.Media.getContentUriForPath("");
            // query the db
            cursor = getBaseContext().getContentResolver().query(uri,
                    projection, selection, null, sortOrder);
            if (cursor != null) {
                songs = new GenericSong[cursor.getCount()];
                int i = 0;
                cursor.moveToFirst();                       
                while (!cursor.isAfterLast()) { 
                	GenericSong GSC = new GenericSong();
                    GSC.songTitle = cursor.getString(0);
                    GSC.songArtist = cursor.getString(1);
                    songs[i++] = GSC;
                    cursor.moveToNext();
                }
            }
        } catch (Exception ex) {

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
