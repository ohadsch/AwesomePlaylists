package com.example.awesomeplaylists.BL;

/**
 * Stores all required info about a song
 * 
 * @author ALEX
 * 
 */
public class GenericSong
{
	/**
	 * ID: A unique ID acquired from the MediaStore cursor run
	 */
	public long ID;
	public String songTitle = "";
	public String songArtist = "";

	public GenericSong()
	{				
	}
	
	public GenericSong(long ID, String songTitle, String songArtist)
	{
		this.ID = ID;
		this.songTitle = songTitle;
		this.songArtist = songArtist;		
	}

	/**
	 * Genres: Classic, Electronic, Rap, Hiphop
	 */
	public Boolean[] genres =
	{ false, false, false, false };
}