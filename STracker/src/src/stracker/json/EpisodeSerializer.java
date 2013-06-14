package src.stracker.json;

import org.json.JSONException;
import org.json.JSONObject;
import src.stracker.model.Episode;

/**
 * @author diogomatos
 * This class represent the serializer to an episode of a tv show
 */
public class EpisodeSerializer implements ISerialize<Episode> {

	/**
	 * (non-Javadoc)
	 * @see src.stracker.json.ISerialize#deserialize(java.lang.String)
	 */
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
