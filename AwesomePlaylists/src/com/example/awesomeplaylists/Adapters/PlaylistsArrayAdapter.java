package com.example.awesomeplaylists.Adapters;

import java.util.ArrayList;

import com.example.awesomeplaylists.R;
import com.example.awesomeplaylists.BL.GenericPlaylist;
import com.example.awesomeplaylists.R.id;
import com.example.awesomeplaylists.R.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PlaylistsArrayAdapter extends ArrayAdapter<GenericPlaylist> {

	private final Context context;
	private final ArrayList<GenericPlaylist> values;
	
	public PlaylistsArrayAdapter(Context context, ArrayList<GenericPlaylist> values) {
	    super(context, R.layout.playlists_row_layout, values);
	    this.context = context;
	    this.values = values;
	  }

	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View rowView = inflater.inflate(R.layout.playlists_row_layout, parent, false);
	    rowView.setTag(position);
	    
	    ImageView imageEdit = (ImageView) rowView.findViewById(R.id.playlists_row_edit);
	    imageEdit.setTag(position);
	    ImageView imageShare = (ImageView) rowView.findViewById(R.id.playlists_row_share);
	    imageShare.setTag(position);
	    ImageView imageDelete = (ImageView) rowView.findViewById(R.id.playlists_row_delete);
	    imageDelete.setTag(position);
	    
	    TextView textPlaylistName = 
	    		(TextView) rowView.findViewById(R.id.playlists_row_name);
	    textPlaylistName.setSelected(true);
	    textPlaylistName.setEnabled(true);
	    textPlaylistName.setFocusable(false);
	    textPlaylistName.setText(values.get(position).playlistName);

	    return rowView;
	  }

}
