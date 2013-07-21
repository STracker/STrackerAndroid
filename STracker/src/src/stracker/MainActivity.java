package src.stracker;

import java.util.ArrayList;
import src.stracker.adapters.TvShowSynopseAdapter;
import src.stracker.asynchttp.CalendarRequest;
import src.stracker.asynchttp.FriendsRequest;
import src.stracker.asynchttp.GenresRequest;
import src.stracker.asynchttp.SubscriptionsRequest;
import src.stracker.asynchttp.TopRatedRequest;
import src.stracker.asynchttp.TvShowRequest;
import src.stracker.model.TvShowSynopse;
import src.stracker.utils.Utils;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

/**
 * This Activity is the initial activity of STracker Android application.
 */
//@ContentView(R.layout.activity_main)
public class MainActivity extends ListActivity {

	private STrackerApp _app;
	private ArrayList<TvShowSynopse> _elems;

	/**
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override  
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_main);
		_app = (STrackerApp) getApplication();
		
		if(Utils.checkConectivity(this, _app)){
			//Request top rated shows
			new TopRatedRequest(this).get(getString(R.string.uri_host_api)+getString(R.string.uri_tvshow_toprated));
		}
	} 
	
	/**
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu); 
		return true;
	}

	/**
	 * (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 * This method defines the callback's when a button of the menu is pressed
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		if(Utils.checkConectivity(this, _app)){
			if (item.getItemId() == R.id.action_profile  ||
			    item.getItemId() == R.id.action_series   ||
			    item.getItemId() == R.id.action_friends  ||
			    item.getItemId() == R.id.action_messages ||
			    item.getItemId() == R.id.form_friend )
				if(!Utils.checkLogin(this, _app))
					return true;
			}
			switch(item.getItemId()){
				case R.id.action_profile:
					Utils.checkLogin(this, _app);
					Intent intentProfile = new Intent(this,ProfileActivity.class);
					intentProfile.putExtra("user", _app.getFbUser());
					startActivity(intentProfile);
					break;
				case R.id.action_series:
					new SubscriptionsRequest(this).authorizedGet(getString(R.string.uri_host_api) + getString(R.string.uri_user_subscriptions), _app);
					break;
				case R.id.action_messages:
					break;
				case R.id.action_friends:
					new FriendsRequest(this).authorizedGet(getString(R.string.uri_host_api)+getString(R.string.uri_user_friends), _app);
					break;
				case R.id.form_friend:
					Utils.initSearchFriend(this, _app);
					break;
				case R.id.form_genre:
					new GenresRequest(this).get(getString(R.string.uri_host_api)+getString(R.string.uri_genres));
					break;
				case R.id.form_name:
					Utils.initSearchByName(this);
					break;
				case R.id.action_calendar:
					new CalendarRequest(this).authorizedGet(getString(R.string.uri_host_api)+getString(R.string.uri_user_newepisodes), _app);
					break;
			}
		return true;
	}

	/**
	 * (non-Javadoc)
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 * When a genre of the list is pressed, create a request to get all tv shows from that genre
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		new TvShowRequest(this).get(getString(R.string.uri_host_api)+_elems.get(position).getUri());
	}

	/**
	 * Callback to TopRatedRequest fill the main list
	 */
	public void onTopRatedCompleted(ArrayList<TvShowSynopse> synopses){
		_elems = synopses;
		TvShowSynopseAdapter adapter = new TvShowSynopseAdapter(this, synopses);
		setListAdapter(adapter);
	}
}
