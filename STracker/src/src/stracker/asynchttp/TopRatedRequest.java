package src.stracker.asynchttp;

import java.util.ArrayList;
import android.content.Context;
import src.stracker.json.JSONLocator;
import src.stracker.json.TvShowSynopseSerializer;
import src.stracker.model.TvShowSynopse;

/**
 * @author diogomatos
 * This implementation represents a request to the STracker top rated television shows
 */
public class TopRatedRequest extends AbstractAsyncHttp {

	private TvShowSynopseSerializer _serializer;
	
	/**
	 * The constructor of the top rated request 
	 * @param context - activity context
	 * @param runnable - callback to be invoked
	 */
	public TopRatedRequest(Context context, MyRunnable runnable) {
		super(context,runnable);
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
