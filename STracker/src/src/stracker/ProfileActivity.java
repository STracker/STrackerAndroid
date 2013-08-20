package src.stracker;

import java.util.HashMap;
import com.loopj.android.image.SmartImageView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import src.stracker.asynchttp.DummyRequest;
import src.stracker.asynchttp.MyRunnable;
import src.stracker.asynchttp.UserRequest;
import src.stracker.model.User;

/**
 * This Activity represents an user profile information.
 * @author diogomatos
 */
@ContentView(R.layout.activity_profile)
public class ProfileActivity extends BaseActivity {

	@InjectView(R.id.profile_name) TextView _profileName;
	@InjectView(R.id.profile_photo_id) SmartImageView _profilePhoto;
	@InjectView(R.id.profile_email) TextView _profileEmail;
	private User _user;
	
	/**
	 * @see roboguice.activity.RoboActivity#onCreate(android.os.Bundle)
	 */
	@Override  
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(getString(R.string.profile_title));
		String uri = getIntent().getStringExtra("uri");
		if(uri == null){
			_user = _application.getFbUser();
			setProfileInformation();
		}
		else {
			new UserRequest(this, new MyRunnable() {
				@Override
				public void run() {
					Toast.makeText(ProfileActivity.this, R.string.error_profile, Toast.LENGTH_SHORT).show();
				}
				
				@Override
				public <T> void runWithArgument(T response) {
					_user = (User) response;
					setProfileInformation();
				}
			}).authorizedGet(uri);
		}
	}
	
	/**
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);   
        return true;
    }
	
	/**
	 * This method defines the callback's when a button of the menu is pressed
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	switch(item.getItemId()){
    	case R.id.action_add_user:
    		HashMap<String, String> params = new HashMap<String, String>();
    		params.put("",_user.getId());
    		new DummyRequest(this, new MyRunnable() {
				@Override
				public void run() {
					Toast.makeText(ProfileActivity.this, R.string.error_friend_req, Toast.LENGTH_SHORT).show();
				}
				
				@Override
				public <T> void runWithArgument(T response) {
					Toast.makeText(ProfileActivity.this, R.string.success_friend_req, Toast.LENGTH_SHORT).show();
				}
			}).authorizedPost(getString(R.string.uri_user_friends), params);
    		break;
    	}
    	return true;
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
