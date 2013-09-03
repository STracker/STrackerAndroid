package src.stracker.user_info;

import java.lang.reflect.Type;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;
import src.stracker.R;
import src.stracker.asynchttp.MyRunnable;
import src.stracker.asynchttp.UserRequests;
import src.stracker.model.Calendar;
/**
 * @author diogomatos
 * This class represents a manager for user calendar.
 */
public class CalendarManager implements IManager<ArrayList<Calendar>> {

	private ArrayList<Calendar> calendar;
	private final Context context;
	private final Gson gson;
	private final Type arrayListType;
	
	/**
	 * Constructor for CalendarManager
	 * @param context - context where the manager is needed
	 */
	public CalendarManager(Context context){
		this.context = context;
		gson = new Gson();
		arrayListType = new TypeToken<ArrayList<Calendar>>(){}.getType();
	}
	
	/**
	 * @see src.stracker.user_info.IManager#get(android.content.Context)
	 */
	@Override
	public ArrayList<Calendar> get(Context context) {
		//if exists in memory
		if(calendar != null) return calendar;
		//if exists in database
		Cursor cursor = context.getContentResolver().query(UserInfoProvider.CONTENT_URI, null, null, null, null);
		cursor.moveToNext();
		String json = cursor.getString(cursor.getColumnIndex(UserTableContract.CALENDAR));
		if(json != null){
			calendar = gson.fromJson(json, arrayListType);
			cursor.close();
			return calendar;
		}
		//make request
		sync(new Runnable() {
			@Override
			public void run() {}
		});
		return null;
	}

	/**
	 * @see src.stracker.user_info.IManager#sync(java.lang.Runnable)
	 */
	@Override
	public void sync(final Runnable runnable) {
		UserRequests.getCalendar(context, new MyRunnable() {
			@Override
			public void run() {
				Toast.makeText(context, R.string.error_calendar, Toast.LENGTH_SHORT).show();
			}
			@SuppressWarnings("unchecked")
			@Override
			public <T> void runWithArgument(T response) {
				update((ArrayList<Calendar>) response);
				runnable.run();
			}
		});
	}

	/**
	 * @see src.stracker.user_info.IManager#update(java.lang.Object)
	 */
	@Override
	public void update(ArrayList<Calendar> elem) {
		calendar = elem;
		ContentValues values = new ContentValues();
		values.put(UserTableContract.CALENDAR, gson.toJson(calendar, arrayListType));
		context.getContentResolver().update(UserInfoProvider.CONTENT_URI, values, null, null);
	}

	/**
	 * @see src.stracker.user_info.IManager#delete()
	 */
	@Override
	public void delete() {
		//Clear calendar because delete of the calendar is the same as deleting the user
		calendar = null;
	}

	/**
	 * @see src.stracker.user_info.IManager#savePersistently(java.lang.Object)
	 */
	@Override
	public void savePersistently(ArrayList<Calendar> elem) {
		calendar = elem;
		update(elem);
	}
}
