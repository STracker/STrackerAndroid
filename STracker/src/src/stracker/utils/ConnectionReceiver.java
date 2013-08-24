package src.stracker.utils;

import src.stracker.STrackerApp;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class ConnectionReceiver extends BroadcastReceiver {
	
	@Override
	public void onReceive(Context context, Intent intent ){
		STrackerApp app = (STrackerApp) context.getApplicationContext();
		app.setConnectivity(!intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false));
		if(!app.isConnected())
			Toast.makeText(context, "Lost internet connection", Toast.LENGTH_SHORT).show();
	}
}
