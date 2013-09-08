package src.stracker.json;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import src.stracker.model.Suggestion;
import src.stracker.model.TvShowSynopse;

/**
 * @author diogomatos
 * This class represent the serializer to an array of suggestions
 */
public class SuggestionSerializer implements ISerialize<ArrayList<Suggestion>> {

	private UserSynopseSerializer userSerializer = new UserSynopseSerializer();
	
	/**
	 * @see src.stracker.json.ISerialize#deserialize(java.lang.String)
	 */
	@Override
	public ArrayList<Suggestion> deserialize(String json) {
		ArrayList<Suggestion> suggestion = new ArrayList<Suggestion>();
		try {
			JSONArray jsonArray = new JSONArray(json);
			for (int i = 0; i < jsonArray.length(); i++) {
		        JSONObject jObj = jsonArray.getJSONObject(i);
		        suggestion.add(new Suggestion(userSerializer.deserialize(jObj.getString("User")),
			    new TvShowSynopse(
					jObj.getJSONObject("TvShow").getString("Id"),
					jObj.getJSONObject("TvShow").getString("Name"),
					jObj.getJSONObject("TvShow").getString("Uri"),
					jObj.getJSONObject("TvShow").getString("Poster")
					)
		        					        		));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return suggestion;
	}
}
