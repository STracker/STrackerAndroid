package src.stracker.json;

public class DummySerializer implements ISerialize<String> {

	@Override
	public String deserialize(String json) {
		return json;
	}

}
