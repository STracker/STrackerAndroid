package src.stracker.asynchttp;

import android.content.Context;
import android.widget.Toast;

public class UserRequest extends AbstractAsyncHttp {

	public UserRequest(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onSuccessHook(String response) {
		Toast.makeText(_context, response, Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onErrorHook(Throwable e, String response) {
		Toast.makeText(_context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
	}
}
