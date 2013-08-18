package src.stracker;

import src.stracker.model.User;
import android.app.Application;

/**
 * This class represents the application. It's needed to keep the global application state.
 */
public class STrackerApp extends Application {
	
	private User _fbUser;
	private boolean _Connectivity;
	
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

	public boolean is_Connected() {
		return _Connectivity;
	}

	public void set_Connectivity(boolean connected) {
		_Connectivity = connected;
	}
}
