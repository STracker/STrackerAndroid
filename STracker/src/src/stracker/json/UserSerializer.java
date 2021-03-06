package src.stracker.json;

import org.json.JSONException;
import org.json.JSONObject;

import src.stracker.model.User;

/**
 * @author diogomatos
 * This class represent the serializer to an user object
 */
public class UserSerializer implements ISerialize<User> {

	private FriendsSerializer _friendSerializer = new FriendsSerializer();
	private SubscriptionSerializer _subscriptionSerializer = new SubscriptionSerializer();
	private SuggestionSerializer _suggestionSerializer = new SuggestionSerializer();
	
	/**
	 * @see src.stracker.json.ISerialize#deserialize(java.lang.String)
	 */
	@Override
	public User deserialize(String json) {
		User user = null;
		try {
			JSONObject jObj = new JSONObject(json);
			user = new User(
								jObj.getString("Name"), 
								jObj.getString("Id"), 
								jObj.getString("Email"),
								jObj.getInt("Version"),
								_subscriptionSerializer.deserialize(jObj.getJSONArray("Subscriptions").toString()),
								_friendSerializer.deserialize(jObj.getJSONArray("Friends").toString()),
								_friendSerializer.deserialize(jObj.getJSONArray("FriendRequests").toString()),
								_suggestionSerializer.deserialize(jObj.getJSONArray("Suggestions").toString())
					);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return user;
	}
}
