package src.stracker;

import com.loopj.android.image.SmartImageView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
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
	 * @see src.stracker.BaseActivity#onCreate(android.os.Bundle)
	 */
	@Override  
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		_user = _application.getFbUser();
		setProfileInformation();
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
		case R.id.action_series:
			startActivity(new Intent(this, SubscriptionsActivity.class));
			break;
		case R.id.action_friends:
			startActivity(new Intent(this, FriendsActivity.class));
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
