package src.stracker.asynchttp;

import java.util.ArrayList;
import src.stracker.json.JSONLocator;
import src.stracker.json.TvShowSynopseSerializer;
import src.stracker.model.TvShowSynopse;
import android.content.Context;

/**
 * @author diogomatos
 * This implementation represents a search of television shows by it's name
 */
public class SearchByNameRequest extends AbstractAsyncHttp {

	private TvShowSynopseSerializer _serializer;
	
	/**
	 * The constructor of the search television show request 
	 * @param context - activity context
	 * @param runnable - callback to be invoked
	 */
	public SearchByNameRequest(Context context, MyRunnable runnable) {
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
