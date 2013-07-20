package src.stracker.asynchttp;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import src.stracker.SubscriptionsActivity;
import src.stracker.json.JSONLocator;
import src.stracker.json.SubscriptionSerializer;
import src.stracker.model.Subscription;

public class SubscriptionsRequest extends AbstractAsyncHttp {

	private SubscriptionSerializer _serializer;
	
	public SubscriptionsRequest(Context context){
		super(context);
		_serializer = (SubscriptionSerializer) JSONLocator.getInstance().getSerializer(Subscription.class);
	}
	
	@Override
	protected void onSuccessHook(String response) {
		ArrayList<Subscription> list = _serializer.deserialize(response);
		Intent intent = new Intent(_context,SubscriptionsActivity.class);
		intent.putExtra("list", list);
		_context.startActivity(intent);
	}

	@Override
	protected void onErrorHook(Throwable e, String response) {
		Toast.makeText(_context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();  
	}
}
