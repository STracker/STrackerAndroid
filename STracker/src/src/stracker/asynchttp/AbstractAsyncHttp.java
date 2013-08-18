package src.stracker.asynchttp;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map.Entry;

import src.stracker.R;
import src.stracker.STrackerApp;
import src.stracker.utils.Utils;
import HawkClient.*;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * @author diogomatos
 * This class provides the implementation of a asynchronous http request.
 * All classes that wants to implement this contract must implement two hook methods: onSuccessHook and onErrorHook. 
 */
public abstract class AbstractAsyncHttp {

	protected AsyncHttpClient _client;
	protected Context _context;
	private ProgressDialog _dialog;
	final int DEFAULT_TIMEOUT = 30000;
	final MyRunnable _runnable;
	protected STrackerApp _app;
	private String BASE_URL;

	/**
	 * The constructor of the abstract asynchronous http request
	 * @param context - activity context where the context was made
	 * @param runnable - callback function 
	 */
	public AbstractAsyncHttp(Context context, MyRunnable runnable){
		_context = context;
		_dialog = new ProgressDialog(context); 
		_client = new AsyncHttpClient();
		_client.setTimeout(DEFAULT_TIMEOUT);
		_app = (STrackerApp) _context.getApplicationContext();
		_runnable = runnable;
		BASE_URL = _context.getString(R.string.uri_host_api);
	}

	/**
	 * This method execute a GET http method to the url that receives by parameter.
	 * @param url
	 */
	public void get(String url){
		//Waiting message
		_dialog.setMessage("loading...");
		_dialog.show();
		_client.addHeader("Cache-Control", "no-cache");
		_client.addHeader("Accept", "application/json");
		_client.get(getAbsoluteUrl(url), _handler);
	}
	
	public void authorizedGet(String url) {
		if(!Utils.checkLogin((Activity)_context, _app)) return;
		//Waiting message
		_dialog.setMessage("loading...");
		_dialog.show();
		_client.addHeader("Authorization", getAuthorizationHeader("GET",getAbsoluteUrl(url), null));
		_client.get(getAbsoluteUrl(url), _handler);
	}
	
	public void authorizedPost(String url, HashMap<String, String> params){
		if(!Utils.checkLogin((Activity)_context, _app)) return;
		PostParams postParams = buildRequestBody(params);
		_client.addHeader("Authorization", getAuthorizationHeader("POST",getAbsoluteUrl(url), postParams.payload));
		_client.post(getAbsoluteUrl(url),postParams.params,_handler);
	}
	
	public void authorizedDelete(String url){
		if(!Utils.checkLogin((Activity)_context, _app)) return;
		_client.addHeader("Authorization", getAuthorizationHeader("DELETE",getAbsoluteUrl(url), null));
		_client.delete(getAbsoluteUrl(url),_handler);
	}
	
	/**
	 * Callback's to http requests
	 */
	private AsyncHttpResponseHandler _handler = new AsyncHttpResponseHandler(){
		@Override
		public void onSuccess(String response) {
			if(_dialog.isShowing()) _dialog.dismiss();
			onSuccessHook(response);
		}

		@Override
		public void onFailure(Throwable e, String response){
			if(_dialog.isShowing()) _dialog.dismiss();
			_runnable.run();
		}
	};
	
	/**
	 * Build and return authorization header according Hawk Protocol specifications
	 */
	private String getAuthorizationHeader(String method, String url, String payload){
		URL objUrl = null;
		try {
			objUrl = new URL(url);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		Long time = System.currentTimeMillis();
		Long timestamp = time / 1000L;
		String nonce = "NONO";//HawkClient.generateNonce();
		HawkCredentials credentials = new HawkCredentials(_app.getFbUser().getId(), _context.getString(R.string.hawk_key));
		String header = "";
		try {
			if(method.equals("POST")){
				header = HawkClient.createAuthorizationHeader(objUrl, method, timestamp+"", nonce, credentials, null, payload, true);
			} else
				header = HawkClient.createAuthorizationHeader(objUrl, method, timestamp+"", nonce, credentials, null, null, false);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return header;
	}
	
	private PostParams buildRequestBody(HashMap<String, String> params){
		RequestParams requestParams = new RequestParams();
		StringBuilder payload = new StringBuilder();
		for(Entry<String, String> par : params.entrySet()){
			requestParams.put(par.getKey(), par.getValue());
			try {
				payload.append(par.getKey())
					   .append("=")
					   .append(URLEncoder.encode(par.getValue(), "UTF-8"))
					   .append("&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		payload.setLength(payload.length() - 1);
		return new PostParams(requestParams, payload.toString()); 
	}
	
	/**
	 * This method must implement the behavior of the class after the http method returns successfully.
	 * @param response - string that contains the Http response
	 */
	protected abstract void onSuccessHook(String response);

	private class PostParams{
		public RequestParams params;
		String payload;
		
		public PostParams(RequestParams requestParams, String pl){
			params = requestParams;
			payload = pl;
		}
	}
	
	private String getAbsoluteUrl(String url){
		return BASE_URL + url;
	}
}
