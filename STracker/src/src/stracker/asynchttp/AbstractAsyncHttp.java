package src.stracker.asynchttp;

import java.net.MalformedURLException;
import java.net.URL;

import src.stracker.STrackerApp;

import HawkClient.HawkClient;
import HawkClient.HawkCredentials;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

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
	public void execute(String url){
		//Waiting message
		_dialog.setMessage("loading...");
		_dialog.show();
		_client.addHeader("Cache-Control", "no-cache");
		_client.addHeader("Accept", "application/json");
		
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

	public void authorizedPost(String url, STrackerApp app){
		RequestParams requestParams = new RequestParams();
		requestParams.put("Id", app.getFbUser().getId());
		requestParams.put("Name", app.getFbUser().getName());
		requestParams.put("Email", app.getFbUser().getEmail());
		requestParams.put("Photo", app.getFbUser().getPhotoUrl());
		
		_client.addHeader("Authorization", getAuthorizationHeaderPost(url, app, requestParams));
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


	public void authorizedGet(String url,STrackerApp app) {
		//Waiting message
		_dialog.setMessage("loading...");
		_dialog.show();
		_client.addHeader("Authorization", getAuthorizationHeaderGet(url, app));
		
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
	
	/**
	 * Build and return authorization header according Hawk Protocol specifications
	 */
	private String getAuthorizationHeaderGet(String url, STrackerApp app){
		URL objUrl = null;
		try {
			objUrl = new URL(url);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		String method = "GET";
		Long time = System.currentTimeMillis();
		Long timestamp = time / 1000L;
		String nonce = "LawkW";
		Log.d("Timestamp", timestamp+"");
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
	 * Build and return authorization header according Hawk Protocol specifications
	 */
	private String getAuthorizationHeaderPost(String url, STrackerApp app, RequestParams requestParams){
		URL objUrl = null;
		try {
			objUrl = new URL(url);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		String method = "POST";
		Long time = System.currentTimeMillis();
		Long timestamp = time / 1000L;
		String nonce = "LawkW";
		Log.d("Timestamp", timestamp+"");
		HawkCredentials credentials = new HawkCredentials(app.getFbUser().getId(), app.getHawkKey());
		String header = "";
		try {
			Log.d("PARAMS", requestParams.toString());
			//header = HawkClient.createAuthorizationHeaderWithPayloadValidation(objUrl, method, timestamp+"", nonce, credentials, requestParams.toString(), null,"application/x-www-form-urlenconded");
			header = HawkClient.createAuthorizationHeader(objUrl, method, timestamp+"", nonce, credentials, null, null);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Log.d("HEADER",header);
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
