package src.stracker.json;

import org.json.JSONException;
import org.json.JSONObject;
import src.stracker.model.Episode;

/**
 * @author diogomatos
 * This class represent the serializer to an episode of a television show
 */
public class EpisodeSerializer implements ISerialize<Episode> {

	private PersonSerializer _serializer = new PersonSerializer();
	
	/**
	 * @see src.stracker.json.ISerialize#deserialize(java.lang.String)
	 */
	@Override
	public Episode deserialize(String json) {
		Episode episode = null;
		try {
			JSONObject jObj = new JSONObject(json);
			episode = new Episode(
					jObj.getString("Name"),
					jObj.getJSONObject("Id").getInt("EpisodeNumber"),
					jObj.getString("Date"),
					jObj.getJSONObject("Id").getString("TvShowId"),
					jObj.getString("Description"),
					jObj.getString("Poster"),
					jObj.getJSONObject("Id").getInt("SeasonNumber"),
					_serializer.deserialize(jObj.getJSONArray("Directors").toString()),
					_serializer.deserialize(jObj.getJSONArray("GuestActors").toString())
					);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return episode;
	}
}
