package src.stracker.asynchttp;

import src.stracker.json.EpisodeSerializer;
import src.stracker.json.JSONLocator;
import src.stracker.model.Episode;
import android.content.Context;

/**
 * @author diogomatos
 * This implementation represents a request to an episode of a television show
 */
public class EpisodeRequest extends AbstractAsyncHttp {

	private EpisodeSerializer _serializer;
	
	/**
	 * The constructor of the episode request 
	 * @param context - activity context
	 * @param runnable - callback to be invoked
	 */
	public EpisodeRequest(Context context, MyRunnable runnable) {
		super(context, runnable);
		_serializer = (EpisodeSerializer) JSONLocator.getInstance().getSerializer(Episode.class);
	}

	/**
	 * @see src.stracker.asynchttp.AbstractAsyncHttp#onSuccessHook(java.lang.String)
	 * @param response - string with the Http Response 
	 */
	@Override
	protected void onSuccessHook(String response) {
		Episode episode = _serializer.deserialize(response);
		_runnable.runWithArgument(episode);
	}
}
