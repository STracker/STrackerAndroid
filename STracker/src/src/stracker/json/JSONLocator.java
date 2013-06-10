package src.stracker.json;

import java.util.Dictionary;
import java.util.Hashtable;

import src.stracker.model.Actor;
import src.stracker.model.Episode;
import src.stracker.model.EpisodeSynopse;
import src.stracker.model.GenreSynopse;
import src.stracker.model.SeasonSynopse;
import src.stracker.model.TvShow;
import src.stracker.model.TvShowSynopse;

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
	}
	
	public ISerialize<?> getSerializer(Class<?> cls){
		return _dictionary.get(cls);
	}
	
	public static JSONLocator getInstance(){
		if (_singleton == null)
			_singleton = new JSONLocator();
		return _singleton;
	}
}
