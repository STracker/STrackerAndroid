package src.stracker.json;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import src.stracker.model.Person;

public class PersonSerializer implements ISerialize<ArrayList<Person>> {

	@Override
	public ArrayList<Person> deserialize(String json) {
		ArrayList<Person> persons = new ArrayList<Person>();
		try{
			JSONArray array = new JSONArray(json);
			for (int i = 0; i < array.length(); i++){
				JSONObject jObj = array.getJSONObject(i);
				persons.add(new Person(jObj.getString("Name")));
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return persons;
	}
}
