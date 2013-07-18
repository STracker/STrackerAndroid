package src.stracker.json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import src.stracker.model.Comment;

public class CommentsSerializer implements ISerialize<ArrayList<Comment>> {

	@Override
	public ArrayList<Comment> deserialize(String json) {
		ArrayList<Comment> comments = new ArrayList<Comment>();
		try {
			JSONArray jsonArray = new JSONArray(json);
			for (int i = 0; i < jsonArray.length(); i++) {
		        JSONObject jObj = jsonArray.getJSONObject(i);
		        comments.add(new Comment(
									jObj.getString("Id"),
									jObj.getString("Body"),
									jObj.getString("Uri"),
									jObj.getJSONObject("User").getString("Name"),
									jObj.getJSONObject("User").getString("Uri"),
									jObj.getJSONObject("User").getString("Id")
							));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return comments;
	}
}
