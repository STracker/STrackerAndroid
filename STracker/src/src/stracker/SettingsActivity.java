package src.stracker;

import src.stracker.service.UpdaterManager;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

/**
 * @author diogomatos
 * This activity represents the user configurations to automatic updates
 */
public class SettingsActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener{

	private static final String CHECKBOX_KEY = "pref_check";
	private static final String DELAY_KEY = "pref_delay";
	private STrackerApp _application;
	
	/**
	 * @see android.preference.PreferenceActivity#onCreate(android.os.Bundle)
	 */
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle bundle){
		super.onCreate(bundle);
		setTitle(R.string.title_preferences);
		addPreferencesFromResource(R.xml.settings);
		_application = (STrackerApp) getApplication();
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		preferences.registerOnSharedPreferenceChangeListener(this);
	}

	/**
	 * @see android.content.SharedPreferences.OnSharedPreferenceChangeListener#onSharedPreferenceChanged(android.content.SharedPreferences, java.lang.String)
	 */
	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		if(((key.equals(CHECKBOX_KEY) || key.equals(DELAY_KEY)) && sharedPreferences.getBoolean(CHECKBOX_KEY, false))){
			_application.getUpdaterManager().setAlarmManagerDelay(Integer.parseInt(sharedPreferences.getString(DELAY_KEY, UpdaterManager.DEFAULT_DELAY+"")));
		} else{
			_application.getUpdaterManager().cancelAlarmManager();
		}
	}
}
