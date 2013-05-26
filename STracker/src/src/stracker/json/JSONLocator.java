package src.stracker.json;

import java.util.Dictionary;
import java.util.Hashtable;

import src.stracker.model.TvShow;
import src.stracker.model.TvShowSynopse;

public class JSONLocator {

	private Dictionary<Class<?>, ISerialize<?>> _dictionary;
	private static JSONLocator _singleton;
	
	private JSONLocator(){
		_dictionary = new Hashtable<Class<?>, ISerialize<?>>();
		_dictionary.put(TvShow.class, new TvShowSerializer());
		_dictionary.put(TvShowSynopse.class, new TvShowSynopseSerializer());
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
