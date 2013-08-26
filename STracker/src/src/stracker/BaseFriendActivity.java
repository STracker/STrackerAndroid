package src.stracker;

import java.util.ArrayList;

import src.stracker.adapters.UserAdapter;
import src.stracker.model.UserSynopse;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

public class BaseFriendActivity extends BaseListActivity {

	protected ArrayList<UserSynopse> _users;
	protected UserAdapter _adapter;
	protected Activity _activity;
	
	/**
	 * @see src.stracker.BaseListActivity#onCreate(android.os.Bundle)
	 */
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setTitle(getString(R.string.profile_friends));
	}

	/**
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> adapt, View view, int position, long id) {
		UserSynopse user = _users.get(position);
		Intent intent = new Intent(_activity, UserActivity.class);
		intent.putExtra("uri", user.getUri());
		startActivity(intent);
	}
}
