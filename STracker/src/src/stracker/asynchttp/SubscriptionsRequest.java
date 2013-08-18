package src.stracker.asynchttp;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import src.stracker.SubscriptionsActivity;
import src.stracker.json.JSONLocator;
import src.stracker.json.SubscriptionSerializer;
import src.stracker.model.Subscription;

/**
 * @author diogomatos
 * This implementation represents a request to the user subscriptions
 */
public class SubscriptionsRequest extends AbstractAsyncHttp {

	private SubscriptionSerializer _serializer;
	
	/**
	 * The constructor of the subscriptions request 
	 * @param context - activity context
	 * @param runnable - callback to be invoked
	 */
	public SubscriptionsRequest(Context context, MyRunnable runnable){
		super(context, runnable);
		_serializer = (SubscriptionSerializer) JSONLocator.getInstance().getSerializer(Subscription.class);
	}
	
	/**
	 * @see src.stracker.asynchttp.AbstractAsyncHttp#onSuccessHook(java.lang.String)
	 * @param response - string with the Http Response 
	 */
	@Override
	protected void onSuccessHook(String response) {
		ArrayList<Subscription> list = _serializer.deserialize(response);
		Intent intent = new Intent(_context,SubscriptionsActivity.class);
		intent.putExtra("list", list);
		_context.startActivity(intent);
	}
}
