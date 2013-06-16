package src.stracker;

import java.util.ArrayList;
import roboguice.activity.RoboListActivity;
import roboguice.inject.ContentView;
import src.stracker.adapters.TvShowSynopseAdapter;
import src.stracker.asynchttp.GenresRequest;
import src.stracker.asynchttp.SearchByNameRequest;
import src.stracker.asynchttp.TopRatedRequest;
import src.stracker.asynchttp.TvShowRequest;
import src.stracker.model.TvShowSynopse;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

/**
 * This Activity is the initial activity of STracker Android application.
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends RoboListActivity {
    
	private STrackerApp _app;
	private Context _context;
    private ArrayList<TvShowSynopse> _elems;
	
	/**
	 * (non-Javadoc)
	 * @see roboguice.activity.RoboListActivity#onCreate(android.os.Bundle)
	 */
	@Override  
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		_app = (STrackerApp) getApplication();
		_context = (this);
		new TopRatedRequest(this).execute(_app.getApiURL()+"tvshows/toprated");
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
    	switch(item.getItemId()){
    	case R.id.action_profile:
    		break;
    	case R.id.action_series:
    		break;
    	case R.id.action_messages:
    		break;
    	case R.id.action_friends:
    		//new SubscribeAsyncHttp(this).post(_app.getApiURL()+"users/100005516760836/subscriptions");
    		//new SubscribeAsyncHttp(this).execute(_app.getURL()+"tvshows/tt0303461/ratings");
    		break;
    	case R.id.action_settings:
    		break;
    	case R.id.form_friend:
    		break;
    	case R.id.form_genre:
    		new GenresRequest(this).execute(_app.getApiURL()+"genres");
    		break;
    	case R.id.form_name:
    		initSearchByName();
    		break;
    	case R.id.action_calendar:
    		startActivity(new Intent(this,CalendarActivity.class));
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
		new TvShowRequest(this).execute(_app.getApiURL()+_elems.get(position).getUri());
	}
    
    /**
     * Callback to TopRatedRequest fill the main list
     */
    public void onTopRatedCompleted(ArrayList<TvShowSynopse> synopses){
    	_elems = synopses;
    	TvShowSynopseAdapter adapter = new TvShowSynopseAdapter(this, synopses);
		setListAdapter(adapter);
    }
    
    /**
     * This method pop's up an AlertDialog to begin the Search a tv show by the name.
     */
    private void initSearchByName(){
    	AlertDialog.Builder adBuilder = new AlertDialog.Builder(this);
        adBuilder.setMessage("Enter TvShow's Name:");
        adBuilder.setCancelable(true);
        // Set an EditText view to get user input 
        final EditText input = new EditText(this);
        adBuilder.setView(input);
        adBuilder.setPositiveButton("Cancel",
                new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int id) {
	                    dialog.cancel();
	                }
        }); 
        adBuilder.setNegativeButton("Search",
        		new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int id) {
	                	String url = _app.getApiURL()+"tvshows?name="+input.getText();
	                	new SearchByNameRequest(_context).execute(url.replaceAll(" ", "+"));
	                }
        });
        AlertDialog alertDialog = adBuilder.create();
        alertDialog.show();
    }
}
