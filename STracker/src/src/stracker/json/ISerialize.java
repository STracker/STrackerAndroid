package src.stracker.json;

public interface ISerialize<T> {
	public String serialize(T object);
	public T deserialize(String json);
}
