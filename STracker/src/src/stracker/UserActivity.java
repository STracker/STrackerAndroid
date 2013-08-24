package src.stracker;

import com.loopj.android.image.SmartImageView;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import src.stracker.asynchttp.MyRunnable;
import src.stracker.asynchttp.UserRequests;
import src.stracker.model.User;

@ContentView(R.layout.activity_profile)
public class UserActivity extends BaseActivity {

	@InjectView(R.id.profile_name) TextView _profileName;
	@InjectView(R.id.profile_photo_id) SmartImageView _profilePhoto;
	@InjectView(R.id.profile_email) TextView _profileEmail;
	private User _user;
	
	/**
	 * @see src.stracker.BaseActivity#onCreate(android.os.Bundle)
	 */
	@Override  
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(getString(R.string.profile_title));
		String uri = getIntent().getStringExtra("uri");
		
		UserRequests.getUser(this, new MyRunnable() {
			@Override
			public void run() {
				Toast.makeText(UserActivity.this, R.string.error_profile, Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public <T> void runWithArgument(T response) {
				_user = (User) response;
				setProfileInformation();
			}
		}, uri);
	}
	
	/**
	 * This method is used to affect the profile information
	 */
	private void setProfileInformation(){
		_profileName.setText(getString(R.string.profile_name) + _user.getName());
		_profilePhoto.setImageUrl(_user.getPhotoUrl());
		_profileEmail.setText(getString(R.string.profile_email) + _user.getEmail());
	}
}
