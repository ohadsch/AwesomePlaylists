package com.example.awesomeplaylists.BL;

/**
 * Stores all required info about a song
 * @author ALEX
 *
 */
public class GenericSong {
	/**
	 * ID: A unique ID acquired from the MediaStore cursor run
	 */
	public long ID;
    public String songTitle = "";
    public String songArtist = "";
    /**
     * Genres: Classic, Electronic, Rap, Hiphop
     */
    public Boolean[] genres = { false, false, false, false };
}