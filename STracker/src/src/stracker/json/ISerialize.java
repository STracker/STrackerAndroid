package src.stracker.json;

/**
 * This interface represents a contract for all classes who wants to deserialize a json representation to a generic object.
 */
public interface ISerialize<T> {
	
	/**
	 * This method deserialize a JSON response to a object model
	 * @param json - json response
	 * @return object
	 */
	public T deserialize(String json);
}
