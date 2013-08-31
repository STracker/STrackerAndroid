package src.stracker;

import java.util.ArrayList;
import roboguice.event.Observes;
import roboguice.inject.ContentView;
import src.stracker.actions.TvShowActions;
import src.stracker.actions.UserActions;
import src.stracker.adapters.TvShowSynopseAdapter;
import src.stracker.asynchttp.MyRunnable;
import src.stracker.asynchttp.TvShowRequests;
import src.stracker.model.TvShowSynopse;
import src.stracker.utils.ShakeDetector;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

/**
 * This Activity is the initial activity of STracker Android application.
 * @author diogomatos
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseListActivity {

	private ArrayList<TvShowSynopse> _elems;
	
	/**
	 * @see src.stracker.BaseListActivity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		performRequest();
	}
	
	/**
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu); 
		return true;
	}

	/**
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId()){
			case R.id.action_profile:
				if(!UserActions.checkLogin(this)) break;
				startActivity(new Intent(this,ProfileActivity.class));
				break;  
			case R.id.form_friend:
				UserActions.searchFriend(this);
				break;
			case R.id.form_genre:
				startActivity(new Intent(this, GenreSynopsisActivity.class));
				break;
			case R.id.form_name:
				if(!UserActions.checkLogin(this)) break;
				TvShowActions.searchTvShowByName(this);
				break;
			case R.id.action_calendar:
				if(!UserActions.checkLogin(this)) break;
				startActivity(new Intent(this, CalendarActivity.class));
				break;
		}
		return true;
	}
	
	/**
	 * When a genre of the list is pressed, create a request to get all tv shows from that genre
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 * @param adapt - adapterview 
	 * @param v - view
	 * @param position - position in list of the triggered item
	 * @param id - identifier
	 */
	@Override
	public void onItemClick(AdapterView<?> adapt, View view, int position, long id) {
		Intent intent = new Intent(this,TvShowActivity.class);
		intent.putExtra(TVSHOW_URI_PARAM, _elems.get(position).getUri());
		startActivity(intent);
	}
	
	/**
	 * Event associated to shake motion
	 * @param event - shake event
	 */
	public void handleShake(@Observes ShakeDetector.OnShakeEvent event) {
		performRequest();
	}
	
	/**
	 * This method is used to perform the HTTP request command
	 */
	private void performRequest(){
		//Request top rated shows
		TvShowRequests.getTopRated(this, new MyRunnable() {
			@Override
			public void run() 
			{
				Toast.makeText(MainActivity.this, R.string.not_found, Toast.LENGTH_SHORT).show(); 
			}
			@SuppressWarnings("unchecked")
			@Override
			public <T> void runWithArgument(T response) 
			{
				_elems = (ArrayList<TvShowSynopse>) response;
				TvShowSynopseAdapter adapter = new TvShowSynopseAdapter(MainActivity.this, _elems);
				_listView.setAdapter(adapter);
			}
		});
	}
}
