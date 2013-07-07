package src.stracker.utils;

import src.stracker.FbLoginActivity;
import src.stracker.STrackerApp;
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
	public static void initSearchByName(final Activity activity, final STrackerApp app){
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
				String url = app.getApiURL()+"tvshows?name="+input.getText();
				new SearchByNameRequest(activity).execute(url.replaceAll(" ", "+"));
			}
		});
		AlertDialog alertDialog = adBuilder.create();
		alertDialog.show();
	}
	
	public static boolean checkLogin(Activity activity, STrackerApp app){
		if(!app.isLoggedIn())
			activity.startActivity(new Intent(activity,FbLoginActivity.class));
		return app.isLoggedIn();
	}
	
	public static boolean checkConectivity(Activity activity, STrackerApp app){
		if(!app.verifyInternetConnection()){
			Toast.makeText(activity, "No internet connection!", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}
}
