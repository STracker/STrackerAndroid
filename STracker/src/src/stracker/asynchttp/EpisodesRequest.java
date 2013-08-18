package src.stracker.asynchttp;

import java.util.ArrayList;
import src.stracker.json.EpisodeSynopseSerializer;
import src.stracker.json.JSONLocator;
import src.stracker.model.EpisodeSynopse;
import android.content.Context;

/**
 * @author diogomatos
 * This implementation represents a request to a list of episodes of a season from a television show
 */
public class EpisodesRequest extends AbstractAsyncHttp {

	private EpisodeSynopseSerializer _serializer;
	
	/**
	 * The constructor of the episodes request 
	 * @param context - activity context
	 * @param runnable - callback to be invoked
	 */
	public EpisodesRequest(Context context, MyRunnable runnable) {
		super(context, runnable);
		_serializer = (EpisodeSynopseSerializer) JSONLocator.getInstance().getSerializer(EpisodeSynopse.class);
	}

	/**
	 * @see src.stracker.asynchttp.AbstractAsyncHttp#onSuccessHook(java.lang.String)
	 * @param response - string with the Http Response 
	 */
	@Override
	protected void onSuccessHook(String response) {
		ArrayList<EpisodeSynopse> list = _serializer.deserialize(response);
		_runnable.runWithArgument(list);
	}
}
