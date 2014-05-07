package com.example.awesomeplaylists;

public class GenericPlaylistClass {
	String playlistName;
	Boolean isClassic;
	Boolean isElectronic;
	Boolean isRap;
	Boolean isHiphop;
	int loudness = 2; // 0 = quiet, 1 = loud, 2 = both
	
	public GenericPlaylistClass() {
		
	}
	
	public GenericPlaylistClass(String playlistName, Boolean isClassic,
						   Boolean isElectronic, Boolean isRap,
						   Boolean isHiphop, int loudness) {
		this.playlistName = playlistName;
		this.isClassic = isClassic;
		this.isElectronic = isElectronic;
		this.isRap = isRap;
		this.isHiphop = isHiphop;
		this.loudness = loudness;
	}
}