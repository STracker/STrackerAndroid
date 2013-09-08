package src.stracker.json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import src.stracker.model.Subscription;
import src.stracker.model.TvShowSynopse;

/**
 * @author diogomatos
 * This class represent the serializer to an array of subscriptions
 */
public class SubscriptionSerializer implements ISerialize<ArrayList<Subscription>> {

	private EpisodeSynopseSerializer _episodeSerializer = new EpisodeSynopseSerializer();
	
	/**
	 * @see src.stracker.json.ISerialize#deserialize(java.lang.String)
	 */
	@Override
	public ArrayList<Subscription> deserialize(String json) {
		ArrayList<Subscription> subscriptions = new ArrayList<Subscription>();
		try{
			JSONArray jsonArray = new JSONArray(json);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jObj = jsonArray.getJSONObject(i);
				subscriptions.add(
						new Subscription(new TvShowSynopse(
									jObj.getJSONObject("TvShow").getString("Id"),
									jObj.getJSONObject("TvShow").getString("Name"),
									jObj.getJSONObject("TvShow").getString("Uri"),
									jObj.getJSONObject("TvShow").getString("Poster")
														  ),
								_episodeSerializer.deserialize(jObj.getJSONArray("EpisodesWatched").toString())));
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return subscriptions;
	}

}
