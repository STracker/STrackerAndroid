package src.stracker.asynchttp;

import java.util.HashMap;

import android.content.Context;

import src.stracker.R;
import src.stracker.STrackerApp;
import src.stracker.json.JSONLocator;
import src.stracker.json.RatingsSerializer;
import src.stracker.model.Episode;
import src.stracker.model.Ratings;
import src.stracker.utils.Utils;

public class RatingRequests {

	private static RatingsSerializer ratingSerializer = (RatingsSerializer) JSONLocator.getInstance().getSerializer(Ratings.class);
	
	public static void getTvShowRating(Context context, MyRunnable runnable, String tvShowId){
		AsyncHttpRequest.get(context, runnable, ratingSerializer, context.getString(R.string.uri_tvshow_rating).replace("tvShowId", tvShowId));
	}
	
	public static void postRating(Context context, MyRunnable runnable, String uri, String rating){
		if(!Utils.checkLogin((STrackerApp) context.getApplicationContext())) return;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("", rating);
		AsyncHttpRequest.authorizedPost(context, runnable, null, uri, params);
	}
	
	public static void getEpisodeRating(Context context, MyRunnable runnable, Episode episode){
		AsyncHttpRequest.get(context, runnable, ratingSerializer, context.getString(R.string.uri_episode_rating)
				.replace("tvShowId", episode.getTvShowId())
				.replace("seasonNumber", episode.getSeasonNumber()+"")
				.replace("episodeNumber", episode.getNumber()+""));
	}
}
