package src.stracker.json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import src.stracker.model.*;

/**
 * @author diogomatos
 * This class represent the serializer to an array of tv show synopses
 */
public class TvShowSynopseSerializer implements ISerialize<ArrayList<TvShowSynopse>> {
  
	/**
	 * @see src.stracker.json.ISerialize#deserialize(java.lang.String)
	 */
	@Override
	public ArrayList<TvShowSynopse> deserialize(String json) {
		ArrayList<TvShowSynopse> tvShows = new ArrayList<TvShowSynopse>();
		try {
			JSONArray jsonArray = new JSONArray(json);
			for (int i = 0; i < jsonArray.length(); i++) {
		        JSONObject jObj = jsonArray.getJSONObject(i);
			    tvShows.add(new TvShowSynopse(
					jObj.getString("Id"),
					jObj.getString("Name"),
					jObj.getString("Uri"),
					jObj.getString("Poster")
					));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return tvShows;
	}
}
