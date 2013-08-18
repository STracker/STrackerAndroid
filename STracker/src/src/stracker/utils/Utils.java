package src.stracker.utils;

import java.util.HashMap;
import src.stracker.FbLoginActivity;
import src.stracker.FriendsActivity;
import src.stracker.R;
import src.stracker.STrackerApp;
import src.stracker.TvShowsByNameActivity;
import src.stracker.asynchttp.DummyRequest;
import src.stracker.asynchttp.MyRunnable;
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
				String url = activity.getString(R.string.uri_tvshow_search)+input.getText();
				Intent intent = new Intent(activity, TvShowsByNameActivity.class);
				intent.putExtra("uri", url.replaceAll(" ", "+"));
				activity.startActivity(intent);
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
				new DummyRequest(activity, new MyRunnable() {
					@Override
					public void run() {
						Toast.makeText(activity, R.string.error_sub_rating, Toast.LENGTH_SHORT).show();
					}
					@Override
					public <T> void runWithArgument(T response) {
						Toast.makeText(activity, R.string.submit_rating, Toast.LENGTH_SHORT).show();
					}
				}).authorizedPost(url, params);
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
				new DummyRequest(activity, new MyRunnable() {
					@Override
					public void run() {
						Toast.makeText(activity, R.string.error_sub_comment, Toast.LENGTH_SHORT).show();
					}
					@Override
					public <T> void runWithArgument(T response) {
						Toast.makeText(activity, R.string.submit_comment, Toast.LENGTH_SHORT).show();
					}
				}).authorizedPost(url, params);
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
				String uri = activity.getString(R.string.uri_user_search)+input.getText();
				Intent intent = new Intent(activity, FriendsActivity.class);
				intent.putExtra("uri", uri.replaceAll(" ", "+"));
				activity.startActivity(intent);
			}
		});
		AlertDialog alertDialog = adBuilder.create();
		alertDialog.show();
	}
}
