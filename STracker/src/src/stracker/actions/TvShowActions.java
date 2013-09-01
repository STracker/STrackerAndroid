package src.stracker.actions;

import src.stracker.BaseActivity;
import src.stracker.R;
import src.stracker.TvShowsByNameActivity;
import src.stracker.asynchttp.MyRunnable;
import src.stracker.asynchttp.UserRequests;
import src.stracker.model.TvShow;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author diogomatos
 * This class aggregate the actions associated with a television show.
 */
public class TvShowActions {
	
	/**
	 * This method pop's up an AlertDialog to begin the Search a television show by the name.
	 * @param activity - Activity where the method is called.
	 */
	public static void searchTvShowByName(final BaseActivity activity){
		AlertDialog.Builder adBuilder = new AlertDialog.Builder(activity);
		adBuilder.setMessage(activity.getString(R.string.tvshow_message));
		adBuilder.setCancelable(true);
		// Set an EditText view to get user input 
		final EditText input = new EditText(activity);
		adBuilder.setView(input);
		adBuilder.setPositiveButton(activity.getString(R.string.button_cancel),
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		}); 
		adBuilder.setNegativeButton(activity.getString(R.string.button_search),
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				Intent intent = new Intent(activity, TvShowsByNameActivity.class);
				intent.putExtra(BaseActivity.URI_PARAM, input.getText().toString().replaceAll(" ", "+"));
				activity.startActivity(intent);
			}
		});
		AlertDialog alertDialog = adBuilder.create();
		alertDialog.show();
	}
	
	/**
	 * This method pop's up an AlertDialog to ask for unsubscribe a television show.
	 * @param activity - Activity where the method is called.
	 * @param tvShow - television show to unsubscribe
	 */
	public static void unsubscribeTvShow(final BaseActivity activity, final TvShow tvShow){
		AlertDialog.Builder adBuilder = new AlertDialog.Builder(activity);
		adBuilder.setMessage(activity.getString(R.string.error_tv_unsubscribe));
		adBuilder.setCancelable(true);
		adBuilder.setPositiveButton(activity.getString(R.string.button_cancel),
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		}); 
		adBuilder.setNegativeButton(activity.getString(R.string.button_unsubscribe),
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				UserRequests.deleteSubscription(activity, new MyRunnable() {
					@Override
					public void run() {
						Toast.makeText(activity, R.string.error_unsubscribe, Toast.LENGTH_SHORT).show();
					}
					@Override
					public <T> void runWithArgument(T response) {
						Toast.makeText(activity, R.string.success_unsubscribe, Toast.LENGTH_SHORT).show();
						UserActions.removeSubscriptionFromUser(activity, tvShow);
					}
				}, tvShow.getId());
			}
		});
		AlertDialog alertDialog = adBuilder.create();
		alertDialog.show();
	}
}
