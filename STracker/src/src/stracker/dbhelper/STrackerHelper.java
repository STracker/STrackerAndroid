package src.stracker.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class STrackerHelper extends SQLiteOpenHelper{
    
	public static final String DATABASE_NAME = "stracker_db"; 
    public static final int DATABASE_VERSION = 1;
    
    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String RATING = "RATING";
    public static final String AIRDAY = "AIRDAY";
    public static final String RUNTIME = "RUNTIME";
    public static final String CREATOR_ID = "CREATOR_ID";
    public static final String CREATE_TVSHOW = "CREATE TABLE TVSHOW ( "
    												+ ID + " TEXT PRIMARY KEY IS NOT NULL,"
    												+ NAME + " TEXT IS NOT NULL,"
    												+ DESCRIPTION + " TEXT IS NOT NULL,"
    												+ RATING + " INTEGER,"
    												+ AIRDAY + " TEXT,"
    												+ RUNTIME + " INTEGER,"
    												+ CREATOR_ID + " TEXT IS NOT NULL )";
    
    public static final String TVSHOW_ID = "TVSHOW_ID";
    public static final String SEASON_NUMBER = "SEASON_NUMBER";
    public static final String CREATE_SEASON = "CREATE TABLE SEASON ( "
    												+ TVSHOW_ID + " TEXT IS NOT NULL,"
    												+ SEASON_NUMBER + "INTEGER IS NOT NULL,"
													+ "PRIMARY KEY ( " +TVSHOW_ID + ", " + SEASON_NUMBER + " ),"
													+ "FOREIGN KEY " + TVSHOW_ID + " REFERENCES TVSHOW (" + ID + ") )";
   
    public static final String EPISODE_NUMBER = "EPISODE_NUMBER";
    public static final String SEASON_ID = "SEASON_ID";
    public static final String CREATE_EPISODE = "CREATE TABLE EPISODE ( "
    				+ EPISODE_NUMBER + "  INTEGER IS NOT NULL,"
    				+ TVSHOW_ID + "  TEXT IS NOT NULL,"
    				+ SEASON_ID + "  TEXT IS NOT NULL,"
    				+ NAME + "  TEXT IS NOT NULL,"
    				+ DESCRIPTION + "  TEXT IS NOT NULL,"
    				+ RATING + " INTEGER,"
    				+ "PRIMARY KEY ( TVSHOW_ID, SEASON_ID, EPISODE_NUMBER ),"
    				+ "FOREIGN KEY TVSHOW_ID, SEASON_ID REFERENCES SEASON ( TVSHOW_ID, SEASON_NUMBER ) )";

    public static final String PHOTO = "PHOTO";
    public static final String CREATE_PERSON = "CREATE TABLE PERSON ( "
    		+ ID + " TEXT PRIMARY KEY IS NOT NULL,"
    		+ NAME + " TEXT IS NOT NULL,"
    		+ PHOTO + " BLOB )";

    public static final String ACTOR_ID = "ACTOR_ID";
    public static final String CHARACTER_NAME = "CHARACTER_NAME";
    public static final String CREATE_TVSHOW_ACTORS = "CREATE TABLE TVSHOW_ACTORS ( "
    		+ TVSHOW_ID + " TEXT IS NOT NULL, "
    		+ ACTOR_ID + " TEXT IS NOT NULL, "
    		+ CHARACTER_NAME + " TEXT IS NOT NULL, "
    		+ EPISODE_NUMBER + " INTEGER, "
    		+ "PRIMARY KEY ( TVSHOW_ID, ACTOR_ID ), "
    		+ "FOREIGN KEY TVSHOW_ID REFERENCES TVSHOW (ID), "
    		+ "FOREIGN KEY ACTOR_ID REFERENCES PERSON (ID) )";

    public static final String PERSON_ID = "PERSON_ID";
    public static final String CREATE_TVSHOW_CREATORS = "CREATE TABLE TVSHOW_CREATORS ( "
						+ TVSHOW_ID + " TEXT IS NOT NULL,"
						+ PERSON_ID + " TEXT IS NOT NULL,"
						+ "PRIMARY KEY ( TVSHOW_ID, PERSON_ID ),"
						+ "FOREIGN KEY TVSHOW_ID REFERENCES TVSHOW (ID),"
						+ "FOREIGN KEY PERSON_ID REFERENCES PERSON (ID) )";
    
    public static final String CREATE_TVSHOW_ARTWORK = "CREATE TABLE TVSHOW_ARTWORK ( "
						+ ID + " INTEGER PRIMARY KEY,"
						+ TVSHOW_ID + " TEXT IS NOT NULL,"
						+ SEASON_NUMBER + " INTEGER,"
						+ PHOTO + " BLOB IS NOT NULL,"
						+ "FOREIGN KEY TVSHOW_ID REFERENCES TVSHOW (ID) )";
    
    public static final String REVIEW_TEXT   = "REVIEW_TEXT";
    public static final String CREATE_REVIEW = "CREATE TABLE REVIEW ( "
					    + ID + " INTEGER PRIMARY KEY,"
						+ TVSHOW_ID + " TEXT IS NOT NULL,"
						+ PERSON_ID + " TEXT IS NOT NULL,"
						+ SEASON_NUMBER + " INTEGER,"
						+ EPISODE_NUMBER + " INTEGER,"
						+ REVIEW_TEXT + " IS NOT NULL,"
						+ " FOREIGN KEY TVSHOW_ID REFERENCES TVSHOW (ID),"
						+ " FOREIGN KEY PERSON_ID REFERENCES PERSON (ID) )";
    
    public static final String DROP_REVIEW  = "DROP TABLE IF EXISTS TVSHOW_REVIEW";
    public static final String DROP_TVSHOW_ARTWORK  = "DROP TABLE IF EXISTS TVSHOW_ARTWORK";
    public static final String DROP_TVSHOW_CREATORS = "DROP TABLE IF EXISTS TVSHOW_CREATORS";
    public static final String DROP_TVSHOW_ACTORS   = "DROP TABLE IF EXISTS TVSHOW_ACTORS";
    public static final String DROP_PERSON  = "DROP TABLE IF EXISTS PERSON";
    public static final String DROP_EPISODE = "DROP TABLE IF EXISTS EPISODE";
    public static final String DROP_SEASON  = "DROP TABLE IF EXISTS SEASON";
    public static final String DROP_TVSHOW  = "DROP TABLE IF EXISTS TVSHOW";
    
	public STrackerHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, DATABASE_NAME, factory, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TVSHOW);
		db.execSQL(CREATE_SEASON);
		db.execSQL(CREATE_EPISODE);
		db.execSQL(CREATE_PERSON);
		db.execSQL(CREATE_TVSHOW_ACTORS);
		db.execSQL(CREATE_TVSHOW_CREATORS);
		db.execSQL(CREATE_TVSHOW_ARTWORK);
		db.execSQL(CREATE_REVIEW);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(DROP_REVIEW);
		db.execSQL(DROP_TVSHOW_ARTWORK);
		db.execSQL(DROP_TVSHOW_CREATORS);
		db.execSQL(DROP_TVSHOW_ACTORS);
		db.execSQL(DROP_PERSON);
		db.execSQL(DROP_EPISODE);
		db.execSQL(DROP_SEASON);
		db.execSQL(DROP_TVSHOW);
	}
}
