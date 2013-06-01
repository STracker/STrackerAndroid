package src.stracker.asynchttp;

import android.app.ProgressDialog;
import android.content.Context;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * @author diogomatos
 *
 */
public abstract class AbstractAsyncHttp {

	private AsyncHttpClient _client;
	protected Context _context;
	private ProgressDialog _dialog;
	
	/**
	 * @param context
	 */
	public AbstractAsyncHttp(Context context){
		_context = context;
		_dialog = new ProgressDialog(context);
		_client = new AsyncHttpClient();
		_client.addHeader("Cache-Control", "no-cache");
		_client.addHeader("Accept", "application/json");
	}
	
	/**
	 * @param url
	 */
	public void execute(String url){
		//Waiting message
		_dialog.setMessage("loading...");
		_dialog.show();
		
		//make http request
		_client.get(url, new AsyncHttpResponseHandler() {
		    @Override
		    public void onSuccess(String response) {
		    	if (_dialog.isShowing()) _dialog.dismiss();
		    	onSuccessHook(response);
		    }
		    
		    @Override
		    public void onFailure(Throwable e, String response){
		    	if (_dialog.isShowing()) _dialog.dismiss();
		    	onErrorHook(e,response);
		    }
		});	
	}
	
	protected abstract void onSuccessHook(String response);
	protected abstract void onErrorHook(Throwable e,String response);
	
}
