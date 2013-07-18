package src.stracker.json;

import org.json.JSONException;
import org.json.JSONObject;

import src.stracker.model.FBUser;

public class UserSerializer implements ISerialize<FBUser> {

	@Override
	public FBUser deserialize(String json) {
		FBUser user = null;
		try {
			JSONObject jObj = new JSONObject(json);
			user = new FBUser(
								jObj.getString("Name"), 
								jObj.getString("Key"), 
								jObj.getString("Email"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return user;
	}
}
