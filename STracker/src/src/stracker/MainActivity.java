package src.stracker;

import java.util.ArrayList;
import src.stracker.adapters.TvShowSynopseAdapter;
import src.stracker.asynchttp.MyRunnable;
import src.stracker.asynchttp.TopRatedRequest;
import src.stracker.model.TvShowSynopse;
import src.stracker.utils.Utils;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

/**
 * This Activity is the initial activity of STracker Android application.
 * @author diogomatos
 */
public class MainActivity extends ListActivity {

	private STrackerApp _app;
	private ArrayList<TvShowSynopse> _elems;
	
	/**
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override  
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_main);
		_app = (STrackerApp) getApplication();	
	} 
	
	/**
	 * @see android.app.Activity#onResume()
	 */
	@Override
	public void onResume(){
		super.onResume();
		//Request top rated shows
		new TopRatedRequest(this, new MyRunnable() {
			@Override
			public void run() 
			{
				Toast.makeText(MainActivity.this, R.string.not_found, Toast.LENGTH_SHORT).show(); 
			}
			
			@Override
			public <T> void runWithArgument(T response) 
			{
				_elems = (ArrayList<TvShowSynopse>) response;
				TvShowSynopseAdapter adapter = new TvShowSynopseAdapter(MainActivity.this, _elems);
				setListAdapter(adapter);
			}
		}).get(getString(R.string.uri_tvshow_toprated));
	}
	 
	/**
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 * @param menu - reference to activity menu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu); 
		return true;
	}

	/**
	 * This method defines the callback's when a button of the menu is pressed
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 * @param item - reference to pressed menu item
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId()){
			case R.id.action_profile:
				Intent intentProfile = new Intent(this,ProfileActivity.class);
				intentProfile.putExtra("user", _app.getFbUser());
				startActivity(intentProfile);
				break;
			case R.id.action_series:
				startActivity(new Intent(this, SubscriptionsActivity.class));
				break;
			case R.id.action_friends:
				startActivity(new Intent(this, FriendsActivity.class));
				break;
			case R.id.form_friend:
				Utils.initSearchFriend(this);
				break;
			case R.id.form_genre:
				startActivity(new Intent(this, GenreSynopsisActivity.class));
				break;
			case R.id.form_name:
				Utils.initSearchByName(this);
				break;
			case R.id.action_calendar:
				startActivity(new Intent(this, CalendarActivity.class));
				break;
		}
		return true;
	}

	/**
	 * When a genre of the list is pressed, create a request to get all tv shows from that genre
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 * @param l - listview 
	 * @param v - view
	 * @param position - position in list of the triggered item
	 * @param id - identifier
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) 
	{
		Intent intent = new Intent(this,TvShowActivity.class);
		intent.putExtra("tvShowUri", _elems.get(position).getUri());
		startActivity(intent);
	}
}
