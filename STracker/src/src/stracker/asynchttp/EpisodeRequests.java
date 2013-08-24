package src.stracker.asynchttp;

import android.content.Context;
import src.stracker.json.EpisodeSerializer;
import src.stracker.json.JSONLocator;
import src.stracker.model.Episode;

public class EpisodeRequests {

	private static EpisodeSerializer episodeSerializer = (EpisodeSerializer) JSONLocator.getInstance().getSerializer(Episode.class);
	
	public static void getEpisode(Context context, MyRunnable runnable, String uri){
		AsyncHttpRequest.get(context, runnable, episodeSerializer, uri);
	}
}
