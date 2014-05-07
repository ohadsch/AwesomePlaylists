package com.example.awesomeplaylists.DAL;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


// This is the modal part - manage the SQLite data base, and implements the singleton design pattern
public class PlaylistDataAccess {	
	//implementation
	final int version = 2;
	class DatabaseHelper extends SQLiteOpenHelper {
	    public DatabaseHelper(Context context)
		{
	    	super(context, DATABASE_NAME, null, DATABASE_VERSION);			
		}
	 
	    @Override
	    public void onCreate(SQLiteDatabase db) {
	        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
	                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
	                + KEY_PH_NO + " TEXT," + KEY_ADDRESS + " TEXT" + ")";
	        db.execSQL(CREATE_CONTACTS_TABLE);
	    }
	 
	    @Override
	    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
	 
	        onCreate(db);
	    }	
	}

	static private PlaylistDataAccess instance = null;
	DatabaseHelper myDb;

	private PlaylistDataAccess(Context context){
		myDb = new DatabaseHelper(context);
	}

	public static PlaylistDataAccess instance(Context context){
		if (instance == null) instance = new PlaylistDataAccess(context);
		return instance;
	}
	
	private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "data.db";
    private static final String TABLE_CONTACTS = "Persons";
 
    // Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone";
    private static final String KEY_ADDRESS = "address";
   
    /*
    public void addItem(Person item) {
	    SQLiteDatabase db = myDb.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    values.put(KEY_ID, String.valueOf(item.get_id()));
	    values.put(KEY_NAME, item.get_name()); 
	    values.put(KEY_PH_NO, String.valueOf(item.get_phone()));
	    values.put(KEY_ADDRESS, item.get_address());
	 
	    // Inserting Row
	    db.insert(TABLE_CONTACTS, null, values);
	    db.close();
    }
     
    public Person getItem(int id) {
    	SQLiteDatabase db = myDb.getReadableDatabase();
    	 
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                KEY_NAME, KEY_PH_NO }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
     
        String itemId = cursor.getString(0);
    	String name = cursor.getString(1);
    	String phoneNumber = cursor.getString(2);
    	String address = cursor.getString(3);
    	    	
    	Person item = new Person(name, itemId, phoneNumber,address);

        return item;
    }
     
    public ArrayList<Person> getAllItems() {
    	ArrayList<Person> itemsList = new ArrayList<Person>();
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;
     
        SQLiteDatabase db = myDb.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
     
        if (cursor.moveToFirst()) {
            do {
            	 String itemId = cursor.getString(0);
             	String name = cursor.getString(1);
             	String phoneNumber = cursor.getString(2);
             	String address = cursor.getString(3);
            	
             	Person item = new Person(name, itemId, phoneNumber,address);
              	itemsList.add(item);
            } while (cursor.moveToNext());
        }
     
        return itemsList;
    }
     
    public int getItemsCount() {
    	String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        return cursor.getCount();
    }

    public int updateItem(Person item) {
    	SQLiteDatabase db = myDb.getWritableDatabase();
    	 
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, item.get_name());
        values.put(KEY_PH_NO, String.valueOf(item.get_phone()));
        values.put(KEY_ADDRESS, item.get_address());
     
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(item.get_id()) });
    }
     
    public void deleteItem(Person item) {
    	deleteItem(item.get_id());
    }
    */
    
    public void deleteItem(String itemId) {
    	SQLiteDatabase db = myDb.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] {itemId});
        db.close();
    }
}
