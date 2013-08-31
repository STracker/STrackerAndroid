package src.stracker;

import com.loopj.android.image.SmartImageView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import roboguice.event.Observes;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import src.stracker.asynchttp.MyRunnable;
import src.stracker.asynchttp.UserRequests;
import src.stracker.model.User;
import src.stracker.utils.ShakeDetector;

/**
 * This Activity represents an user profile information.
 * @author diogomatos 
 */ 
@ContentView(R.layout.activity_profile) 
public class ProfileActivity extends BaseActivity {

	@InjectView(R.id.profile_name)          TextView       _profileName;
	@InjectView(R.id.profile_photo_id)      SmartImageView _profilePhoto;
	@InjectView(R.id.profile_email)         TextView       _profileEmail;
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
	 * This method is used to affect the profile information
	 */
	private void setProfileInformation(){
		_profileName        .setText(getString(R.string.profile_name) + _application.getFbUser().getName());
		_profilePhoto       .setImageUrl(_application.getFbUser().getPhotoUrl());
		_profileEmail       .setText(getString(R.string.profile_email) + _application.getFbUser().getEmail());
		_subscriptionsCount .setText(_application.getFbUser().getSubscriptions().size()+EMPTY_STRING);
		_friendRequestsCount.setText(_application.getFbUser().getFriendRequests().size()+EMPTY_STRING);
		_suggestionsCount   .setText(_application.getFbUser().getSuggestions().size()+EMPTY_STRING);
		_friendCount        .setText(_application.getFbUser().getFriends().size()+EMPTY_STRING);
	}
	
	/**
	 * Event associated to shake motion
	 * @param event - shake event
	 */
	public void handleShake(@Observes ShakeDetector.OnShakeEvent event) {
		UserRequests.getSelf(this, new MyRunnable() {
			@Override
			public void run() {
				Toast.makeText(ProfileActivity.this, R.string.error_user_req, Toast.LENGTH_SHORT).show();
			}
			@Override
			public <T> void runWithArgument(T response) {
				_application.setFbUser((User) response);
				setProfileInformation();
			}
		}, _application.getFbUser().getId());
	}
}
