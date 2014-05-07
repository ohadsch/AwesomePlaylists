package com.example.awesomeplaylists.BL;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.MediaController;

public class MusicController extends MediaController {

	public MusicController(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MusicController(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MusicController(Context context, boolean useFastForward) {
		super(context, useFastForward);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void hide()
	{
		this.show(0);
	}

	@Override
	public void setMediaPlayer(MediaPlayerControl player) {
		// TODO Auto-generated method stub
		super.setMediaPlayer(player);
		 this.show();
	}
}
