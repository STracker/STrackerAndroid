package src.stracker.asynchttp;

import android.content.Context;
import src.stracker.json.EpisodeSerializer;
import src.stracker.json.JSONLocator;
import src.stracker.model.Episode; 

/**
 * @author diogomatos
 * This class have all the HTTP requests for an Episode.
 */
public class EpisodeRequests {

	private static EpisodeSerializer episodeSerializer = (EpisodeSerializer) JSONLocator.getInstance().getSerializer(Episode.class);
	
	/**
	 * This method represents a request to an episode.
	 * @param context - context of the Activity where the request occur
	 * @param runnable - callback that will be called after the HTTP request
	 * @param uri - URI of the resource
	 */
	public static void getEpisode(Context context, MyRunnable runnable, String uri){
		AsyncHttpRequest.get(context, runnable, episodeSerializer, uri);
	}
}
