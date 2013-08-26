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
import android.widget.Toast;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import src.stracker.asynchttp.MyRunnable;
import src.stracker.asynchttp.UserRequests;
import src.stracker.model.User;
import src.stracker.model.UserSynopse;

/**
 * @author diogomatos
 * This activity represents an user information
 */
@ContentView(R.layout.activity_user)
public class UserActivity extends BaseActivity {
 
	@InjectView(R.id.user_name)     		TextView       _userName;
	@InjectView(R.id.user_photo_id) 		SmartImageView _userPhoto;
	@InjectView(R.id.user_email)    		TextView       _userEmail;
	@InjectView(R.id.subscriptions_layout2) LinearLayout   _subscriptionsLayout;
	@InjectView(R.id.friends_layout2)       LinearLayout   _friendsLayout;
	@InjectView(R.id.subscriptions_count2)  TextView       _subscriptionsCount;
	@InjectView(R.id.friend_count2)         TextView       _friendCount;
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
				Toast.makeText(UserActivity.this, R.string.error_user_req, Toast.LENGTH_SHORT).show();
			}
			@Override
			public <T> void runWithArgument(T response) {
				_user = (User) response;
				setuserInformation();
			}
		}, uri);
		//Create clickable listeners
		// - Subscriptions
		_subscriptionsLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(UserActivity.this, UserSubscriptionsActivity.class);
				intent.putExtra("list", _user.getSubscriptions());
				startActivity(intent);
			}
		});
		// - Friends
		_friendsLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(UserActivity.this, UserFriendsActivity.class); 
				intent.putExtra("list", _user.getFriends());
				startActivity(intent);
			}
		});
	}
	
	/**
	 * This method is used to affect the user information
	 */
	private void setuserInformation(){
		_userName.setText(getString(R.string.user_name) + _user.getName());
		_userPhoto.setImageUrl(_user.getPhotoUrl());
		_userEmail.setText(getString(R.string.user_email) + _user.getEmail());
		_friendCount.setText(_user.getFriends().size()+"");
		_subscriptionsCount.setText(_user.getSubscriptions().size()+"");
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
				UserRequests.postFriendRequest(this, new MyRunnable() {
					@Override
					public void run() {
						Toast.makeText(UserActivity.this, R.string.error_friend_req, Toast.LENGTH_SHORT).show();
					}
					@Override
					public <T> void runWithArgument(T response) {
						Toast.makeText(UserActivity.this, R.string.success_friend_req, Toast.LENGTH_SHORT).show();
						finish();
					}
				}, _user.getId());
				break;
			case R.id.action_del_user: 
				UserRequests.deleteFriend(this, new MyRunnable() {
					@Override
					public void run() {
						Toast.makeText(UserActivity.this, R.string.error_friend_del, Toast.LENGTH_SHORT).show();
					}
					@Override
					public <T> void runWithArgument(T response) {
						Toast.makeText(UserActivity.this, R.string.friend_removed, Toast.LENGTH_SHORT).show();
						finish();
					}
				}, _user.getId());
				break;
    	}
    	return true;
    }
	
	/**
	 * @see android.app.Activity#onPrepareOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onPrepareOptionsMenu (Menu menu) {
	    //if the user already is a friend disable friend request option
		for(UserSynopse user : _application.getFbUser().getFriends()){
	    	if(user.getId().equals(_user.getId())){
	    		menu.findItem(R.id.action_add_user).setVisible(false);
	    		break;
	    	}
		}
		//if the friend request is visible, remove friend is disabled
		if(menu.findItem(R.id.action_add_user).isVisible())
			menu.findItem(R.id.action_del_user).setVisible(false);
	    return true;
	}
}
