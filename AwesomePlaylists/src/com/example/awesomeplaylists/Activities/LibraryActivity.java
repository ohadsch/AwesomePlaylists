package com.example.awesomeplaylists.Activities;


import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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

import com.example.awesomeplaylists.R;
import com.example.awesomeplaylists.Adapters.LibrarySongsArrayAdapter;
import com.example.awesomeplaylists.BL.GenericSong;

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
        String selection = MediaStore.Audio.Media.IS_MUSIC + " = 1";
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
            Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
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
	
//	private void LongSongsFromCard2() {
//		Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//        // Perform a query on the content resolver. The URI we're passing specifies that we
//        // want to query for all audio media on external storage (e.g. SD card)
//        Cursor cur = ContentResolver.query(uri, null,
//                MediaStore.Audio.Media.IS_MUSIC + " = 1", null, null);
//        Log.i(TAG, "Query finished. " + (cur == null ? "Returned NULL." : "Returned a cursor."));
//        if (cur == null) {
//            // Query failed...
//            Log.e(TAG, "Failed to retrieve music: cursor is null :-(");
//            return;
//        }
//        if (!cur.moveToFirst()) {
//            // Nothing to query. There is no music on the device. How boring.
//            Log.e(TAG, "Failed to move cursor to first row (no query results).");
//            return;
//        }
//        Log.i(TAG, "Listing...");
//        // retrieve the indices of the columns where the ID, title, etc. of the song are
//        int artistColumn = cur.getColumnIndex(MediaStore.Audio.Media.ARTIST);
//        int titleColumn = cur.getColumnIndex(MediaStore.Audio.Media.TITLE);
//        int albumColumn = cur.getColumnIndex(MediaStore.Audio.Media.ALBUM);
//        int durationColumn = cur.getColumnIndex(MediaStore.Audio.Media.DURATION);
//        int idColumn = cur.getColumnIndex(MediaStore.Audio.Media._ID);
//        Log.i(TAG, "Title column index: " + String.valueOf(titleColumn));
//        Log.i(TAG, "ID column index: " + String.valueOf(titleColumn));
//        // add each song to mItems
//        do {
//            Log.i(TAG, "ID: " + cur.getString(idColumn) + " Title: " + cur.getString(titleColumn));
//            mItems.add(new Item(
//                    cur.getLong(idColumn),
//                    cur.getString(artistColumn),
//                    cur.getString(titleColumn),
//                    cur.getString(albumColumn),
//                    cur.getLong(durationColumn)));
//        } while (cur.moveToNext());
//        Log.i(TAG, "Done querying media. MusicRetriever is ready.");
//	}
}
