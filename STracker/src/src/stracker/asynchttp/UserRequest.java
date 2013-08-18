package src.stracker.asynchttp;

import src.stracker.json.JSONLocator;
import src.stracker.json.UserSerializer;
import src.stracker.model.User;
import android.content.Context;

/**
 * @author diogomatos
 * This implementation represents a request to a STracker user
 */
public class UserRequest extends AbstractAsyncHttp {

	private UserSerializer _serializer;
	
	/**
	 * The constructor of the user request 
	 * @param context - activity context
	 * @param runnable - callback to be invoked
	 */
	public UserRequest(Context context, MyRunnable runnable) {
		super(context, runnable);
		_serializer = (UserSerializer) JSONLocator.getInstance().getSerializer(User.class);
	}

	/**
	 * @see src.stracker.asynchttp.AbstractAsyncHttp#onSuccessHook(java.lang.String)
	 * @param response - string with the Http Response 
	 */
	@Override
	protected void onSuccessHook(String response) {
		User user = _serializer.deserialize(response);
		_runnable.runWithArgument(user);
	}
}
