package src.stracker.utils;

import java.util.HashMap;
import src.stracker.FbLoginActivity;
import src.stracker.R;
import src.stracker.STrackerApp;
import src.stracker.asynchttp.DummyRequest;
import src.stracker.asynchttp.FriendsRequest;
import src.stracker.asynchttp.SearchByNameRequest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

public class Utils {

	/**
	 * This method pop's up an AlertDialog to begin the Search a tv show by the name.
	 */
	public static void initSearchByName(final Activity activity){
		AlertDialog.Builder adBuilder = new AlertDialog.Builder(activity);
		adBuilder.setMessage("Enter TvShow's Name:");
		adBuilder.setCancelable(true);
		// Set an EditText view to get user input 
		final EditText input = new EditText(activity);
		adBuilder.setView(input);
		adBuilder.setPositiveButton("Cancel",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		}); 
		adBuilder.setNegativeButton("Search",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				String url = activity.getString(R.string.uri_host_api)+activity.getString(R.string.uri_tvshow_search)+input.getText();
				new SearchByNameRequest(activity).get(url.replaceAll(" ", "+"));
			}
		});
		AlertDialog alertDialog = adBuilder.create();
		alertDialog.show();
	}
	
	/**
	 * This method check if the application user is logged in with facebook
	 */
	public static boolean checkLogin(Activity activity, STrackerApp app){
		if(!app.isLoggedIn())
			activity.startActivity(new Intent(activity,FbLoginActivity.class));
		return app.isLoggedIn();
	}
	
	/**
	 * This method check if the device has internet connectivity
	 */
	public static boolean checkConectivity(Activity activity, STrackerApp app){
		if(!app.verifyInternetConnection()){
			Toast.makeText(activity, "No internet connection!", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}
	
	/**
	 * This method pop's up an AlertDialog to begin the rating submission.
	 */
	public static void initRatingSubmission(final String url, final Activity activity, final int rating){
		AlertDialog.Builder adBuilder = new AlertDialog.Builder(activity);
		adBuilder.setMessage("Do you really want to submit the Rating?");
		adBuilder.setCancelable(true);
		adBuilder.setPositiveButton("Cancel",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		}); 
		adBuilder.setNegativeButton("Submit",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				HashMap<String, String> params = new HashMap<String, String>();
				params.put("", rating+"");
				new DummyRequest(activity).authorizedPost(url, params);
			}
		});
		AlertDialog alertDialog = adBuilder.create();
		alertDialog.show();
	}
	
	/**
	 * This method pop's up an AlertDialog to write new comment
	 */
	public static void addComment(final String url, final Activity activity){
		AlertDialog.Builder adBuilder = new AlertDialog.Builder(activity);
		adBuilder.setMessage("Please insert text comment:");
		final EditText input = new EditText(activity);
		adBuilder.setView(input);
		adBuilder.setCancelable(true);
		adBuilder.setPositiveButton("Cancel",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		}); 
		adBuilder.setNegativeButton("Submit",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				HashMap<String, String> params = new HashMap<String, String>();
				params.put("", input.getText().toString());
				new DummyRequest(activity).authorizedPost(url, params);
			}
		});
		AlertDialog alertDialog = adBuilder.create();
		alertDialog.show();
	}
	
	/**
	 * This method pop's up an AlertDialog to begin the Search a friend in STracker.
	 */
	public static void initSearchFriend(final Activity activity){
		AlertDialog.Builder adBuilder = new AlertDialog.Builder(activity);
		adBuilder.setMessage("Enter Friend's Name:");
		adBuilder.setCancelable(true);
		// Set an EditText view to get user input 
		final EditText input = new EditText(activity);
		adBuilder.setView(input);
		adBuilder.setPositiveButton("Cancel",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		}); 
		adBuilder.setNegativeButton("Search",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				String url = activity.getString(R.string.uri_host_api)+activity.getString(R.string.uri_user_search)+input.getText();
				new FriendsRequest(activity).authorizedGet(url.replaceAll(" ", "+"));
			}
		});
		AlertDialog alertDialog = adBuilder.create();
		alertDialog.show();
	}
}
