package src.stracker.tasks;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public abstract class AbstractBaseTask<T> extends AsyncTask<String, Integer, String> {

	public Context context;
	public ProgressDialog dialog;
	public Exception exception;
	public Activity activity;

	protected AbstractBaseTask() {
	}

	public AbstractBaseTask(Context context) {
		this.context = context;
		this.dialog = new ProgressDialog(context);
	}

	@Override
	protected void onPreExecute() {
		this.dialog.setMessage("loading...");
		this.dialog.show();
	}

	@Override
	protected String doInBackground(String... uris) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet request = new HttpGet(uris[0]);
		HttpResponse response;
		String responseString = null;
		try {
			request.setHeader("Cache-Control", "no-cache"); 
			request.setHeader("Accept", "application/json");
			response = httpclient.execute(request);
			StatusLine statusLine = response.getStatusLine();
			if(statusLine.getStatusCode() == HttpStatus.SC_OK){
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				responseString = out.toString();
			} else{
				//Closes the connection.
				response.getEntity().getContent().close();
				throw new IOException(statusLine.getReasonPhrase());
			}
		} catch (Exception e) {
			exception = e; 
		} 
		return responseString;
	}

	@Override
	protected void onPostExecute(String result) {
		if (dialog.isShowing()) dialog.dismiss();
		if (exception == null) {
			onResult(result);
		} else {
			onError(exception.getMessage().toString());
		}
	}

	public abstract void onResult(String result);
	public abstract void onError(String error);
}
