package src.stracker.user_info;

import com.google.gson.Gson;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.widget.Toast;
import src.stracker.FbLoginActivity;
import src.stracker.R;
import src.stracker.asynchttp.MyRunnable;
import src.stracker.asynchttp.UserRequests;
import src.stracker.model.User;
import src.stracker.STrackerApp;
/**
 * @author diogomatos
 * This class represents a manager for user information.
 */
public class UserManager implements IManager<User>{
	
	private User user;
	private final Context _context;
	private final Gson gson;
	private final CalendarManager calendar;
	
	/**
	 * Constructor of user manager
	 * @param context - context where the manager is needed
	 */
	public UserManager(Context context){
		_context = context;
		gson = new Gson();
		calendar = new CalendarManager(context);
	}
	
	/**
	 * @see src.stracker.user_info.IManager#get(android.content.Context)
	 */
	@Override
	public User get(Context context) {
		//verify in memory if exists
		if(user != null) return user;
		//verify in database if the user is created
	    Cursor cursor = context.getContentResolver().query(UserInfoProvider.CONTENT_URI, null, null, null, null);
	    if(cursor.moveToNext()){
			user = gson.fromJson(cursor.getString(cursor.getColumnIndex(UserTableContract.USER)), User.class);
			((STrackerApp) context.getApplicationContext()).createHawkCreadentials(user.getId());
			cursor.close();
			return user;
		}
		//if there's no user in file then login 
		context.startActivity(new Intent(context, FbLoginActivity.class));
		return null;
	}

	/**
	 * @see src.stracker.user_info.IManager#sync(java.lang.Runnable)
	 */
	@Override
	public void sync(final Runnable runnable) {
		//Request for get the information about the user
		UserRequests.getSelf(_context, new MyRunnable() {
			@Override
			public void run() {
				Toast.makeText(_context, R.string.error_user_req, Toast.LENGTH_SHORT).show();
			}
			@Override
			public <T> void runWithArgument(T response) {
				localUpdate((User) response);
				calendar.sync(new Runnable() {
					@Override
					public void run() {}
				});
				runnable.run();
			}
		}, user.getId(), user.getVersion());
	}

	/**
	 * @see src.stracker.user_info.IManager#update(java.lang.Object)
	 */
	@Override
	public void update(User elem) {
		elem.incVersion();
		localUpdate(elem);
	}
	
	/**
	 * Auxiliary method to update user information without changing his version
	 * @param elem - user to update
	 */
	private void localUpdate(User elem){
		//save the user
		user = elem;
		ContentValues values = new ContentValues();
		values.put(UserTableContract.USER, gson.toJson(user));
		_context.getContentResolver().update(UserInfoProvider.CONTENT_URI, values, null, null);
	}

	/**
	 * @see src.stracker.user_info.IManager#delete()
	 */
	@Override
	public void delete() {
		//Clear user
		user = null;
		//Remove previous data
		_context.getContentResolver().delete(UserInfoProvider.CONTENT_URI, null, null);
		//Stop the updater service
		((STrackerApp)_context.getApplicationContext()).getUpdaterManager().cancelAlarmManager();
		//Clear shared preferences
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(_context);
		SharedPreferences.Editor editor = preferences.edit();
		editor.clear();
		editor.commit();
	}

	/**
	 * @see src.stracker.user_info.IManager#savePersistently(java.lang.Object)
	 */
	@Override
	public void savePersistently(User elem) {
		if(user == null){
			user = elem;
			ContentValues values = new ContentValues();
			values.put(UserTableContract.USER, gson.toJson(elem));
			_context.getContentResolver().insert(UserInfoProvider.CONTENT_URI, values);
		}
	}	
	
	/**
	 * This method returns the user calendar manager.
	 * @return calendar
	 */
	public CalendarManager getCalendar(){
		return calendar;
	}
	
	/**
	 * Verify if user is logged in
	 * @return boolean
	 */
	public boolean isLogged(){
		return user != null;
	}
}
