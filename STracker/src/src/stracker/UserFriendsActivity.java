package src.stracker;

import android.os.Bundle;
import roboguice.inject.ContentView;
import src.stracker.adapters.UserAdapter;
import src.stracker.model.UserSynopse;

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
		_users = getIntent().getParcelableArrayListExtra(LIST_PARAM);
		//if i am a friend, remove my entry from that list
		for(UserSynopse synopse : _users){
			if(synopse.getId().equals(_application.getUserManager().get(this).getId())){
				_users.remove(synopse);
				break;
			}
		}
		_adapter = new UserAdapter(this, _users);
		_listView.setAdapter(_adapter); 
		_activity = this;
	}
}
