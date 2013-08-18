package src.stracker.asynchttp;

import java.util.ArrayList;

import src.stracker.CalendarActivity;
import src.stracker.json.CalendarSerializer;
import src.stracker.model.EpisodeSynopse;
import android.content.Context;
import android.content.Intent;

/**
 * @author diogomatos
 * This implementation represents a request to an user calendar
 */
public class CalendarRequest extends AbstractAsyncHttp {

	private CalendarSerializer _serializer;
	
	/**
	 * The constructor of the calendar request 
	 * @param context - activity context
	 * @param runnable - callback to be invoked
	 */
	public CalendarRequest(Context context, MyRunnable runnable) {
		super(context, runnable);
		_serializer = new CalendarSerializer();
	}
	
	/**
	 * @see src.stracker.asynchttp.AbstractAsyncHttp#onSuccessHook(java.lang.String)
	 * @param response - string with the Http Response 
	 */
	@Override
	protected void onSuccessHook(String response) {
		ArrayList<EpisodeSynopse> list = _serializer.deserialize(response);
		Intent intent = new Intent(_context,CalendarActivity.class);
		intent.putExtra("list", list);
		_context.startActivity(intent);
	}
}
