package src.stracker.json;

import org.json.JSONArray;
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
					jObj.getInt("Rating"),
					jObj.getString("TvShowId"),
					jObj.getString("Description"),
					getArtwork(jObj.getJSONArray("Artworks")),
					jObj.getInt("SeasonNumber")
					);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return episode;
	}
	
	private String getArtwork(JSONArray arr){
		String ret = "";
		try {
			for(int i = 0; i < arr.length();){
				return arr.getJSONObject(i).getString("ImageUrl");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return ret;
	}
}
