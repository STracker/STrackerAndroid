package src.stracker.json;

import org.json.JSONException;
import org.json.JSONObject;
import src.stracker.model.Ratings;

/**
 * @author diogomatos
 * This class represent the serializer to a rating object
 */
public class RatingsSerializer implements ISerialize<Ratings> {

	/**
	 * @see src.stracker.json.ISerialize#deserialize(java.lang.String)
	 */
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
