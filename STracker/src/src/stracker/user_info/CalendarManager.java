package src.stracker.user_info;

import com.google.gson.Gson;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;
import src.stracker.R;
import src.stracker.asynchttp.MyRunnable;
import src.stracker.asynchttp.UserRequests;
import src.stracker.model.Calendar;
/**
 * @author diogomatos
 * This class represents a manager for user calendar.
 */
public class CalendarManager implements IManager<Calendar> {

	private static final String CALENDAR_INFO = "calendar_info";
	private Calendar calendar;
	private final SharedPreferences preferences;
	private final Gson gson;
	
	/**
	 * Constructor for CalendarManager
	 * @param prefs - Shared Preference
	 */
	public CalendarManager(SharedPreferences prefs){
		preferences = prefs;
		gson = new Gson();
	}
	
	/**
	 * @see src.stracker.user_info.IManager#get(android.content.Context)
	 */
	@Override
	public Calendar get(Context context) {
		//if exists in memory
		if(calendar != null) return calendar;
		//if exists in shared preference
		String jsonInfo = preferences.getString(CALENDAR_INFO, null);
		if(jsonInfo != null){
			calendar = gson.fromJson(jsonInfo, Calendar.class);
			return calendar;
		}
		//make request
		sync(context);
		return null;
	}

	/**
	 * @see src.stracker.user_info.IManager#sync(android.content.Context)
	 */
	@Override
	public void sync(final Context context) {
		UserRequests.getCalendar(context, new MyRunnable() {
			@Override
			public void run() {
				Toast.makeText(context, R.string.error_calendar, Toast.LENGTH_SHORT).show();
			}
			@Override
			public <T> void runWithArgument(T response) {
				savePersistently((Calendar) response);
			}
		});
	}

	/**
	 * @see src.stracker.user_info.IManager#update(java.lang.Object)
	 */
	@Override
	public void update(Calendar elem) {
		//increment version then save the calendar
		calendar = elem;
		//user.incVersion();
		savePersistently(calendar);
	}

	/**
	 * @see src.stracker.user_info.IManager#delete()
	 */
	@Override
	public void delete() {
		//Clear calendar
		calendar = null;
		//Remove previous data
		SharedPreferences.Editor edit = preferences.edit();
		edit.remove(CALENDAR_INFO);
		edit.commit();
	}

	/**
	 * @see src.stracker.user_info.IManager#savePersistently(java.lang.Object)
	 */
	@Override
	public void savePersistently(Calendar elem) {
		calendar = elem;
		SharedPreferences.Editor edit = preferences.edit();
		//Use shared preferences to save information about user
	    String json = gson.toJson(elem);
	    edit.putString(CALENDAR_INFO, json);
	    edit.commit();
	}
}
