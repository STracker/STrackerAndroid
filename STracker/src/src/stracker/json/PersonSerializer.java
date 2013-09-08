package src.stracker.json;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import src.stracker.model.Person;
/**
 * @author diogomatos
 * This class represent the serializer to an array of persons
 */
public class PersonSerializer implements ISerialize<ArrayList<Person>> {

	/**
	 * @see src.stracker.json.ISerialize#deserialize(java.lang.String)
	 */
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
