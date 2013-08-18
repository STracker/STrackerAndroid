package src.stracker.asynchttp;

import java.util.ArrayList;
import src.stracker.json.JSONLocator;
import src.stracker.json.TvShowSynopseSerializer;
import src.stracker.model.TvShowSynopse;
import android.content.Context;

/**
 * @author diogomatos
 * This implementation represents a request a search of tv shows by it's genre
 */
public class SearchByGenreRequest extends AbstractAsyncHttp {

	private TvShowSynopseSerializer _serializer;
	protected String _genre;
	
	/**
	 * The constructor of the search by genre 
	 * @param context - activity context
	 * @param runnable - callback to be invoked
	 */
	public SearchByGenreRequest(Context context, MyRunnable runnable) {
		super(context, runnable);
		_serializer = (TvShowSynopseSerializer) JSONLocator.getInstance().getSerializer(TvShowSynopse.class);
	}

	/**
	 * @see src.stracker.asynchttp.AbstractAsyncHttp#onSuccessHook(java.lang.String)
	 * @param response - string with the Http Response 
	 */
	@Override
	protected void onSuccessHook(String response) {
		ArrayList<TvShowSynopse> list = _serializer.deserialize(response);
		_runnable.runWithArgument(list);
	}
}
