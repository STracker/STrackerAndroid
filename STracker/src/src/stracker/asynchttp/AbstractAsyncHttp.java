package src.stracker.asynchttp;

import android.app.ProgressDialog;
import android.content.Context;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * @author diogomatos
 *
 */
public abstract class AbstractAsyncHttp {

	protected AsyncHttpClient _client;
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
		//_client.addHeader("Content-Type", "application/x-www-form-urlencoded");
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
	
	public void post(String url){
		RequestParams requestParams = new RequestParams();
		//requestParams.put("userId", "100005516760836");
		requestParams.put("TvShowId", "tt0306414");
		_client.post(url,requestParams,new AsyncHttpResponseHandler() {
		    @Override
		    public void onSuccess(String response) {
		    	onSuccessHook(response);
		    }
		    
		    @Override
		    public void onFailure(Throwable e, String response){
		    	onErrorHook(e,response);
		    }
		});
	}
	
	protected abstract void onSuccessHook(String response);
	protected abstract void onErrorHook(Throwable e,String response);
}
