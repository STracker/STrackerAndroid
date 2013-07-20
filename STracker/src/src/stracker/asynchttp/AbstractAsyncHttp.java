package src.stracker.asynchttp;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map.Entry;
import src.stracker.STrackerApp;
import HawkClient.*;
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

	/**
	 * @param context
	 */
	public AbstractAsyncHttp(Context context){
		_context = context;
		_dialog = new ProgressDialog(context); 
		_client = new AsyncHttpClient();
		_client.setTimeout(DEFAULT_TIMEOUT);
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
		_client.get(url, _handler);
	}
	
	public void authorizedGet(String url,STrackerApp app) {
		//Waiting message
		_dialog.setMessage("loading...");
		_dialog.show();
		_client.addHeader("Authorization", getAuthorizationHeader("GET",url, app, null));
		_client.get(url, _handler);
	}
	
	public void authorizedPost(String url, STrackerApp app, HashMap<String, String> params){
		PostParams postParams = buildRequestBody(params);
		_client.addHeader("Authorization", getAuthorizationHeader("POST",url, app, postParams.payload));
		_client.post(url,postParams.params,_handler);
	}
	
	public void authorizedDelete(String url, STrackerApp app){
		_client.addHeader("Authorization", getAuthorizationHeader("DELETE",url, app, null));
		_client.delete(url,_handler);
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
			onErrorHook(e,response);
		}
	};
	
	/**
	 * Build and return authorization header according Hawk Protocol specifications
	 */
	private String getAuthorizationHeader(String method, String url, STrackerApp app, String payload){
		URL objUrl = null;
		try {
			objUrl = new URL(url);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		Long time = System.currentTimeMillis();
		Long timestamp = time / 1000L;
		String nonce = "NONO";//HawkClient.generateNonce();
		HawkCredentials credentials = new HawkCredentials(app.getFbUser().getId(), app.getHawkKey());
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
	 */
	protected abstract void onSuccessHook(String response);
	/**
	 * This method must implement the behavior of the class after the http method returns in error.
	 */
	protected abstract void onErrorHook(Throwable e,String response);
	
	private class PostParams{
		public RequestParams params;
		String payload;
		
		public PostParams(RequestParams requestParams, String pl){
			params = requestParams;
			payload = pl;
		}
	}
}
