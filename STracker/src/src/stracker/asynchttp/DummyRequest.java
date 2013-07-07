package src.stracker.asynchttp;

import android.content.Context;
import android.util.Log;

public class DummyRequest extends AbstractAsyncHttp {

	public DummyRequest(Context context) {
		super(context);
	}

	@Override
	protected void onSuccessHook(String response) {
		Log.d("DummySuccess", response);
	}

	@Override
	protected void onErrorHook(Throwable e, String response) {
	Log.d("DummyError", response);
	}
}
