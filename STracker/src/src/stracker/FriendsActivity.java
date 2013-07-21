package src.stracker;

import java.util.ArrayList;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import roboguice.activity.RoboListActivity;
import roboguice.inject.ContentView;
import src.stracker.adapters.UserAdapter;
import src.stracker.asynchttp.UserRequest;
import src.stracker.model.UserSynopse;

@ContentView(R.layout.activity_list)
public class FriendsActivity extends RoboListActivity {

	private ArrayList<UserSynopse> _users;
	private UserAdapter _adapter;
	private STrackerApp _app;
	
	/**
	 * (non-Javadoc)
	 * @see roboguice.activity.RoboListActivity#onCreate(android.os.Bundle)
	 */
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		_app = (STrackerApp) getApplication();
		_users = getIntent().getParcelableArrayListExtra("list");
		_adapter = new UserAdapter(this, _users);
		setListAdapter(_adapter);
	}
	
	/**
	 * (non-Javadoc)
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		UserSynopse user = _users.get(position);
		new UserRequest(this).authorizedGet(getString(R.string.uri_host_api)+user.getUri(),_app);
	}
}
