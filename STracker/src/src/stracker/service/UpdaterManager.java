package src.stracker.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class UpdaterManager {

	private AlarmManager _manager;
	private PendingIntent _operation;
	public static final int DEFAULT_DELAY = 1;
	
	public UpdaterManager(Context context){
		_manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		_operation = PendingIntent.getService(context, 0, new Intent(context, UserInfoService.class), 0);
	}
	
	public void setAlarmManagerDelay(int delay){ // 
		_manager.setRepeating(AlarmManager.RTC, 0, delay * 1000 * 60, _operation);
	}
	
	public void cancelAlarmManager(){
		_manager.cancel(_operation);
	}	
}
