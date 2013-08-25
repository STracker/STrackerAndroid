package src.stracker.utils;

import src.stracker.FbLoginActivity;
import src.stracker.R;
import src.stracker.STrackerApp;
import src.stracker.SearchFriendActivity;
import src.stracker.TvShowsByNameActivity;
import src.stracker.asynchttp.CommentRequests;
import src.stracker.asynchttp.MyRunnable;
import src.stracker.asynchttp.RatingRequests;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author diogomatos
 * This class represents a few utility methods used in STracker application.
 */
public class Utils {

	/**
	 * This method pop's up an AlertDialog to begin the Search a television show by the name.
	 * @param activity - Activity where the method is called.
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
				Intent intent = new Intent(activity, TvShowsByNameActivity.class);
				intent.putExtra("uri", input.getText().toString().replaceAll(" ", "+"));
				activity.startActivity(intent);
			}
		});
		AlertDialog alertDialog = adBuilder.create();
		alertDialog.show();
	}
	
	/**
	 * This method check if the application user is logged in with facebook.
	 * @param context - Context of the Activity where the login is needed.
	 * @return boolean - true if the user is logged in.
	 */
	public static boolean checkLogin(Context context){
		if(!((STrackerApp)context.getApplicationContext()).isLoggedIn())
			context.startActivity(new Intent(context,FbLoginActivity.class));
		return ((STrackerApp)context.getApplicationContext()).isLoggedIn();
	}
	
	/**
	 * This method pop's up an AlertDialog to begin the rating submission.
	 * @param url - URL of the resource where the rating is saved.
	 * @param activity - Activity where the method is called.
	 * @param rating - rating given by the user
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
				RatingRequests.postRating((STrackerApp)activity.getApplication(), new MyRunnable() {
					@Override
					public void run() {
						Toast.makeText(activity, R.string.error_sub_rating, Toast.LENGTH_SHORT).show();
					}
					@Override
					public <T> void runWithArgument(T response) {
						Toast.makeText(activity, R.string.submit_rating, Toast.LENGTH_SHORT).show();
					}
				}, url, rating+"");
			}
		});
		AlertDialog alertDialog = adBuilder.create();
		alertDialog.show();
	}
	
	/**
	 * This method pop's up an AlertDialog to write new comment.
	 * @param url - URL of the resource where the comment will be posted.
	 * @param activity - Activity where the method is called.
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
				CommentRequests.postComment((STrackerApp)activity.getApplication(), new MyRunnable() {
					@Override
					public void run() {
						Toast.makeText(activity, R.string.error_sub_comment, Toast.LENGTH_SHORT).show();
					}
					@Override
					public <T> void runWithArgument(T response) {
						Toast.makeText(activity, R.string.submit_comment, Toast.LENGTH_SHORT).show();
					}
				}, url, input.getText().toString());
			}
		});
		AlertDialog alertDialog = adBuilder.create();
		alertDialog.show();
	}
	
	/**
	 * This method pop's up an AlertDialog to begin the Search a friend in STracker.
	 * @param activity - Activity where the method is called.
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
				Intent intent = new Intent(activity, SearchFriendActivity.class);
				intent.putExtra("name", input.getText().toString().replaceAll(" ", "+"));
				activity.startActivity(intent);
			}
		});
		AlertDialog alertDialog = adBuilder.create();
		alertDialog.show();
	}
}
