package src.stracker.asynchttp;

import src.stracker.ProfileActivity;
import src.stracker.json.JSONLocator;
import src.stracker.json.UserSerializer;
import src.stracker.model.User;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class UserRequest extends AbstractAsyncHttp {

	private UserSerializer _serializer;
	
	public UserRequest(Context context) {
		super(context);
		_serializer = (UserSerializer) JSONLocator.getInstance().getSerializer(User.class);
	}

	@Override
	protected void onSuccessHook(String response) {
		User user = _serializer.deserialize(response);
		Intent intent = new Intent(_context,ProfileActivity.class);
		intent.putExtra("user", user);
		_context.startActivity(intent);
	}

	@Override
	protected void onErrorHook(Throwable e, String response) {
		Toast.makeText(_context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
	}
}
