package src.stracker.json;

import java.util.Dictionary;
import java.util.Hashtable;

public class JSONLocator {

	private Dictionary<Class<?>, ISerialize<?>> _dictionary;
	
	public JSONLocator(){
		_dictionary = new Hashtable<Class<?>, ISerialize<?>>();
	}
	
	public ISerialize<?> getSerializer(Class<?> cls){
		return _dictionary.get(cls);
	}
}
