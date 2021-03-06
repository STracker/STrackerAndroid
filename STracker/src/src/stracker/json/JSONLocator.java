package src.stracker.json;

import java.util.Dictionary;
import java.util.Hashtable;

import src.stracker.model.Calendar;
import src.stracker.model.Comment;
import src.stracker.model.Actor;
import src.stracker.model.Episode;
import src.stracker.model.EpisodeSynopse;
import src.stracker.model.Genre;
import src.stracker.model.Season;
import src.stracker.model.User;
import src.stracker.model.GenreSynopse;
import src.stracker.model.Ratings;
import src.stracker.model.SeasonSynopse;
import src.stracker.model.Subscription;
import src.stracker.model.TvShow;
import src.stracker.model.TvShowSynopse;
import src.stracker.model.UserSynopse;

/**
 * This class represents a singleton object that have all the Json serializers.
 */
public class JSONLocator {

	private Dictionary<Class<?>, ISerialize<?>> _dictionary;
	private static JSONLocator _singleton;
	
	private JSONLocator(){
		_dictionary = new Hashtable<Class<?>, ISerialize<?>>();		
		_dictionary.put(TvShowSynopse.class,  new TvShowSynopseSerializer());
		_dictionary.put(SeasonSynopse.class,  new SeasonSynopseSerializer());
		_dictionary.put(EpisodeSynopse.class, new EpisodeSynopseSerializer());
		_dictionary.put(Episode.class,        new EpisodeSerializer());
		_dictionary.put(GenreSynopse.class,   new GenresSerializer());
		_dictionary.put(Actor.class,          new ActorSerializer());
		_dictionary.put(TvShow.class,         new TvShowSerializer());
		_dictionary.put(Ratings.class,        new RatingsSerializer());
		_dictionary.put(Comment.class,        new CommentsSerializer());
		_dictionary.put(User.class, 		  new UserSerializer());
		_dictionary.put(Subscription.class,   new SubscriptionSerializer());
		_dictionary.put(UserSynopse.class,    new FriendsSerializer());
		_dictionary.put(Calendar.class,       new CalendarSerializer());
		_dictionary.put(Genre.class,          new GenreSerializer());
		_dictionary.put(Season.class, 		  new SeasonSerializer());
	}
	
	/**
	 * Return the aggregate of serializers
	 */
	public ISerialize<?> getSerializer(Class<?> cls){
		return _dictionary.get(cls);
	}
	
	/**
	 * Return the locator
	 */
	public static JSONLocator getInstance(){
		if (_singleton == null)
			_singleton = new JSONLocator();
		return _singleton;
	}
}
