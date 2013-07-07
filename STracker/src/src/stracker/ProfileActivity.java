package src.stracker;

import com.loopj.android.image.SmartImageView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_profile)
public class ProfileActivity extends RoboActivity {

	@InjectView(R.id.profile_name) TextView _profileName;
	@InjectView(R.id.profile_photo_id) SmartImageView _profilePhoto;
	@InjectView(R.id.profile_email) TextView _profileEmail;
	private STrackerApp _app;
	
	@Override  
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		_app = (STrackerApp) getApplication();
		setTitle("Profile");
		Log.d("DEBUG", "STrackerApp User is null? Ans: " + (_app.getFbUser() == null));
		_profileName.setText("Name: " + _app.getFbUser().getName());
		_profilePhoto.setImageUrl(_app.getFbUser().getPhotoUrl());
		_profileEmail.setText("Email: " + _app.getFbUser().getEmail());
	}
}
