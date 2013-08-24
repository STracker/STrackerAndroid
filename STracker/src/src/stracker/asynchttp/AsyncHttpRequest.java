package src.stracker.asynchttp;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map.Entry;
import src.stracker.R;
import src.stracker.STrackerApp;
import src.stracker.json.ISerialize;
import HawkClient.*;
import android.app.ProgressDialog;
import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * @author diogomatos
 * This class provides the implementation of a asynchronous HTTP request.
 * All classes that wants to implement this contract must implement two hook methods: onSuccessHook and onErrorHook. 
 */
public class AsyncHttpRequest {

	private static final AsyncHttpClient _client = new AsyncHttpClient();
	private static ProgressDialog _dialog;
	private static final int DEFAULT_TIMEOUT = 30000;

	/**
	 * The constructor of the abstract asynchronous HTTP request
	 */
	AsyncHttpRequest(){
		_client.setTimeout(DEFAULT_TIMEOUT);
		_client.addHeader("Cache-Control", "no-cache");
		_client.addHeader("Accept", "application/json");
	}

	/**
	 * This method execute a GET HTTP method to the URL that receives by parameter.
	 * @param url
	 */
	public static void get(Context context, final MyRunnable runnable, final ISerialize<?> serializer, String uri){
		showProgressDialog(context);
		_client.get(getAbsoluteUrl(context, uri), createHandler(runnable, serializer));
	}
	
	public static void authorizedGet(Context context, MyRunnable runnable, ISerialize<?> serializer, String uri) {
		_client.addHeader("Authorization", getAuthorizationHeader(context, "GET",getAbsoluteUrl(context, uri), null));
		get(context, runnable, serializer, uri);
	}
	
	public static void authorizedPost(Context context, MyRunnable runnable, ISerialize<?> serializer, String uri, HashMap<String, String> params){
		PostParams postParams = buildRequestBody(params);
		String absoluteUrl = getAbsoluteUrl(context, uri);
		_client.addHeader("Authorization", getAuthorizationHeader(context, "POST", absoluteUrl, postParams.payload));
		_client.post(absoluteUrl, postParams.params, createHandler(runnable, serializer));
	}
	
	public static void authorizedDelete(Context context, MyRunnable runnable, ISerialize<?> serializer, String uri){
		String absoluteUrl = getAbsoluteUrl(context, uri);
		_client.addHeader("Authorization", getAuthorizationHeader(context, "DELETE",absoluteUrl, null));
		_client.delete(absoluteUrl, createHandler(runnable, serializer));
	}
	
	/**
	 * Build and return authorization header according Hawk Protocol specifications
	 */
	private static String getAuthorizationHeader(Context context, String method, String url, String payload){
		URL objUrl = null;
		try {
			objUrl = new URL(url);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		Long time = System.currentTimeMillis();
		Long timestamp = time / 1000L;
		String nonce = "NONO";//HawkClient.generateNonce();
		String header = "";
		try {
			if(method.equals("POST")){
				header = HawkClient.createAuthorizationHeader(objUrl, method, timestamp+"", nonce, ((STrackerApp)context.getApplicationContext()).getHawkCredentials(), null, payload, true);
			} else
				header = HawkClient.createAuthorizationHeader(objUrl, method, timestamp+"", nonce, ((STrackerApp)context.getApplicationContext()).getHawkCredentials(), null, null, false);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return header;
	}
	
	private static PostParams buildRequestBody(HashMap<String, String> params){
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

	private static class PostParams{
		public RequestParams params;
		String payload;
		
		public PostParams(RequestParams requestParams, String pl){
			params = requestParams;
			payload = pl;
		}
	}
	
	private static String getAbsoluteUrl(Context context, String uri) {
		return context.getString(R.string.uri_host_api) + uri;
	}
	
	private static void showProgressDialog(Context context){
		//Waiting message
		if(_dialog == null){
			_dialog = new ProgressDialog(context); 
			_dialog.setMessage(context.getString(R.string.loading_message));
		}
		_dialog.show();
	}
	
	private static void hideProgressDialog(){
		if(_dialog.isShowing()) _dialog.dismiss();
	}
	
	private static AsyncHttpResponseHandler createHandler(final MyRunnable runnable, final ISerialize<?> serializer){
		return new AsyncHttpResponseHandler(){
			@Override
			public void onSuccess(String response) {
				hideProgressDialog();
				if(serializer != null) 
					runnable.runWithArgument(serializer.deserialize(response));
			}
	
			@Override
			public void onFailure(Throwable e, String response){
				hideProgressDialog();
				runnable.run();
			}
		};
	}
}
