package src.stracker.user_info;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class STrackerHelper extends SQLiteOpenHelper{
    
	private static final String DATABASE_NAME = "stracker_db"; 
    private static final int DATABASE_VERSION = 1;
    
	public STrackerHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String columns = UserTableContract._ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				 + UserTableContract.USER       + " TEXT, "
				 + UserTableContract.CALENDAR   + " TEXT "
				 ;
		String sql = "CREATE TABLE IF NOT EXISTS " + UserTableContract.USER_TABLE + " ( " + columns + " ) ";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + UserTableContract.USER_TABLE);
		onCreate(db);
	}
}