package src.stracker.asynchttp;

import src.stracker.json.JSONLocator;
import src.stracker.json.RatingsSerializer;
import src.stracker.model.Ratings;
import android.content.Context;

/**
 * @author diogomatos
 * This implementation represents a request to a rating of a television show
 */
public class TvShowRatingRequest extends AbstractAsyncHttp {

	private RatingsSerializer _serializer;
	
	/**
	 * The constructor of the television show rating request 
	 * @param context - activity context
	 * @param runnable - callback to be invoked
	 */
	public TvShowRatingRequest(Context context, MyRunnable runnable) {
		super(context, runnable);
		_serializer = (RatingsSerializer) JSONLocator.getInstance().getSerializer(Ratings.class);
	}

	/**
	 * @see src.stracker.asynchttp.AbstractAsyncHttp#onSuccessHook(java.lang.String)
	 * @param response - string with the Http Response 
	 */
	@Override
	protected void onSuccessHook(String response) {
		Ratings rating = _serializer.deserialize(response);
		_runnable.runWithArgument(rating);
	}
}
