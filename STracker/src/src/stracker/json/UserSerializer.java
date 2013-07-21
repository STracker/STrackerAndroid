package src.stracker.json;

import org.json.JSONException;
import org.json.JSONObject;

import src.stracker.model.User;

public class UserSerializer implements ISerialize<User> {

	@Override
	public User deserialize(String json) {
		User user = null;
		try {
			JSONObject jObj = new JSONObject(json);
			user = new User(
								jObj.getString("Name"), 
								jObj.getString("Key"), 
								jObj.getString("Email"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return user;
	}
}
