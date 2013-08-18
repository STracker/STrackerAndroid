package src.stracker.json;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import src.stracker.model.EpisodeSynopse;

/**
 * @author diogomatos
 * This class represent the serializer to an array of episode synopses
 */
public class CalendarSerializer implements ISerialize<ArrayList<EpisodeSynopse>> {

	/**
	 * (non-Javadoc)
	 * @see src.stracker.json.ISerialize#deserialize(java.lang.String)
	 */
	@Override
	public ArrayList<EpisodeSynopse> deserialize(String json) {
		ArrayList<EpisodeSynopse> episodes = new ArrayList<EpisodeSynopse>();
		try {
			JSONObject jsonObj = new JSONObject(json);
			JSONArray jsonArray = jsonObj.getJSONArray("EpisodeSynopsis");
			for (int i = 0; i < jsonArray.length(); i++) {
		        JSONObject jObj = jsonArray.getJSONObject(i);
		        episodes.add(new EpisodeSynopse(
					jObj.getInt("EpisodeNumber"),
					jObj.getString("Name"),
					jObj.getString("Uri"),
					jObj.getString("Date"),
					jObj.getInt("SeasonNumber")
					));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return episodes;
	}
}
