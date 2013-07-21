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
	
	private final String _ApiURL = "http://strackerserverdev.apphb.com/api/";
	private final String _URL = "http://strackerserverdev.apphb.com/";
	private final String _HawkKey = "werxhqb98rpaxn39848xrunpaw3489ruxnpa98w4rxn";
	private User _fbUser;
	
	public String getApiURL() {
		return _ApiURL;
	}
	
	public String getURL() {
		return _URL;
	}
	
	public void setFbUser(User user){
		if(_fbUser == null)
			_fbUser = user;
	}
	
	public User getFbUser(){
		return _fbUser;
	}
	
	public String getHawkKey(){
		return _HawkKey;
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
