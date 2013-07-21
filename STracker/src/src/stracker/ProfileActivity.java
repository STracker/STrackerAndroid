package src.stracker;

import java.util.HashMap;

import com.loopj.android.image.SmartImageView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import src.stracker.asynchttp.DummyRequest;
import src.stracker.model.User;

@ContentView(R.layout.activity_profile)
public class ProfileActivity extends RoboActivity {

	@InjectView(R.id.profile_name) TextView _profileName;
	@InjectView(R.id.profile_photo_id) SmartImageView _profilePhoto;
	@InjectView(R.id.profile_email) TextView _profileEmail;
	private User _user;
	private STrackerApp _app;
	
	/**
	 * (non-Javadoc)
	 * @see roboguice.activity.RoboActivity#onCreate(android.os.Bundle)
	 */
	@Override  
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setTitle("Profile");
		_app = (STrackerApp) getApplication();
		_user = getIntent().getParcelableExtra("user");
		_profileName.setText("Name: " + _user.getName());
		_profilePhoto.setImageUrl(_user.getPhotoUrl());
		_profileEmail.setText("Email: " + _user.getEmail());
	}
	
	/**
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);   
        return true;
    }
	
	/**
	 * (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 * This method defines the callback's when a button of the menu is pressed
	 */
	@Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	switch(item.getItemId()){
    	case R.id.action_add_user:
    		HashMap<String, String> params = new HashMap<String, String>();
    		params.put("",_user.getId());
    		new DummyRequest(this).authorizedPost(getString(R.string.uri_host_api)+getString(R.string.uri_user_friends), _app, params);
    		break;
    	}
    	return true;
    }
}
