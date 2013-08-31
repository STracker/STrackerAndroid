package src.stracker.asynchttp;

import android.content.Context;
import src.stracker.json.JSONLocator;
import src.stracker.json.SeasonSerializer;
import src.stracker.model.Season;

/**
 * @author diogomatos
 * This class have all the HTTP requests for seasons.
 */
public class SeasonRequests {

	private static SeasonSerializer seasonSerializer = (SeasonSerializer) JSONLocator.getInstance().getSerializer(Season.class);
	
	/**
	 * This method represents a request the season episodes.
	 * @param context - context of the Activity where the request occur
	 * @param runnable - callback that will be called after the HTTP request
	 * @param uri - URI of the resource
	 */
	public static void getSeasonEpisodes(Context context, MyRunnable runnable, String uri){
		AsyncHttpRequest.get(context, runnable, seasonSerializer, uri);
	}
}
