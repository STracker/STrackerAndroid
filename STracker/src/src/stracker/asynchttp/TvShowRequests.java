package src.stracker.asynchttp;

import android.content.Context;
import src.stracker.R;
import src.stracker.json.GenresSerializer;
import src.stracker.json.JSONLocator;
import src.stracker.json.TvShowSerializer;
import src.stracker.json.TvShowSynopseSerializer;
import src.stracker.model.GenreSynopse;
import src.stracker.model.TvShow;
import src.stracker.model.TvShowSynopse;

/**
 * @author diogomatos
 * This class have all the HTTP requests for Television Show.
 */
public class TvShowRequests {
	
	private static TvShowSerializer tvShowSerializer = (TvShowSerializer) JSONLocator.getInstance().getSerializer(TvShow.class);
	private static GenresSerializer genresSerializer = (GenresSerializer) JSONLocator.getInstance().getSerializer(GenreSynopse.class);
	private static TvShowSynopseSerializer tvShowSynopseSerializer = (TvShowSynopseSerializer) JSONLocator.getInstance().getSerializer(TvShowSynopse.class);
	
	/**
	 * This method represents a request to a television show.
	 * @param context - context of the Activity where the request occur
	 * @param runnable - callback that will be called after the HTTP request
	 * @param uri - URI of the resource
	 */
	public static void getTvShow(Context context, MyRunnable runnable, String uri){
		AsyncHttpRequest.get(context, runnable, tvShowSerializer, uri);
	}

	/**
	 * This method represents a request to a list of genres.
	 * @param context - context of the Activity where the request occur
	 * @param runnable - callback that will be called after the HTTP request
	 */
	public static void getGenres(Context context, MyRunnable runnable){
		AsyncHttpRequest.get(context, runnable, genresSerializer, context.getString(R.string.uri_genres));
	}
	
	/**
	 * This method represents a request to a genre.
	 * @param context - context of the Activity where the request occur
	 * @param runnable - callback that will be called after the HTTP request
	 * @param uri - URI of the resource
	 */
	public static void getGenre(Context context, MyRunnable runnable, String uri){
		AsyncHttpRequest.get(context, runnable, tvShowSynopseSerializer, uri);
	}
	
	/**
	 * This method represents a request to the top rated television shows.
	 * @param context - context of the Activity where the request occur
	 * @param runnable - callback that will be called after the HTTP request
	 */
	public static void getTopRated(Context context, MyRunnable runnable){
		AsyncHttpRequest.get(context, runnable, tvShowSynopseSerializer, context.getString(R.string.uri_tvshow_toprated));
	}
	
	/**
	 * This method represents a search to a television show.
	 * @param context - context of the Activity where the request occur
	 * @param runnable - callback that will be called after the HTTP request
	 * @param name - search string
	 */
	public static void getTvShowByName(Context context, MyRunnable runnable, String name){
		AsyncHttpRequest.get(context, runnable, tvShowSynopseSerializer, context.getString(R.string.uri_tvshow_search)+name);
	}
}
