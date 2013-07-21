package src.stracker;

import src.stracker.model.User;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * This class represents the application. It's needed to keep the global application state.
 */
public class STrackerApp extends Application {
	
	private User _fbUser;
	
	public void setFbUser(User user){
		if(_fbUser == null)
			_fbUser = user;
	}
	
	public User getFbUser(){
		return _fbUser;
	}

	public void logout(){
		setFbUser(null);
	}
	
	public boolean isLoggedIn(){
		return _fbUser != null;
	}
	
	/**
	 * Auxiliary method for verify the connection type.
	 */
	public boolean verifyInternetConnection() {
		ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		return (info != null);
	}
}
