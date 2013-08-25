package src.stracker;

import com.google.inject.Inject;
import android.os.Bundle;
import roboguice.activity.RoboActivity;
import src.stracker.utils.ShakeDetector;

/**
 * @author diogomatos
 * This activity is a base activity where all STracker activities must extend.
 */
public class BaseActivity extends RoboActivity {
	
	protected STrackerApp _application;
	protected @Inject ShakeDetector _shakeDetector;
	
	/**
	 * @see roboguice.activity.RoboListActivity#onCreate(android.os.Bundle)
	 */
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		_application = (STrackerApp) getApplication();
	}	
}
