package src.stracker.json;

import org.json.JSONException;
import org.json.JSONObject;
import src.stracker.model.UserSynopse;

/**
 * @author diogomatos
 * This class represent the serializer to an user synopse object
 */
public class UserSynopseSerializer implements ISerialize<UserSynopse> {

	/**
	 * @see src.stracker.json.ISerialize#deserialize(java.lang.String)
	 */
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
