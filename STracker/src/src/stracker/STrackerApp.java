package src.stracker;

import android.app.Application;

/**
 * This class represents the application. It's needed to keep the global application state.
 */
public class STrackerApp extends Application {
	
	private final String _ApiURL = "http://stracker.apphb.com/api/";
	private final String _URL = "http://stracker.apphb.com/";
	
	public String getApiURL() {
		return _ApiURL;
	}
	
	public String getURL() {
		return _URL;
	}
}
