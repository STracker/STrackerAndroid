package src.stracker;

import android.os.Bundle;
import roboguice.inject.ContentView;
import src.stracker.adapters.UserAdapter;

/**
 * @author diogomatos
 * This class represents the list of my friends.
 */
@ContentView(R.layout.activity_list)
public class MyFriendsActivity extends BaseFriendActivity {

	/**
	 * @see src.stracker.BaseListActivity#onCreate(android.os.Bundle)
	 */
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		_users = _application.getFbUser().getFriends();
		_adapter = new UserAdapter(this, _users);
		_listView.setAdapter(_adapter);
		_activity = this;
	}   
}
