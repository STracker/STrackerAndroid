package src.stracker.json;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import src.stracker.model.SeasonSynopse;

/**
 * @author diogomatos
 * This class represent the serializer to an array of season synopses
 */
public class SeasonSynopseSerializer implements ISerialize<ArrayList<SeasonSynopse>> {

	/**
	 * @see src.stracker.json.ISerialize#deserialize(java.lang.String)
	 */
	@Override
	public ArrayList<SeasonSynopse> deserialize(String json) {
		ArrayList<SeasonSynopse> seasons = new ArrayList<SeasonSynopse>();
		try {
			JSONArray jsonArray = new JSONArray(json);
			for (int i = 0; i < jsonArray.length(); i++) {
		        JSONObject jObj = jsonArray.getJSONObject(i);
			    seasons.add(new SeasonSynopse(
					jObj.getJSONObject("Id").getInt("SeasonNumber"),
					jObj.getString("Uri"),
					jObj.getString("Name")
					));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return seasons;
	}
}
