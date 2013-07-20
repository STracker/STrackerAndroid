package src.stracker;

import java.util.ArrayList;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import roboguice.activity.RoboListActivity;
import roboguice.inject.ContentView;
import src.stracker.adapters.SubscriptionAdapter;
import src.stracker.asynchttp.TvShowRequest;
import src.stracker.model.Subscription;
import src.stracker.model.TvShowSynopse;

@ContentView(R.layout.activity_list)
public class SubscriptionsActivity extends RoboListActivity {

	private SubscriptionAdapter _adapter;
	private ArrayList<Subscription> _subscriptions;
	private STrackerApp _app;
	
	/**
	 * (non-Javadoc)
	 * @see roboguice.activity.RoboListActivity#onCreate(android.os.Bundle)
	 */
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setTitle("Subscriptions");
		_app = (STrackerApp) getApplication();
		_subscriptions = getIntent().getParcelableArrayListExtra("list");
		_adapter = new SubscriptionAdapter(this, _subscriptions);
		setListAdapter(_adapter);
	}
	
	/**
	 * (non-Javadoc)
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 * When a list result is pressed make the specific request according the type of the results
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		TvShowSynopse tvshow = _subscriptions.get(position).getTvShowSynope();
		new TvShowRequest(this).get(_app.getApiURL()+tvshow.getUri());
	}
}
