package src.stracker;

import java.util.ArrayList;
import java.util.List;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import src.stracker.asynchttp.MyRunnable;
import src.stracker.asynchttp.TvShowRequests;
import src.stracker.model.TvShowSynopse;
/**
 * @author diogomatos
 * This class represents a list of television show synopsis obtained by it's name.
 */
public class TvShowsByNameActivity extends TvShowSynopsisActivity {

	private int rangeStart;
	private int rangeEnd;
	private String _uri;
	
	/**
	 * In this method the URI isn't used. Instead the parameter is used to 
	 * keep the name in television show search
	 * @see src.stracker.SynopsisActivity#fetchRequest(java.lang.String)
	 */
	@Override
	protected void fetchRequest(String uri) {
		_uri = uri;
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
		TvShowRequests.getTvShowByName(this, new MyRunnable() {
			@Override
			public void run() {
				Toast.makeText(TvShowsByNameActivity.this, R.string.error_search_by_name, Toast.LENGTH_SHORT).show();
			}
			@SuppressWarnings("unchecked")
			@Override
			public <T> void runWithArgument(T response) {
				_synopses = (ArrayList<TvShowSynopse>) response;
				List<String> elems = new ArrayList<String>();
	        	for(TvShowSynopse synopse : _synopses)
	            	elems.add(synopse.getName());
	        	setAdapter(elems);
			}
		}, _uri, rangeStart, rangeEnd);
	}
}
