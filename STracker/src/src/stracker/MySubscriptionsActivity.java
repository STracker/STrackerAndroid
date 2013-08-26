package src.stracker;

import roboguice.inject.ContentView;
import src.stracker.adapters.SubscriptionAdapter;
import android.os.Bundle;

/**
 * This Activity is the list with the my subscriptions.
 * @author diogomatos
 */
@ContentView(R.layout.activity_list)
public class MySubscriptionsActivity extends BaseSubscriptionsActivity{

	/**
	 * @see src.stracker.BaseListActivity#onCreate(android.os.Bundle)
	 */
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setTitle(getString(R.string.profile_subscriptions));
		_subscriptions = _application.getFbUser().getSubscriptions();
		_adapter = new SubscriptionAdapter(MySubscriptionsActivity.this, _subscriptions);
		_listView.setAdapter(_adapter);
		_activity = this;
	}
}
