package src.stracker;

import java.util.ArrayList;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

	private int rangeStart;
	private int rangeEnd;
	
	/**
	 * @see src.stracker.BaseListActivity#onCreate(android.os.Bundle)
	 */
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		_activity = this;
		rangeStart = Integer.parseInt(getString(R.string.range_begin));
		rangeEnd = Integer.parseInt(getString(R.string.range_end));
		makeRequest();
	}
	
	/**
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu); 
		return true;
	}

	/**
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId()){
			case R.id.action_more_results:
				int interval = Integer.parseInt(getString(R.string.range_interval));
				rangeStart = rangeEnd + 1;
				rangeEnd = rangeStart + interval;
				makeRequest();
				break;  
		}
		return true;
	}
	
	/**
	 * Auxiliary method to make the request for television shows
	 */
	private void makeRequest(){
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
					if(synopse.getId().equals(_application.getUserManager().get(SearchFriendActivity.this).getId())){
						_users.remove(synopse);
						break;
					}
				}
				_adapter = new UserAdapter(SearchFriendActivity.this, _users);
				_listView.setAdapter(_adapter);
			}
		}, getIntent().getStringExtra(NAME_PARAM), rangeStart, rangeEnd);
	}
}
