package src.stracker;

import java.util.ArrayList;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import roboguice.inject.ContentView;
import src.stracker.adapters.UserAdapter;
import src.stracker.model.UserSynopse;

@ContentView(R.layout.activity_list)
public class FriendsActivity extends BaseListActivity {

	private ArrayList<UserSynopse> _users;
	private UserAdapter _adapter;
	
	/**
	 * @see src.stracker.BaseListActivity#onCreate(android.os.Bundle)
	 */
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		_users = _application.getFbUser().getFriends();
		_adapter = new UserAdapter(FriendsActivity.this, _users);
		_listView.setAdapter(_adapter);
	}

	/**
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> adapt, View view, int position, long id) {
		UserSynopse user = _users.get(position);
		Intent intent = new Intent(this, ProfileActivity.class);
		intent.putExtra("uri", user.getUri());
		startActivity(intent);
	}
}
