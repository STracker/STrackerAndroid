package src.stracker.json;

import org.json.JSONException;
import org.json.JSONObject;
import src.stracker.model.*;

/**
 * @author diogomatos
 * This class represent the serializer to a television show
 */
public class TvShowSerializer implements ISerialize<TvShow> {

	private SeasonSynopseSerializer _seasonSerializer = new SeasonSynopseSerializer();
    private GenresSerializer _genreSerializer = new GenresSerializer();
    private ActorSerializer _actorSerializer = new ActorSerializer();
    
    /**
     * @see src.stracker.json.ISerialize#deserialize(java.lang.String)
     */
	@Override
	public TvShow deserialize(String json) {
		TvShow tvShow = null;
		try {
			JSONObject jObj = new JSONObject(json);
			tvShow = new TvShow(
					jObj.getString("Id"),
					jObj.getString("Name"),
					jObj.getString("Description"),
					jObj.getString("AirDay"),
					jObj.getInt("Runtime"),
					jObj.getString("AirTime"),
					jObj.getString("FirstAired"),
					jObj.getString("Poster"),
					_genreSerializer.deserialize(jObj.getJSONArray("Genres").toString()),
					_seasonSerializer.deserialize(jObj.getJSONArray("Seasons").toString()),
					_actorSerializer.deserialize(jObj.getJSONArray("Actors").toString())
					);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return tvShow;
	}
}
