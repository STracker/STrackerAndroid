package src.stracker;

import roboguice.inject.ContentView;
import src.stracker.adapters.UserAdapter;

/**
 * @author diogomatos
 * This class represents the list of my friends.
 */
@ContentView(R.layout.activity_list)
public class MyFriendsActivity extends BaseFriendActivity {

	/**
	 * @see roboguice.activity.RoboActivity#onResume()
	 */
	@Override
	public void onResume(){
		super.onResume();
		_users = _application.getUserManager().get(this).getFriends();
		_adapter = new UserAdapter(this, _users);
		_listView.setAdapter(_adapter);
		_activity = this;
	}
}
