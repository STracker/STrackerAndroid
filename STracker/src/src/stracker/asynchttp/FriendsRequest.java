package src.stracker.asynchttp;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import src.stracker.FriendsActivity;
import src.stracker.json.FriendsSerializer;
import src.stracker.json.JSONLocator;
import src.stracker.model.UserSynopse;

public class FriendsRequest extends AbstractAsyncHttp {

	private FriendsSerializer _serializer;
	
	public FriendsRequest(Context context) {
		super(context);
		_serializer = (FriendsSerializer) JSONLocator.getInstance().getSerializer(UserSynopse.class);
	}
	
	@Override
	protected void onSuccessHook(String response) {
		ArrayList<UserSynopse> list = _serializer.deserialize(response);
		Intent intent = new Intent(_context, FriendsActivity.class);
		intent.putExtra("list", list);
		_context.startActivity(intent);
	}

	@Override
	protected void onErrorHook(Throwable e, String response) {
		Toast.makeText(_context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
	}
}
