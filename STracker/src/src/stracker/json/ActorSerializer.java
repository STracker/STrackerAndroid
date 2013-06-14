package src.stracker.json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import src.stracker.model.Actor;

/**
 * @author diogomatos
 * This class represent the serializer to an array of actors
 */
public class ActorSerializer implements ISerialize<ArrayList<Actor>> {

	/**
	 * (non-Javadoc)
	 * @see src.stracker.json.ISerialize#deserialize(java.lang.String)
	 */
	@Override
	public ArrayList<Actor> deserialize(String json) {
		ArrayList<Actor> actors = new ArrayList<Actor>();
		try {
			JSONArray jsonArray = new JSONArray(json);
			for (int i = 0; i < jsonArray.length(); i++) {
		        JSONObject jObj = jsonArray.getJSONObject(i);
			    actors.add(new Actor(
					jObj.getString("Name"),
					jObj.getString("CharacterName"),
					jObj.getJSONObject("Photo").getString("ImageUrl")
					));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return actors;
	}
}
