package src.stracker.user_info;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * @author diogomatos
 * STracker ContentProvider to user information
 */
public class UserInfoProvider extends ContentProvider {

	public static final String  AUTHORITY    = "src.stracker.user_provider";
	public static final Uri     CONTENT_URI  = Uri.parse(ContentResolver.SCHEME_CONTENT + "://" + AUTHORITY);	
	private static UriMatcher   URIMATCHER   = new UriMatcher(UriMatcher.NO_MATCH);
	private static final int    STATUS_ID  = 1;
	private static final int    STATUS_ALL = 2;
	private static final String MIME_STATUS_ALL = "vnd.android.cursor.dir/vnd.src.stracker.user." + UserTableContract.USER_TABLE;
	private static final String MIME_STATUS_ONE = "vnd.android.cursor.item/vnd.src.stracker.user." + UserTableContract.USER_TABLE;

	//DbHelper
	private STrackerHelper _dbHelper;

	static {
		URIMATCHER.addURI(AUTHORITY, "STATUS/#", STATUS_ID);
		URIMATCHER.addURI(AUTHORITY, "STATUS", STATUS_ALL);
	}

	/**
	 * @see android.content.ContentProvider#onCreate()
	 */
	@Override
	public boolean onCreate() {
		_dbHelper = new STrackerHelper(getContext());
		return true;
	}

	/**
	 * @see android.content.ContentProvider#getType(android.net.Uri)
	 */
	@Override
	public String getType(Uri uri) {
		return URIMATCHER.match(uri) == STATUS_ALL ? MIME_STATUS_ALL : MIME_STATUS_ONE;
	}

	/**
	 * @see android.content.ContentProvider#insert(android.net.Uri, android.content.ContentValues)
	 */
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = _dbHelper.getWritableDatabase();
		try{
			long row = db.insert(UserTableContract.USER_TABLE, null, values);
			return row == -1 ? null : ContentUris.withAppendedId(uri, row);
		}finally{
			db.close();
		}
	}

	/**
	 * @see android.content.ContentProvider#query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String)
	 */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
			String sortOrder) {
		SQLiteDatabase db = _dbHelper.getReadableDatabase();
		return db.query(UserTableContract.USER_TABLE, projection, selection, selectionArgs, null, null, sortOrder);
	}

	/**
	 * @see android.content.ContentProvider#update(android.net.Uri, android.content.ContentValues, java.lang.String, java.lang.String[])
	 */
	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		SQLiteDatabase db = _dbHelper.getWritableDatabase();
		try{
			return db.update(UserTableContract.USER_TABLE, values, selection, selectionArgs);
		}finally{
			db.close();
		}
	}

	/**
	 * @see android.content.ContentProvider#delete(android.net.Uri, java.lang.String, java.lang.String[])
	 */
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = _dbHelper.getWritableDatabase();
		try{
			return db.delete(UserTableContract.USER_TABLE, selection, selectionArgs);
		}finally{
			db.close();
		}
	}
}