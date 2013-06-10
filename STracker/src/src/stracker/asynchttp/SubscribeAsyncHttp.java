/**
 * 
 */
package src.stracker.asynchttp;

import android.content.Context;
import android.widget.Toast;

/**
 * @author diogomatos
 *
 */
public class SubscribeAsyncHttp extends AbstractAsyncHttp {

	/**
	 * @param context
	 */
	public SubscribeAsyncHttp(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see src.stracker.asynchttp.AbstractAsyncHttp#onSuccessHook(java.lang.String)
	 */
	@Override
	protected void onSuccessHook(String response) {
		// TODO Auto-generated method stub
		Toast.makeText(_context, "Resposta: "+response, Toast.LENGTH_SHORT).show();  
	}

	/* (non-Javadoc)
	 * @see src.stracker.asynchttp.AbstractAsyncHttp#onErrorHook(java.lang.Throwable, java.lang.String)
	 */
	@Override
	protected void onErrorHook(Throwable e, String response) {
		// TODO Auto-generated method stub
		Toast.makeText(_context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();  
	}
}
