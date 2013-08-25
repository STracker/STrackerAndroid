package src.stracker;

import android.os.Bundle;
import roboguice.inject.ContentView;
import src.stracker.adapters.UserAdapter;

/**
 * @author diogomatos
 * This class represents a list of user friends 
 */
@ContentView(R.layout.activity_list)
public class UserFriendsActivity extends BaseFriendActivity{

	/**
	 * @see src.stracker.BaseListActivity#onCreate(android.os.Bundle)
	 */
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		_users = getIntent().getParcelableArrayListExtra("list");
		_adapter = new UserAdapter(this, _users);
		_listView.setAdapter(_adapter);
	}
}
