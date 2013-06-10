package src.stracker.json;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import src.stracker.model.EpisodeSynopse;

public class EpisodeSynopseSerializer implements ISerialize<ArrayList<EpisodeSynopse>> {

	@Override
	public ArrayList<EpisodeSynopse> deserialize(String json) {
		ArrayList<EpisodeSynopse> episodes = new ArrayList<EpisodeSynopse>();
		try {
			JSONObject jObject = new JSONObject(json);
			JSONArray jsonArray = jObject.getJSONArray("EpisodeSynopses");
			for (int i = 0; i < jsonArray.length(); i++) {
		        JSONObject jObj = jsonArray.getJSONObject(i);
		        episodes.add(new EpisodeSynopse(
					jObj.getInt("EpisodeNumber"),
					jObj.getString("Name"),
					jObj.getString("Uri")
					));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return episodes;
	}
}
