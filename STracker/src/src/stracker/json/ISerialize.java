package src.stracker.json;

/**
 * This interface represents a contract for all classes who wants to deserialize a json representation to a generic object.
 */
public interface ISerialize<T> {
	public T deserialize(String json);
}
