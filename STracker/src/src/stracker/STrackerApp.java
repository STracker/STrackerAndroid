package src.stracker;

import src.stracker.model.User;
import HawkClient.HawkCredentials;
import android.app.Application;

/**
 * This class represents the application. It's needed to keep the global application state.
 */
public class STrackerApp extends Application {
	
	private User _fbUser;
	private boolean _connectivity;
	private HawkCredentials _credentials;
	
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

	public boolean isConnected() {
		return _connectivity;
	}

	public void setConnectivity(boolean connected) {
		_connectivity = connected;
	}
	
	public void createHawkCreadentials(String id){
		_credentials = new HawkCredentials(id, getString(R.string.hawk_key));
	}
	
	public HawkCredentials getHawkCredentials(){
		return _credentials;
	}
}
