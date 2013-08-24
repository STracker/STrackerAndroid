package src.stracker;

import java.util.ArrayList;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import roboguice.inject.ContentView;
import src.stracker.adapters.SubscriptionAdapter;
import src.stracker.model.Subscription;
import src.stracker.model.TvShowSynopse;

/**
 * This Activity is the list with the user subscriptions.
 * @author diogomatos
 */
@ContentView(R.layout.activity_list)
public class SubscriptionsActivity extends BaseListActivity {

	private SubscriptionAdapter _adapter;
	private ArrayList<Subscription> _subscriptions;
	
	/**
	 * @see src.stracker.BaseListActivity#onCreate(android.os.Bundle)
	 */
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setTitle(getString(R.string.title_subs));
		_subscriptions = _application.getFbUser().getSubscriptions();
		_adapter = new SubscriptionAdapter(SubscriptionsActivity.this, _subscriptions);
		_listView.setAdapter(_adapter);
	}

	/**
	 * When a list result is pressed make the specific request according the type of the results
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> adapt, View view, int position, long id) {
		TvShowSynopse tvshow = _subscriptions.get(position).getTvShowSynope();
		Intent intent = new Intent(this,TvShowActivity.class);
		intent.putExtra("tvShowUri", tvshow.getUri());
		startActivity(intent);
	}
}
