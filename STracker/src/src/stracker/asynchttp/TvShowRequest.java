package src.stracker.asynchttp;
import src.stracker.json.JSONLocator;
import src.stracker.json.TvShowSerializer;
import src.stracker.model.TvShow;
import android.content.Context;

/**
 * @author diogomatos
 * This implementation represents a request to a television show
 */
public class TvShowRequest extends AbstractAsyncHttp {
	
	private TvShowSerializer _tvShowSerializer;
	
	/**
	 * The constructor of the television show request 
	 * @param context - activity context
	 * @param runnable - callback to be invoked
	 */
	public TvShowRequest(Context context, MyRunnable runnable) {
		super(context,runnable);
		_tvShowSerializer = (TvShowSerializer) JSONLocator.getInstance().getSerializer(TvShow.class);
	}

	/**
	 * @see src.stracker.asynchttp.AbstractAsyncHttp#onSuccessHook(java.lang.String)
	 * @param response - string with the Http Response 
	 */
	@Override
	protected void onSuccessHook(String response) {
		TvShow tvshow = _tvShowSerializer.deserialize(response);
		_runnable.runWithArgument(tvshow);
	}
}
