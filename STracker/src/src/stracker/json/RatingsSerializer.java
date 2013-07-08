package src.stracker.json;

import org.json.JSONException;
import org.json.JSONObject;
import src.stracker.model.Ratings;

public class RatingsSerializer implements ISerialize<Ratings> {

	@Override
	public Ratings deserialize(String json) {
		Ratings rating = null;
		try {
			JSONObject jObj = new JSONObject(json);
			rating = new Ratings(
					jObj.getDouble("Rating"),
					jObj.getInt("Total"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return rating;
	}
}
