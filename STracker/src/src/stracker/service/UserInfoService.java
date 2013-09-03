package src.stracker.service;

import src.stracker.STrackerApp;
import src.stracker.user_info.UserManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class UserInfoService extends Service{
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		UserManager manager = ((STrackerApp) getApplication()).getUserManager();
		manager.sync(new Runnable() {
			@Override
			public void run() {
				stopSelf();
			}
		});
		Log.d("updater", "toma");
	    return START_NOT_STICKY;
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
}
