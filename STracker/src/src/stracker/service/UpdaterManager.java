package src.stracker.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * @author diogomatos
 * This class represents a manager to handle the AlarmManager to user information service.
 */
public class UpdaterManager {

	private AlarmManager _manager;
	private PendingIntent _operation;
	public static final int DEFAULT_DELAY = 1;
	
	/**
	 * Updater Manager constructor.
	 */
	public UpdaterManager(Context context){
		_manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		_operation = PendingIntent.getService(context, 0, new Intent(context, UserInfoService.class), 0);
	}
	
	/**
	 * Method to change the delay between updates
	 * @param delay - delay in minutes
	 */
	public void setAlarmManagerDelay(int delay){ // 
		_manager.setRepeating(AlarmManager.RTC, 0, delay * 1000 * 60, _operation);
	}
	
	/**
	 * Method to cancel the update service
	 */
	public void cancelAlarmManager(){
		_manager.cancel(_operation);
	}	
}
