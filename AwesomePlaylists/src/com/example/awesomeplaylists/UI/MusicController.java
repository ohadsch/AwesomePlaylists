package com.example.awesomeplaylists.UI;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.MediaController;

public class MusicController extends MediaController
{

	private Activity myActivity;

	public MusicController(Context context)
	{
		super(context);

		myActivity = (Activity) context;
	}

	public MusicController(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public MusicController(Context context, boolean useFastForward)
	{
		super(context, useFastForward);	
	}

	@Override
	public void hide()
	{
		// Disable auto-hide of the controller
		this.show(0);
	}

	@Override
	public void setMediaPlayer(MediaPlayerControl player)
	{
		// TODO Auto-generated method stub
		super.setMediaPlayer(player);
		this.show();
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event)
	{
		// Close the controller+activity when back key was pressed
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK)
		{
			super.hide();
			myActivity.finish();

		}
		return true;
	}

}
