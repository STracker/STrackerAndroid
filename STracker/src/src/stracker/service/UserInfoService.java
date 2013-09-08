package src.stracker.service;

import src.stracker.STrackerApp;
import src.stracker.user_info.UserManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * @author diogomatos
 * Service to update user profile information
 */
public class UserInfoService extends Service{
	
	/**
	 * @see android.app.Service#onStartCommand(android.content.Intent, int, int)
	 */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		//Save manager and old user 
		final UserManager manager = ((STrackerApp) getApplication()).getUserManager();
		//Only synchronize if user is logged in
		if(manager.isLogged())
			//Synchronize information and stop the service
			manager.sync(new Runnable() {
				@Override
				public void run() {
					//Stop the service	
					stopSelf();
				}
			});
		//Not Sticky means that the service will not start until the user open the application again
		//for example the Android needs memory and stop the services, this service will not start
		//when the resources are free
	    return START_NOT_STICKY;
	}

	/**
	 * @see android.app.Service#onBind(android.content.Intent)
	 */
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
}
