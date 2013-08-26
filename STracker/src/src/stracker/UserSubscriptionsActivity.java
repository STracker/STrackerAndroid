package src.stracker;

import android.os.Bundle;
import roboguice.inject.ContentView;
import src.stracker.adapters.SubscriptionAdapter;

/**
 * This Activity is the list with the user subscriptions.
 * @author diogomatos
 */
@ContentView(R.layout.activity_list)
public class UserSubscriptionsActivity extends BaseSubscriptionsActivity{
	
	/**
	 * @see src.stracker.BaseListActivity#onCreate(android.os.Bundle)
	 */
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setTitle(getString(R.string.profile_subscriptions));
		_subscriptions = getIntent().getParcelableArrayListExtra("list");
		_adapter = new SubscriptionAdapter(UserSubscriptionsActivity.this, _subscriptions);
		_listView.setAdapter(_adapter);
		_activity = this;
	}
}
