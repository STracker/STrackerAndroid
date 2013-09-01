package src.stracker;

import com.loopj.android.image.SmartImageView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import roboguice.event.Observes;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import src.stracker.utils.ShakeDetector;

/**
 * This Activity represents an user profile information.
 * @author diogomatos 
 */ 
@ContentView(R.layout.activity_profile) 
public class ProfileActivity extends BaseActivity {

	@InjectView(R.id.profile_name)          TextView       _profileName;
	@InjectView(R.id.profile_photo_id)      SmartImageView _profilePhoto;
	@InjectView(R.id.subscriptions_layout)  LinearLayout   _subscriptionsLayout;
	@InjectView(R.id.friends_layout)        LinearLayout   _friendsLayout;
	@InjectView(R.id.suggestions_layout)    LinearLayout   _suggestionsLayout;
	@InjectView(R.id.friendrequests_layout) LinearLayout   _friendRequestsLayout;
	@InjectView(R.id.subscriptions_count)   TextView       _subscriptionsCount;
	@InjectView(R.id.friend_count)          TextView       _friendCount;
	@InjectView(R.id.suggestions_count)     TextView       _suggestionsCount; 
	@InjectView(R.id.friendrequest_count)   TextView       _friendRequestsCount;
	 
	/**
	 * @see src.stracker.BaseActivity#onCreate(android.os.Bundle)
	 */
	@Override  
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setProfileInformation();
		//Create clickable listeners
		// - Subscriptions
		_subscriptionsLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(ProfileActivity.this, MySubscriptionsActivity.class));
			}
		});
		// - Friends
		_friendsLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(ProfileActivity.this, MyFriendsActivity.class));
			}
		});
		// - Suggestions
		_suggestionsLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(ProfileActivity.this, SuggestionActivity.class));
			}
		});
		// - Friend Requests
		_friendRequestsLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(ProfileActivity.this, FriendRequestActivity.class));
			}
		});
	}
	
	/**
	 * @see roboguice.activity.RoboActivity#onResume()
	 */
	@Override
	public void onResume(){
		super.onResume();
		setProfileInformation();
	}
	
	/**
	 * This method is used to affect the profile information
	 */
	private void setProfileInformation(){
		_profileName        .setText(getString(R.string.profile_name) + _application.getUserManager().get(this).getName());
		_profilePhoto       .setImageUrl(_application.getUserManager().get(this).getPhotoUrl());
		_subscriptionsCount .setText(_application.getUserManager().get(this).getSubscriptions().size()+EMPTY_STRING);
		_friendRequestsCount.setText(_application.getUserManager().get(this).getFriendRequests().size()+EMPTY_STRING);
		_suggestionsCount   .setText(_application.getUserManager().get(this).getSuggestions().size()+EMPTY_STRING);
		_friendCount        .setText(_application.getUserManager().get(this).getFriends().size()+EMPTY_STRING);
	}
	
	/**
	 * Event associated to shake motion
	 * @param event - shake event
	 */
	public void handleShake(@Observes ShakeDetector.OnShakeEvent event) {
		_application.getUserManager().sync(this);
		setProfileInformation();
	}
	
	/**
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_profile, menu); 
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
			case R.id.action_logout:
				_application.getUserManager().delete();
				finish();
				break;
			case R.id.action_preferences:
				startActivity(new Intent(this, SettingsActivity.class));
				break;
    	}
    	return true;
    }
}
