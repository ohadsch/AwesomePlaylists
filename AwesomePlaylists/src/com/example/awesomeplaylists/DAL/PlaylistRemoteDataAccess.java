package com.example.awesomeplaylists.DAL;

import java.util.List;
import android.content.Context;
import com.example.awesomeplaylists.BL.GenericPlaylist;
import com.example.awesomeplaylists.BL.UserData;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;


public class PlaylistRemoteDataAccess {
	private final static PlaylistRemoteDataAccess instance = new PlaylistRemoteDataAccess();
	private static boolean init = false;
	private PlaylistRemoteDataAccess(){}
	
	public static PlaylistRemoteDataAccess instance(Context context){
		if (!init){
			instance.init(context);
			init = true;
		}
		return instance;	
	}
	
	private void init(Context context){
		Parse.initialize(context, "quvBDhWr7OKhHuIWPawTIliQjshK6KvUTOu9qmcO", "OaenOyzFgfKB9EZJdAygAgA9Xq0tLMcA3I1ZtNwQ");
	}
	
		
	public void saveUserData(UserData ud){
		ParseObject po = new ParseObject("UserData");
		po.put("UserEMail", ud.userEmail);
		po.put("isShuffle", ud.isShuffle);
		po.put("lists", ud.lists);	
		po.put("STAM", "STAM");
		po.saveInBackground();
	}

	interface GetGenericPlaylistsClbk{
		public void done(List<GenericPlaylist> list);
	}
	
//	public void getAllStudents(final GetPlaylistsClbk clbk){
//		ParseQuery<ParseObject> pfq = new ParseQuery<ParseObject>("Student");
//		pfq.findInBackground(new FindCallback<ParseObject>() {
//			@Override
//			public void done(List<ParseObject> objects, ParseException e) {
//				List<Student> slist = new ArrayList<Student>();
//				for(ParseObject po : objects){
//					slist.add(new Student(po.getString("stid"),po.getString("name")));
//				}
//				clbk.done(slist);
//			}
//		});
	
		
//		ParseQuery<ParseObject> query = ParseQuery.getQuery("UserData");
//		query.getInBackground(objectId, callback)round(objectId, callback)round("xWMyZ4YEGZ", new GetPlaylistsClbk<ParseObject>() {
//		  public void done(ParseObject object, ParseException e) {
//		    if (e == null) {
//		      // object will be your game score
//		    } else {
//		      // something went wrong
//		    }
//		  }
//		});
//	}
	
}
