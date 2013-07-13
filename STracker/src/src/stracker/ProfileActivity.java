package src.stracker;

import com.loopj.android.image.SmartImageView;

import android.os.Bundle;
import android.widget.TextView;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import src.stracker.model.FBUser;

@ContentView(R.layout.activity_profile)
public class ProfileActivity extends RoboActivity {

	@InjectView(R.id.profile_name) TextView _profileName;
	@InjectView(R.id.profile_photo_id) SmartImageView _profilePhoto;
	@InjectView(R.id.profile_email) TextView _profileEmail;
	private FBUser _user;
	
	@Override  
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setTitle("Profile");
		_user = getIntent().getParcelableExtra("user");
		_profileName.setText("Name: " + _user.getName());
		_profilePhoto.setImageUrl(_user.getPhotoUrl());
		_profileEmail.setText("Email: " + _user.getEmail());
	}
}
