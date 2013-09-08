package src.stracker;

import com.google.inject.Inject;
import android.os.Bundle;
import android.widget.Toast;
import roboguice.activity.RoboActivity;
import src.stracker.utils.ShakeDetector;

/**
 * @author diogomatos
 * This activity is a base activity where all STracker activities must extend.
 */
public class BaseActivity extends RoboActivity {
	
	protected STrackerApp _application;
	protected @Inject ShakeDetector _shakeDetector;
	
	//Constants used to identify intent extras and other utilities
	public static final String URI_PARAM 		    = "uri";
	public static final String TVSHOW_URI_PARAM 	= "tvShowUri";
	public static final String TVSHOW_ID_PARAM      = "tvShowId";
	public static final String LIST_PARAM 		    = "list";
	public static final String NAME_PARAM           = "name";
	public static final String COMMENT_PARAM 	    = "comment";
	public static final String SEASON_NUMBER_PARAM  = "seasonNumber";
	public static final String EPISODE_NUMBER_PARAM = "episodeNumber";
	public static final String EMPTY_STRING         = "";
	public static final String DATE_MASK 		    = "yyyy-MM-dd";
	
	/**
	 * @see roboguice.activity.RoboListActivity#onCreate(android.os.Bundle)
	 */
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		_application = (STrackerApp) getApplication();
		if(!_application.isConnected())
			Toast.makeText(this, R.string.no_connectivity, Toast.LENGTH_SHORT).show();
	}	
}
