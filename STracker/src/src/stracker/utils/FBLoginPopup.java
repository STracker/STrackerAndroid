package src.stracker.utils;

import com.facebook.widget.LoginButton;

import src.stracker.R;
import src.stracker.STrackerApp;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

public class FBLoginPopup {
	
    public static void createLogin(final Context context) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.fb_login);
        final LoginButton loginBtn = new LoginButton(context);
        loginBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				STrackerApp app = (STrackerApp) ((Activity) context).getApplication();
				app.logout();
			}
		});
        builder.setView(loginBtn);
        // Create the AlertDialog object and return it
        AlertDialog alertDialog = builder.create();
		alertDialog.show();
    }
}
