package src.stracker;

import java.util.ArrayList;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import roboguice.activity.RoboListActivity;
import roboguice.inject.ContentView;
import src.stracker.adapters.UserAdapter;
import src.stracker.asynchttp.FriendsRequest;
import src.stracker.asynchttp.MyRunnable;
import src.stracker.model.UserSynopse;

@ContentView(R.layout.activity_list)
public class FriendsActivity extends RoboListActivity {

	private ArrayList<UserSynopse> _users;
	private UserAdapter _adapter;
	
	/**
	 * @see roboguice.activity.RoboListActivity#onCreate(android.os.Bundle)
	 */
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		new FriendsRequest(this, new MyRunnable() {
			@Override
			public void run() {
				Toast.makeText(FriendsActivity.this, R.string.error_friends, Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public <T> void runWithArgument(T response) {
				_users = (ArrayList<UserSynopse>) response;
				_adapter = new UserAdapter(FriendsActivity.this, _users);
				setListAdapter(_adapter);
			}
		}).authorizedGet(getString(R.string.uri_user_friends));
	}
	
	/**
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		UserSynopse user = _users.get(position);
		Intent intent = new Intent(this, ProfileActivity.class);
		intent.putExtra("uri", user.getUri());
		startActivity(intent);
	}
}
