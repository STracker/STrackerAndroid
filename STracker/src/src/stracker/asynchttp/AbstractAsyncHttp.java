package src.stracker.asynchttp;

import java.net.MalformedURLException;
import java.net.URL;

import src.stracker.STrackerApp;

import HawkClient.HawkClient;
import HawkClient.HawkCredentials;
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
		
		//make http request
		_client.get(url, new AsyncHttpResponseHandler() {
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
	
	public void authorizedGet(String url,STrackerApp app) {
		//Waiting message
		_dialog.setMessage("loading...");
		_dialog.show();
		_client.addHeader("Authorization", getAuthorizationHeader("GET",url, app));
		
		//make http request
		_client.get(url, new AsyncHttpResponseHandler() {
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
	
	public void authorizedPost(String url, STrackerApp app, RequestParams requestParams){
		_client.addHeader("Authorization", getAuthorizationHeader("POST",url, app));
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
	
	public void authorizedDelete(String url, STrackerApp app){
		_client.addHeader("Authorization", getAuthorizationHeader("DELETE",url, app));
		_client.delete(url,new AsyncHttpResponseHandler() {
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
	
	/**
	 * Build and return authorization header according Hawk Protocol specifications
	 */
	private String getAuthorizationHeader(String method, String url, STrackerApp app){
		URL objUrl = null;
		try {
			objUrl = new URL(url);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		Long time = System.currentTimeMillis();
		Long timestamp = time / 1000L;
		String nonce = "LawkW";
		HawkCredentials credentials = new HawkCredentials(app.getFbUser().getId(), app.getHawkKey());
		String header = "";
		try {
			header = HawkClient.createAuthorizationHeader(objUrl, method, timestamp+"", nonce, credentials, null, null);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return header;
	}
	
	/**
	 * This method must implement the behavior of the class after the http method returns successfully.
	 */
	protected abstract void onSuccessHook(String response);
	/**
	 * This method must implement the behavior of the class after the http method returns in error.
	 */
	protected abstract void onErrorHook(Throwable e,String response);
}
