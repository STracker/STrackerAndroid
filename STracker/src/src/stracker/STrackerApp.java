package src.stracker;

import src.stracker.service.UpdaterManager;
import src.stracker.user_info.UserManager;
import HawkClient.HawkCredentials;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * This class represents the application. It's needed to keep the global application state.
 */
public class STrackerApp extends Application {
	
	private boolean         _connectivity;
	private HawkCredentials _credentials;
	private UserManager     _userManager;
	private UpdaterManager  _updater;
	
	/**
	 * @see android.app.Application#onCreate()
	 */
	@Override
	public void onCreate(){
		_userManager = new UserManager(this);
		_updater = new UpdaterManager(this);
		verifyConnection();
	}
	
	/**
	 * This method returns the updater manager.
	 * @return Updater Manager
	 */
	public UpdaterManager getUpdaterManager(){
		return _updater;
	}
	
	/**
	 * This method is used to get the user manager object
	 * @param preferences - shared preferences of the application
	 * @return user manager
	 */
	public UserManager getUserManager(){
		return _userManager;
	}

	/**
	 * This method returns if the device is connected to the Internet
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
	
	/**
	 * Auxiliary method for verify the connection type.
	 * @param boolean
	 */
	private void verifyConnection() {
		ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		//No connectivity at all
		_connectivity = (info != null);
	}
}
