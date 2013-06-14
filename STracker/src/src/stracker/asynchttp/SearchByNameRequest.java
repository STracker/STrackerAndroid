/**
 * 
 */
package src.stracker.asynchttp;

import java.util.ArrayList;

import org.apache.http.NoHttpResponseException;

import src.stracker.ResultActivity;
import src.stracker.STrackerApp;
import src.stracker.json.JSONLocator;
import src.stracker.json.TvShowSynopseSerializer;
import src.stracker.model.TvShowSynopse;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @author diogomatos
 * This implementation represents a search of tv shows by it's name
 */
public class SearchByNameRequest extends AbstractAsyncHttp {

	private TvShowSynopseSerializer _serializer;
	private Context _context;
	private STrackerApp _app;
	
	/**
	 * @param context
	 */
	public SearchByNameRequest(Context context) {
		super(context);
		_context = context;
		_app = (STrackerApp) ((Activity) context).getApplication();
		_serializer = (TvShowSynopseSerializer) JSONLocator.getInstance().getSerializer(TvShowSynopse.class);
	}

	/** 
	 * (non-Javadoc)
	 * @see src.stracker.asynchttp.AbstractAsyncHttp#onSuccessHook(java.lang.String)
	 */
	@Override
	protected void onSuccessHook(String response) {
		ArrayList<TvShowSynopse> list = _serializer.deserialize(response);
		if (list.size() == 0) onErrorHook(new NoHttpResponseException(""), "");
		if (list.size() == 1){
			TvShowSynopse tvshow = list.get(0);
			new TvShowRequest(_context).execute(_app.getURL()+tvshow.getUri());
		}
		else {
			Intent intent = new Intent(_context,ResultActivity.class);
			intent.putExtra("type", "TVSHOWSYNOPSE");
			intent.putExtra("list", list);
			intent.putExtra("genre", "Search Results...");
			_context.startActivity(intent);
		}
	}

	/** 
	 * (non-Javadoc)
	 * @see src.stracker.asynchttp.AbstractAsyncHttp#onErrorHook(java.lang.Throwable, java.lang.String)
	 */
	@Override
	protected void onErrorHook(Throwable e, String response) {
		Toast.makeText(_context, "We don't know this TvShow yet!", Toast.LENGTH_SHORT).show(); 
	}
}
