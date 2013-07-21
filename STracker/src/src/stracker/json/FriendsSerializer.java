package src.stracker.json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import src.stracker.model.UserSynopse;

public class FriendsSerializer implements ISerialize<ArrayList<UserSynopse>> {

	@Override
	public ArrayList<UserSynopse> deserialize(String json) {
		ArrayList<UserSynopse> friends = new ArrayList<UserSynopse>();
		try {
			JSONArray jsonArray = new JSONArray(json);
			for (int i = 0; i < jsonArray.length(); i++) {
		        JSONObject jObj = jsonArray.getJSONObject(i);
		        friends.add(new UserSynopse(
									jObj.getString("Name"),
									jObj.getString("Id"),
									jObj.getString("Uri")
									));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return friends;
	}
}
