package com.example.awesomeplaylists.BL;

import java.util.ArrayList;


public class UserData {
	public ArrayList<GenericPlaylist> lists;
	public String userEmail;
	public boolean isShuffle;
	public boolean isBlaBla;
	
	public UserData(String email){
		userEmail = email;
		isShuffle = false;
		isBlaBla = false;
		lists = new ArrayList<GenericPlaylist>(); 
	}
}
