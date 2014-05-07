package com.example.awesomeplaylists.BL;

public class GenericPlaylist {
	public String playlistName;
	public Boolean isClassic;
	public Boolean isElectronic;
	public Boolean isRap;
	public Boolean isHiphop;
	public int loudness = 2; // 0 = quiet, 1 = loud, 2 = both
	
	public GenericPlaylist() {
		
	}
	
	public GenericPlaylist(String playlistName, Boolean isClassic,
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