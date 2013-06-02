package src.stracker.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import src.stracker.model.*;

public class TvShowSerializer implements ISerialize<TvShow> {

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
					//getGenres(jObj.getJSONArray("Genres")),
					jObj.getInt("Rating"),
					getPoster(jObj.getJSONArray("Artworks"))
					//null,
					//null
					);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return tvShow;
	}
	
	/*
	private List<Genre> getGenres(JSONArray arr){
		List<Genre> ret = new LinkedList<Genre>();
		try {
			for(int i = 0; i < arr.length(); i++){
				ret.add(Genre.values()[arr.getInt(i)]);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return ret;
	}
	*/
	private String getPoster(JSONArray arr){
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
