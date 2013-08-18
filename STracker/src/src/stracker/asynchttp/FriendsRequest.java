package src.stracker.asynchttp;

import java.util.ArrayList;
import android.content.Context;
import android.content.Intent;
import src.stracker.FriendsActivity;
import src.stracker.json.FriendsSerializer;
import src.stracker.json.JSONLocator;
import src.stracker.model.UserSynopse;

/**
 * @author diogomatos
 * This implementation represents a friend request
 */
public class FriendsRequest extends AbstractAsyncHttp {

	private FriendsSerializer _serializer;
	
	/**
	 * The constructor of the friends request 
	 * @param context - activity context
	 * @param runnable - callback to be invoked
	 */
	public FriendsRequest(Context context, MyRunnable runnable) {
		super(context, runnable);
		_serializer = (FriendsSerializer) JSONLocator.getInstance().getSerializer(UserSynopse.class);
	}
	
	/**
	 * @see src.stracker.asynchttp.AbstractAsyncHttp#onSuccessHook(java.lang.String)
	 * @param response - string with the Http Response 
	 */
	@Override
	protected void onSuccessHook(String response) {
		ArrayList<UserSynopse> list = _serializer.deserialize(response);
		Intent intent = new Intent(_context, FriendsActivity.class);
		intent.putExtra("list", list);
		_context.startActivity(intent);
	}
}
