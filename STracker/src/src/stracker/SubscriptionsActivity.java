package src.stracker;

import java.util.ArrayList;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import roboguice.activity.RoboListActivity;
import roboguice.inject.ContentView;
import src.stracker.adapters.SubscriptionAdapter;
import src.stracker.asynchttp.MyRunnable;
import src.stracker.asynchttp.SubscriptionsRequest;
import src.stracker.model.Subscription;
import src.stracker.model.TvShowSynopse;

/**
 * This Activity is the list with the user subscriptions.
 * @author diogomatos
 */
@ContentView(R.layout.activity_list)
public class SubscriptionsActivity extends RoboListActivity {

	private SubscriptionAdapter _adapter;
	private ArrayList<Subscription> _subscriptions;
	
	/**
	 * @see roboguice.activity.RoboListActivity#onCreate(android.os.Bundle)
	 */
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		new SubscriptionsRequest(this, new MyRunnable() {
			@Override
			public void run() {
				Toast.makeText(SubscriptionsActivity.this, R.string.error_subs, Toast.LENGTH_SHORT).show();
			}
			@Override
			public <T> void runWithArgument(T response) {
				setTitle(getString(R.string.title_subs));
				_subscriptions = getIntent().getParcelableArrayListExtra("list");
				_adapter = new SubscriptionAdapter(SubscriptionsActivity.this, _subscriptions);
				setListAdapter(_adapter);
			}
		}).authorizedGet(getString(R.string.uri_user_subscriptions));
	}
	
	/**
	 * When a list result is pressed make the specific request according the type of the results
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		TvShowSynopse tvshow = _subscriptions.get(position).getTvShowSynope();
		Intent intent = new Intent(this,TvShowActivity.class);
		intent.putExtra("tvShowUri", tvshow.getUri());
		startActivity(intent);
	}
}
