package src.stracker.json;

import org.json.JSONException;
import org.json.JSONObject;
import src.stracker.model.UserSynopse;

public class UserSynopseSerializer implements ISerialize<UserSynopse> {

	@Override
	public UserSynopse deserialize(String json) {
		UserSynopse user = null;
		JSONObject jObj;
		try {
			jObj = new JSONObject(json);
	        user = new UserSynopse(
								jObj.getString("Name"),
								jObj.getString("Id"),
								jObj.getString("Uri")
								);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return user;
	}
}
