package src.stracker.asynchttp;

import android.content.Context;
import android.util.Log;

/**
 * @author diogomatos
 * This implementation represents a dummy request.
 */
public class DummyRequest extends AbstractAsyncHttp {

	/**
	 * The constructor of the dummy request 
	 * @param context - activity context
	 * @param runnable - callback to be invoked
	 */
	public DummyRequest(Context context, MyRunnable runnable) {
		super(context, runnable);
	}

	/**
	 * @see src.stracker.asynchttp.AbstractAsyncHttp#onSuccessHook(java.lang.String)
	 * @param response - string with the Http Response 
	 */
	@Override
	protected void onSuccessHook(String response) {
		Log.d("DummySuccess", response);
		_runnable.runWithArgument(null);
	}
}
