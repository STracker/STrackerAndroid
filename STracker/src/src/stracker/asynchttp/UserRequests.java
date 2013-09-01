package src.stracker.asynchttp;

import java.util.HashMap;
import android.content.Context;
import src.stracker.R;
import src.stracker.STrackerApp;
import src.stracker.json.CalendarSerializer;
import src.stracker.json.FriendsSerializer;
import src.stracker.json.JSONLocator;
import src.stracker.json.UserSerializer;
import src.stracker.model.Calendar;
import src.stracker.model.User;
import src.stracker.model.UserSynopse;
/**
 * @author diogomatos
 * This class have all the HTTP requests for an User.
 */
public class UserRequests {
	
	private static UserSerializer userSerializer = (UserSerializer) JSONLocator.getInstance().getSerializer(User.class);
	private static CalendarSerializer calendarSerializer = (CalendarSerializer) JSONLocator.getInstance().getSerializer(Calendar.class);
	private static FriendsSerializer friendsSerializer = (FriendsSerializer) JSONLocator.getInstance().getSerializer(UserSynopse.class);
	
	/**
	 * This method represents a subscription to a television show
	 * @param context - context of the Activity where the request occur
	 * @param runnable - callback that will be called after the HTTP request
	 * @param tvShowId - identifier of a television show for the subscription
	 */
	public static void postSubscription(Context context, MyRunnable runnable, String tvShowId){
		if(((STrackerApp)context.getApplicationContext()).getUserManager().get(context) == null) return;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("", tvShowId);
		AsyncHttpRequest.authorizedPost(context, runnable, null, context.getString(R.string.uri_user_subscriptions).replace("/tvShowId", ""), params);
	}

	/**
	 * This method represents a subscription to a television show
	 * @param context - context of the Activity where the request occur
	 * @param runnable - callback that will be called after the HTTP request
	 * @param tvShowId - identifier of a television show for the subscription
	 */
	public static void deleteSubscription(Context context, MyRunnable runnable, String tvShowId){
		if(((STrackerApp)context.getApplicationContext()).getUserManager().get(context) == null) return;
		AsyncHttpRequest.authorizedDelete(context, runnable, null, context.getString(R.string.uri_user_subscriptions).replace("tvShowId", tvShowId));
	}
	
	/**
	 * This method represents a calendar of an user
	 * @param context - context of the Activity where the request occur
	 * @param runnable - callback that will be called after the HTTP request
	 */
	public static void getCalendar(Context context, MyRunnable runnable){
		if(((STrackerApp)context.getApplicationContext()).getUserManager().get(context) == null) return;
		AsyncHttpRequest.authorizedGet(context, runnable, calendarSerializer, context.getString(R.string.uri_user_newepisodes));
	}
	
	/**
	 * This method represents a request to the user information
	 * @param context - context of the Activity where the request occur
	 * @param runnable - callback that will be called after the HTTP request
	 * @param userId - identifier of a user
	 * @param version - user version
	 */
	public static void getSelf(Context context, MyRunnable runnable, String userId, int version){
		AsyncHttpRequest.authorizedGetWithVersion(context, runnable, userSerializer, context.getString(R.string.uri_user_info).replace("userId", userId), version);
	}
	
	/**
	 * This method represents an user information request
	 * @param context - context of the Activity where the request occur
	 * @param runnable - callback that will be called after the HTTP request
	 * @param uri - URI of the resource
	 */
	public static void getUser(Context context, MyRunnable runnable, String uri){
		if(((STrackerApp)context.getApplicationContext()).getUserManager().get(context) == null) return;
		AsyncHttpRequest.authorizedGet(context, runnable, userSerializer, uri);
	}
	
	/**
	 * This method represents a registration of an user
	 * @param context - context of the Activity where the request occur
	 * @param runnable - callback that will be called after the HTTP request
	 * @param name - user name
	 * @param email - user email
	 * @param photoUrl - user photo
	 */
	public static void postUser(Context context, MyRunnable runnable, String name, String email, String photoUrl){
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("Name", name);
		params.put("Email", email);
		params.put("Photo", photoUrl);
		AsyncHttpRequest.authorizedPost(context, runnable, null, context.getString(R.string.uri_users), params);
	}
	
	/**
	 * This method represents a friend request to a STracker user
	 * @param context - context of the Activity where the request occur
	 * @param runnable - callback that will be called after the HTTP request
	 * @param id - identifier of an user to ask for friendship
	 */
	public static void postFriendRequest(Context context, MyRunnable runnable, String id){
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("",id);
		AsyncHttpRequest.authorizedPost(context, runnable, null, context.getString(R.string.uri_user_friends).replace("/userId", ""), params);
	}
	
	/**
	 * This method represents a delete of a friend
	 * @param context - context of the Activity where the request occur
	 * @param runnable - callback that will be called after the HTTP request
	 * @param id - identifier of an user to delete
	 */
	public static void deleteFriend(Context context, MyRunnable runnable, String id){
		AsyncHttpRequest.authorizedDelete(context, runnable, null, context.getString(R.string.uri_user_friends).replace("userId", id));
	}
	
	/**
	 * This method represents an accept to a friend request from an user
	 * @param context - context of the Activity where the request occur
	 * @param runnable - callback that will be called after the HTTP request
	 * @param id - identifier of the user
	 */
	public static void postAcceptFriendRequest(Context context, MyRunnable runnable, String id){
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("",id);
		AsyncHttpRequest.authorizedPost(context, runnable, null, context.getString(R.string.uri_user_friends_req).replace("/userId", ""), params);
	}
	
	/**
	 * This method represents a reject to a friend request from an user
	 * @param context - context of the Activity where the request occur
	 * @param runnable - callback that will be called after the HTTP request
	 * @param id - identifier of the user
	 */
	public static void deleteFriendRequest(Context context, MyRunnable runnable, String id){
		AsyncHttpRequest.authorizedDelete(context, runnable, null, context.getString(R.string.uri_user_friends_req).replace("userId", id));
	}
	
	/**
	 * This method represents a reject an user suggestion
	 * @param context - context of the Activity where the request occur
	 * @param runnable - callback that will be called after the HTTP request
	 * @param tvShowId - identifier of the television show
	 * @param userId - identifier of an user
	 */
	public static void deleteSuggestion(Context context, MyRunnable runnable, String tvShowId, String userId){
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("",userId);
		AsyncHttpRequest.authorizedDelete(context, runnable, null, context.getString(R.string.uri_user_suggestion).replace("tvShowId", tvShowId));
	}
	
	/**
	 * This method represents search for a user in STracker.
	 * @param context - context of the Activity where the request occur
	 * @param runnable - callback that will be called after the HTTP request
	 * @param name - name of the user to search for
	 */
	public static void getSearchFriends(Context context, MyRunnable runnable, String name){
		AsyncHttpRequest.authorizedGet(context, runnable, friendsSerializer, context.getString(R.string.uri_user_search)+name);
	}
}
