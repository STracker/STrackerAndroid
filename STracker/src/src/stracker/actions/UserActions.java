package src.stracker.actions;

import java.util.ArrayList;

import src.stracker.BaseActivity;
import src.stracker.R;
import src.stracker.STrackerApp;
import src.stracker.SearchFriendActivity;
import src.stracker.model.EpisodeSynopse;
import src.stracker.model.Subscription;
import src.stracker.model.TvShow;
import src.stracker.model.User;
import src.stracker.model.UserSynopse;
import src.stracker.user_info.UserManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.EditText;

/**
 * @author diogomatos
 * This class aggregate the actions associated with an user.
 */
public class UserActions {
	
	/**
	 * This method pop's up an AlertDialog to begin the Search a friend in STracker.
	 * @param activity - Activity where the method is called.
	 */
	public static void searchFriend(final BaseActivity activity){
		AlertDialog.Builder adBuilder = new AlertDialog.Builder(activity);
		adBuilder.setMessage(activity.getString(R.string.rating_message));
		adBuilder.setCancelable(true);
		// Set an EditText view to get user input 
		final EditText input = new EditText(activity);
		adBuilder.setView(input);
		adBuilder.setNegativeButton(activity.getString(R.string.button_cancel),
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		}); 
		adBuilder.setPositiveButton(activity.getString(R.string.button_search),
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				Intent intent = new Intent(activity, SearchFriendActivity.class);
				intent.putExtra(BaseActivity.NAME_PARAM, input.getText().toString().replaceAll(" ", "+"));
				activity.startActivity(intent);
			}
		});
		AlertDialog alertDialog = adBuilder.create();
		alertDialog.show();
	}
	
	/**
	 * This method removes friend from user's list FriendRequests
	 * @param activity - Activity where the method is called.
	 * @param synopse - user to remove
	 */
	public static void removeFriendFromRequests(BaseActivity activity, UserSynopse synopse){
		UserManager manager = ((STrackerApp) activity.getApplication()).getUserManager();
		User user = manager.get(activity);
		user.removeFriendRequest(synopse);
		manager.update(user);
	}
	
	/**
	 * This method accept a friend request. Remove from FriendRequests list and add in Friends list.
	 * @param activity - Activity where the method is called.
	 * @param synopse - user to accept as friend
	 */
	public static void acceptFriendRequest(BaseActivity activity, UserSynopse synopse){
		UserManager manager = ((STrackerApp) activity.getApplication()).getUserManager();
		User user = manager.get(activity);
		user.removeFriendRequest(synopse);
		user.addFriend(synopse);
		manager.update(user);
	}
	
	/**
	 * This method add a subscription to user information
	 * @param activity - Activity where the method is called.
	 * @param tvshow - television show to subscribe
	 */
	public static void addSubscriptionToUser(BaseActivity activity, TvShow tvshow){
		UserManager manager = ((STrackerApp) activity.getApplication()).getUserManager();
		User user = manager.get(activity);
		user.addSubscription(new Subscription(tvshow.generateSynopse(activity.getString(R.string.uri_tvshow)), new ArrayList<EpisodeSynopse>()));
		manager.update(user);
	}
	
	/**
	 * This method remove a subscription from user information
	 * @param activity - Activity where the method is called.
	 * @param tvshow - television show to subscribe
	 */
	public static void removeSubscriptionFromUser(BaseActivity activity, TvShow tvshow){
		UserManager manager = ((STrackerApp) activity.getApplication()).getUserManager();
		User user = manager.get(activity);
		user.removeSubscription(new Subscription(tvshow.generateSynopse(activity.getString(R.string.uri_tvshow)), new ArrayList<EpisodeSynopse>()));
		manager.update(user);
	}
	
	/**
	 * This method remove a friend from user
	 * @param activity - Activity where the method is called.
	 * @param synopse - user to remove
	 */
	public static void removeFriendFromUser(BaseActivity activity, UserSynopse synopse){
		UserManager manager = ((STrackerApp) activity.getApplication()).getUserManager();
		User user = manager.get(activity);
		user.removeFriend(synopse);
		manager.update(user);
	}
}
