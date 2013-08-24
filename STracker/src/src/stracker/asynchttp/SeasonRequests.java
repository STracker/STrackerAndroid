package src.stracker.asynchttp;

import android.content.Context;
import src.stracker.json.EpisodeSynopseSerializer;
import src.stracker.json.JSONLocator;
import src.stracker.model.EpisodeSynopse;

public class SeasonRequests {

	private static EpisodeSynopseSerializer episodeSynopseSerializer = (EpisodeSynopseSerializer) JSONLocator.getInstance().getSerializer(EpisodeSynopse.class);
	
	public static void getSeasonEpisodes(Context context, MyRunnable runnable, String uri){
		AsyncHttpRequest.get(context, runnable, episodeSynopseSerializer, uri);
	}
}
