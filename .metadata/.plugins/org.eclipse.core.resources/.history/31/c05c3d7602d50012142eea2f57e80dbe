package src.stracker.json;

import org.json.JSONException;
import org.json.JSONObject;
import src.stracker.model.Episode;

public class EpisodeSerializer implements ISerialize<Episode> {

	@Override
	public Episode deserialize(String json) {
		Episode episode = null;
		try {
			JSONObject jObj = new JSONObject(json);
			episode = new Episode(
					jObj.getString("Name"),
					jObj.getInt("EpisodeNumber"),
					jObj.getString("Date"),
					jObj.getString("TvShowId"),
					jObj.getString("Description"),
					jObj.getJSONObject("Poster").getString("ImageUrl"),
					jObj.getInt("SeasonNumber")
					);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return episode;
	}
}
