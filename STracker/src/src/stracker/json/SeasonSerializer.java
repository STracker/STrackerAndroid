package src.stracker.json;

import org.json.JSONObject;
import src.stracker.model.Season;

/**
 * @author diogomatos
 * This class represent the serializer to an array of episode synopses
 */
public class SeasonSerializer implements ISerialize<Season> {

	private EpisodeSynopseSerializer serializer = new EpisodeSynopseSerializer();
	
	/**
	 * @see src.stracker.json.ISerialize#deserialize(java.lang.String)
	 */
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
