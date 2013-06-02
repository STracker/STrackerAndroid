/**
 * 
 */
package src.stracker.asynchttp;

import java.util.ArrayList;

import src.stracker.ResultActivity;
import src.stracker.json.JSONLocator;
import src.stracker.json.TvShowSynopseSerializer;
import src.stracker.model.TvShowSynopse;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @author diogomatos
 *
 */
public class SearchByGenreRequest extends AbstractAsyncHttp {

	private TvShowSynopseSerializer _serializer;
	protected String _genre;
	
	/**
	 * @param context
	 */
	public SearchByGenreRequest(Context context, String genre) {
		super(context);
		_serializer = (TvShowSynopseSerializer) JSONLocator.getInstance().getSerializer(TvShowSynopse.class);
		_genre = genre;
	}

	/* (non-Javadoc)
	 * @see src.stracker.asynchttp.AbstractAsyncHttp#onSuccessHook(java.lang.String)
	 */
	@Override
	protected void onSuccessHook(String response) {
		try{
			ArrayList<TvShowSynopse> list = _serializer.deserialize(response);
			Intent intent = new Intent(_context,ResultActivity.class);
			intent.putExtra("type", "TVSHOWSYNOPSE");
			intent.putExtra("list", list);
			intent.putExtra("genre", _genre);
			_context.startActivity(intent);
		}catch(Exception e){
			onErrorHook(e,e.getClass().getSimpleName());
		}
	}

	/* (non-Javadoc)
	 * @see src.stracker.asynchttp.AbstractAsyncHttp#onErrorHook(java.lang.Throwable, java.lang.String)
	 */
	@Override
	protected void onErrorHook(Throwable e, String response) {
		Toast.makeText(_context, "No results for selected Genre", Toast.LENGTH_SHORT).show(); 
	}
}
