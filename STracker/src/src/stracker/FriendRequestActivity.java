package src.stracker;

import java.util.ArrayList;
import roboguice.inject.ContentView;
import src.stracker.adapters.FriendReqAdapter;
import src.stracker.model.UserSynopse;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

/**
 * @author diogomatos
 * This activity represents a list of friend requests
 */
@ContentView(R.layout.activity_list)
public class FriendRequestActivity extends BaseListActivity {

	private ArrayList<UserSynopse> _requests;
	private FriendReqAdapter _adapter;
	
	/**
	 * @see src.stracker.BaseListActivity#onCreate(android.os.Bundle)
	 */
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setTitle(getString(R.string.profile_friendrequests));
		_requests = _application.getFbUser().getFriendRequests();
		_adapter = new FriendReqAdapter(this, _requests);
		_listView.setAdapter(_adapter);
	}
	
	/**
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> adapt, View view, int position, long id) { }
}
