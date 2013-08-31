package src.stracker.actions;

import src.stracker.BaseActivity;
import src.stracker.R;
import src.stracker.STrackerApp;
import src.stracker.asynchttp.CommentRequests;
import src.stracker.asynchttp.MyRunnable;
import src.stracker.asynchttp.RatingRequests;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author diogomatos
 * This class aggregates the generic methods to television show and episode actions.
 */
public class SharedActions {
	
	/**
	 * This method pop's up an AlertDialog to begin the rating submission.
	 * @param url - URL of the resource where the rating is saved.
	 * @param activity - Activity where the method is called.
	 * @param rating - rating given by the user
	 */
	public static void initRatingSubmission(final String url, final BaseActivity activity, final int rating){
		AlertDialog.Builder adBuilder = new AlertDialog.Builder(activity);
		adBuilder.setMessage(activity.getString(R.string.rating_message));
		adBuilder.setCancelable(true);
		adBuilder.setPositiveButton(activity.getString(R.string.button_cancel),
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		}); 
		adBuilder.setNegativeButton(activity.getString(R.string.button_submit),
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
	public static void addComment(final String url, final BaseActivity activity){
		AlertDialog.Builder adBuilder = new AlertDialog.Builder(activity);
		adBuilder.setMessage(activity.getString(R.string.comment_message));
		final EditText input = new EditText(activity);
		adBuilder.setView(input);
		adBuilder.setCancelable(true);
		adBuilder.setPositiveButton(activity.getString(R.string.button_cancel),
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		}); 
		adBuilder.setNegativeButton(activity.getString(R.string.button_submit),
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
}
