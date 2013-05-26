package src.stracker;

import android.app.Application;

public class STrackerApp extends Application {
	
	private final String _URL = "http://strackerserverdev.apphb.com/api/";
  
	public String getURL() {
		return _URL;
	}
}
