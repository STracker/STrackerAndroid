package src.stracker.json;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import src.stracker.model.GenreSynopse;

/**
 * @author diogomatos
 * This class represent the serializer to an array of genre synopses
 */
public class GenresSerializer implements ISerialize<ArrayList<GenreSynopse>> {

	/**
	 * (non-Javadoc)
	 * @see src.stracker.json.ISerialize#deserialize(java.lang.String)
	 */
	@Override
	public ArrayList<GenreSynopse> deserialize(String json) {
		ArrayList<GenreSynopse> genres = new ArrayList<GenreSynopse>();
		try {
			JSONArray jsonArray = new JSONArray(json);
			for (int i = 0; i < jsonArray.length(); i++) {
		        JSONObject jObj = jsonArray.getJSONObject(i);
			    genres.add(new GenreSynopse(
					jObj.getString("Id"),
					jObj.getString("Uri")
					));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return genres;
	}
}
