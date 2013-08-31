package src.stracker;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import src.stracker.adapters.SubscriptionAdapter;
import src.stracker.model.Subscription;
import src.stracker.model.TvShowSynopse;

/**
 * This Activity is the list with the user subscriptions.
 * @author diogomatos
 */
public class BaseSubscriptionsActivity extends BaseListActivity {

	protected SubscriptionAdapter _adapter;
	protected ArrayList<Subscription> _subscriptions;
	protected Activity _activity;
	
	/**
	 * @see src.stracker.BaseListActivity#onCreate(android.os.Bundle)
	 */
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setTitle(getString(R.string.profile_subscriptions));
	}

	/**
	 * When a list result is pressed make the specific request according the type of the results
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> adapt, View view, int position, long id) {
		TvShowSynopse tvshow = _subscriptions.get(position).getTvShowSynope();
		Intent intent = new Intent(_activity,TvShowActivity.class);
		intent.putExtra(TVSHOW_URI_PARAM, tvshow.getUri());
		startActivity(intent);
	}
}
