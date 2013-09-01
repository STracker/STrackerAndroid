package src.stracker.user_info;

import com.google.gson.Gson;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;
import src.stracker.FbLoginActivity;
import src.stracker.R;
import src.stracker.asynchttp.MyRunnable;
import src.stracker.asynchttp.UserRequests;
import src.stracker.model.Calendar;
import src.stracker.model.User;
import src.stracker.STrackerApp;
/**
 * @author diogomatos
 * This class represents a manager for user information.
 */
public class UserManager implements IManager<User>{
	
	private static final String USER_INFO = "user_info";
	private User user;
	private final SharedPreferences preferences;
	private final Gson gson;
	private final CalendarManager calendar;
	
	/**
	 * Constructor of user manager
	 * @param prefs - shared preferences
	 */
	public UserManager(SharedPreferences prefs){
		preferences = prefs;
		gson = new Gson();
		calendar = new CalendarManager(prefs);
	}
	
	/**
	 * @see src.stracker.user_info.IManager#get(android.content.Context)
	 */
	@Override
	public User get(Context context) {
		//verify in memory if exists
		if(user != null) return user;
		//verify in preferences if the user is created
	    String jsonInfo = preferences.getString(USER_INFO, null);
		if(jsonInfo != null){
			user = gson.fromJson(jsonInfo, User.class);
			((STrackerApp) context.getApplicationContext()).createHawkCreadentials(user.getId());
			return user;
		}
		//if there's no user in file then login 
		context.startActivity(new Intent(context, FbLoginActivity.class));
		return null;
	}

	/**
	 * @see src.stracker.user_info.IManager#sync(android.content.Context)
	 */
	@Override
	public void sync(final Context context) {
		if(user != null){
			//Request for get the information about the user
			UserRequests.getSelf(context, new MyRunnable() {
				@Override
				public void run() {
					Toast.makeText(context, R.string.error_user_req, Toast.LENGTH_SHORT).show();
				}
				@Override
				public <T> void runWithArgument(T response) {
					savePersistently((User) response);
					calendar.sync(context);
				}
			}, user.getId(), user.getVersion());
		}
	}

	/**
	 * @see src.stracker.user_info.IManager#update(java.lang.Object)
	 */
	@Override
	public void update(User elem) {
		//increment version then save the user
		user = elem;
		user.incVersion();
		savePersistently(user);
	}

	/**
	 * @see src.stracker.user_info.IManager#delete()
	 */
	@Override
	public void delete() {
		//Clear user
		user = null;
		//Remove previous data
		SharedPreferences.Editor edit = preferences.edit();
		edit.remove(USER_INFO);
		edit.commit();
		calendar.delete();
	}

	/**
	 * @see src.stracker.user_info.IManager#savePersistently(java.lang.Object)
	 */
	@Override
	public void savePersistently(User elem) {
		user = elem;
		SharedPreferences.Editor edit = preferences.edit();
		//Use shared preferences to save information about user
	    String json = gson.toJson(elem);
	    edit.putString(USER_INFO, json);
	    edit.commit();
	}	
	
	/**
	 * This method returns the user calendar.
	 * @param context - context of the activity where is called
	 * @return calendar
	 */
	public Calendar getCalendar(Context context){
		return calendar.get(context);
	}
}
