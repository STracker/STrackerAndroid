package src.stracker.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class SyncHttpRequest {
	
	private HttpClient _httpclient;
	private static SyncHttpRequest _singleton;
	
	private SyncHttpRequest(){
		_httpclient = new DefaultHttpClient();
	}
	
	public static SyncHttpRequest getInstance(){
		if (_singleton == null)
			_singleton = new SyncHttpRequest();
		return _singleton;
	}
	
	public String execute(String url){
		HttpGet request = new HttpGet(url);
		HttpResponse response;
		String responseString = null;
		try {
			request.setHeader("Cache-Control", "no-cache"); 
			request.setHeader("Accept", "application/json");
			response = _httpclient.execute(request);
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
			e.printStackTrace(); 
		} 
		return responseString;
	}
}
