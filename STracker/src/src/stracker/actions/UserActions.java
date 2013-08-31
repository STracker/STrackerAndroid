package src.stracker.actions;

import src.stracker.BaseActivity;
import src.stracker.FbLoginActivity;
import src.stracker.R;
import src.stracker.STrackerApp;
import src.stracker.SearchFriendActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.EditText;

/**
 * @author diogomatos
 * This class aggregate the actions associated with an user.
 */
public class UserActions {

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
		adBuilder.setPositiveButton(activity.getString(R.string.button_cancel),
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		}); 
		adBuilder.setNegativeButton(activity.getString(R.string.button_search),
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
}
