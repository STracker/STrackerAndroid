package src.stracker.json;

import org.json.JSONException;
import org.json.JSONObject;
import src.stracker.model.*;

public class TvShowSerializer implements ISerialize<TvShow> {

	//private SeasonSynopseSerializer _seasonSerializer = (SeasonSynopseSerializer) JSONLocator.getInstance().getSerializer(SeasonSynopse.class);
	//private GenresSerializer _genreSerializer = (GenresSerializer) JSONLocator.getInstance().getSerializer(GenreSynopse.class);
	//private ActorSerializer _actorSerializer = (ActorSerializer) JSONLocator.getInstance().getSerializer(Actor.class);
	
	private SeasonSynopseSerializer _seasonSerializer = new SeasonSynopseSerializer();
    private GenresSerializer _genreSerializer = new GenresSerializer();
    private ActorSerializer _actorSerializer = new ActorSerializer();
    
	@Override
	public TvShow deserialize(String json) {
		TvShow tvShow = null;
		try {
			JSONObject jObj = new JSONObject(json);
			tvShow = new TvShow(
					jObj.getString("TvShowId"),
					jObj.getString("Name"),
					jObj.getString("Description"),
					jObj.getString("AirDay"),
					jObj.getInt("Runtime"),
					jObj.getString("AirTime"),
					jObj.getString("FirstAired"),
					jObj.getJSONObject("Poster").getString("ImageUrl"),
					_genreSerializer.deserialize(jObj.getJSONArray("Genres").toString()),
					_seasonSerializer.deserialize(jObj.getJSONArray("SeasonSynopses").toString()),
					_actorSerializer.deserialize(jObj.getJSONArray("Actors").toString())
					);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return tvShow;
	}
}
