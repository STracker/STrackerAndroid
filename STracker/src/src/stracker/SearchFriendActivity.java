package src.stracker;

import java.util.ArrayList;
import android.os.Bundle;
import android.widget.Toast;
import roboguice.inject.ContentView;
import src.stracker.adapters.UserAdapter;
import src.stracker.asynchttp.MyRunnable;
import src.stracker.asynchttp.UserRequests;
import src.stracker.model.UserSynopse;

/**
 * @author diogomatos
 * This class represents a list of users 
 */
@ContentView(R.layout.activity_list)
public class SearchFriendActivity extends BaseFriendActivity {

	/**
	 * @see src.stracker.BaseListActivity#onCreate(android.os.Bundle)
	 */
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		_activity = this;
		UserRequests.getSearchFriends(this, new MyRunnable() {
			@Override
			public void run() {
				Toast.makeText(SearchFriendActivity.this, R.string.error_search_friend, Toast.LENGTH_SHORT).show();
			}
			@SuppressWarnings("unchecked")
			@Override
			public <T> void runWithArgument(T response) {
				_users = (ArrayList<UserSynopse>) response;
				//if i am a result, remove my entry from that list
				for(UserSynopse synopse : _users){
					if(synopse.getId().equals(_application.getFbUser().getId())){
						_users.remove(synopse);
						break;
					}
				}
				_adapter = new UserAdapter(SearchFriendActivity.this, _users);
				_listView.setAdapter(_adapter);
			}
		}, getIntent().getStringExtra(NAME_PARAM));
	}
}
