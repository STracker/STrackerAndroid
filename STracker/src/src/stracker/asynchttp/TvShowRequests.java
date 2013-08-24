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

public class TvShowRequests {
	
	private static TvShowSerializer tvShowSerializer = (TvShowSerializer) JSONLocator.getInstance().getSerializer(TvShow.class);
	private static GenresSerializer genresSerializer = (GenresSerializer) JSONLocator.getInstance().getSerializer(GenreSynopse.class);
	private static TvShowSynopseSerializer tvShowSynopseSerializer = (TvShowSynopseSerializer) JSONLocator.getInstance().getSerializer(TvShowSynopse.class);
	
	public static void getTvShow(Context context, MyRunnable runnable, String uri){
		AsyncHttpRequest.get(context, runnable, tvShowSerializer, uri);
	}

	public static void getGenres(Context context, MyRunnable runnable){
		AsyncHttpRequest.get(context, runnable, genresSerializer, context.getString(R.string.uri_genres));
	}
	
	public static void getGenre(Context context, MyRunnable runnable, String uri){
		AsyncHttpRequest.get(context, runnable, tvShowSynopseSerializer, uri);
	}
	
	public static void getTopRated(Context context, MyRunnable runnable){
		AsyncHttpRequest.get(context, runnable, tvShowSynopseSerializer, context.getString(R.string.uri_tvshow_toprated));
	}
	
	public static void getTvShowByName(Context context, MyRunnable runnable, String name){
		AsyncHttpRequest.get(context, runnable, tvShowSynopseSerializer, context.getString(R.string.uri_tvshow_search)+"?name="+name);
	}
}
