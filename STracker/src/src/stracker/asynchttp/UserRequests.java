package src.stracker.asynchttp;

import java.util.HashMap;

import android.content.Context;
import src.stracker.R;
import src.stracker.STrackerApp;
import src.stracker.json.CalendarSerializer;
import src.stracker.json.JSONLocator;
import src.stracker.json.UserSerializer;
import src.stracker.model.Calendar;
import src.stracker.model.User;
import src.stracker.utils.Utils;

public class UserRequests {
	
	private static UserSerializer userSerializer = (UserSerializer) JSONLocator.getInstance().getSerializer(User.class);
	private static CalendarSerializer calendarSerializer = (CalendarSerializer) JSONLocator.getInstance().getSerializer(Calendar.class);
	
	public static void postSubscription(Context context, MyRunnable runnable, String tvShowId){
		if(!Utils.checkLogin((STrackerApp)context.getApplicationContext())) return;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("", tvShowId);
		AsyncHttpRequest.authorizedPost(context, runnable, userSerializer, context.getString(R.string.uri_user_subscriptions), params);
	}

	public static void getCalendar(Context context, MyRunnable runnable){
		if(!Utils.checkLogin((STrackerApp)context.getApplicationContext())) return;
		AsyncHttpRequest.authorizedGet(context, runnable, calendarSerializer, context.getString(R.string.uri_user_newepisodes));
	}
	
	public static void getSelf(Context context, MyRunnable runnable, String userId){
		AsyncHttpRequest.authorizedGet(context, runnable, userSerializer, context.getString(R.string.uri_user_info).replace("userId", userId));
	}
	
	public static void getUser(Context context, MyRunnable runnable, String uri){
		if(!Utils.checkLogin((STrackerApp)context.getApplicationContext())) return;
		AsyncHttpRequest.authorizedGet(context, runnable, userSerializer, uri);
	}
	
	public static void postUser(Context context, MyRunnable runnable, String name, String email, String photoUrl){
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("Name", name);
		params.put("Email", email);
		params.put("Photo", photoUrl);
		AsyncHttpRequest.authorizedPost(context, runnable, null, context.getString(R.string.uri_users), params);
	}
}
