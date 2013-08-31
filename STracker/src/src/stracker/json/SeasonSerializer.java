package src.stracker.json;

import org.json.JSONObject;
import src.stracker.model.Season;

public class SeasonSerializer implements ISerialize<Season> {

	private EpisodeSynopseSerializer serializer = new EpisodeSynopseSerializer();
	
	@Override
	public Season deserialize(String json) {
		Season season = null;
		try{
			season = new Season(serializer.deserialize(new JSONObject(json).getJSONArray("Episodes").toString()));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return season;
	}
}
