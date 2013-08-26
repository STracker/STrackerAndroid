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
	
	/**
	 * This method set the user field
	 * @param user - represents the user
	 */
	public void setFbUser(User user){
		_fbUser = user;
	}
	
	/**
	 * This method gets the user
	 * @return user
	 */
	public User getFbUser(){
		return _fbUser;
	}

	/**
	 * This method clear the user information
	 */
	public void logout(){
		setFbUser(null);
	}
	
	/**
	 * This method returns if an user is logged in or not
	 * @return boolean
	 */
	public boolean isLoggedIn(){
		return _fbUser != null;
	}

	/**
	 * This method returns if the device is connected to the internet
	 * @return boolean
	 */
	public boolean isConnected() {
		return _connectivity;
	}

	/**
	 * This method set the information of the connectivity flag
	 * @param connected - flag of the connectivity
	 */
	public void setConnectivity(boolean connected) {
		_connectivity = connected;
	}
	
	/**
	 * This method creates the hawk credentials object
	 * @param id - user identifier
	 */
	public void createHawkCreadentials(String id){
		_credentials = new HawkCredentials(id, getString(R.string.hawk_key));
	}
	
	/**
	 * This method returns the hawk credentials
	 * @return credentials
	 */
	public HawkCredentials getHawkCredentials(){
		return _credentials;
	}
}
