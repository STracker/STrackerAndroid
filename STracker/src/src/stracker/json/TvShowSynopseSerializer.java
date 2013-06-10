package src.stracker.json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import src.stracker.model.*;

public class TvShowSynopseSerializer implements ISerialize<ArrayList<TvShowSynopse>> {
  
	@Override
	public ArrayList<TvShowSynopse> deserialize(String json) {
		ArrayList<TvShowSynopse> tvShows = new ArrayList<TvShowSynopse>();
		try {
			JSONArray jsonArray = new JSONObject(json).getJSONArray("TvshowsSynopses");
			for (int i = 0; i < jsonArray.length(); i++) {
		        JSONObject jObj = jsonArray.getJSONObject(i);
			    tvShows.add(new TvShowSynopse(
					jObj.getString("Id"),
					jObj.getString("Name"),
					jObj.getString("Uri"),
					jObj.getJSONObject("Poster").getString("ImageUrl")
					));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return tvShows;
	}
}
