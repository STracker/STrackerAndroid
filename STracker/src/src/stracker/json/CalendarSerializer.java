package src.stracker.json;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import src.stracker.model.Calendar;
import src.stracker.model.TvShowSynopse;

/**
 * @author diogomatos
 * This class represent the serializer to an array of episode synopses
 */
public class CalendarSerializer implements ISerialize<ArrayList<Calendar>> {

	private EpisodeSynopseSerializer _episodeSerializer = new EpisodeSynopseSerializer();
	
	/**
	 * @see src.stracker.json.ISerialize#deserialize(java.lang.String)
	 */
	@Override
	public ArrayList<Calendar> deserialize(String json) {
		ArrayList<Calendar> calendarEntries = new ArrayList<Calendar>();
		try {
			JSONArray jsonArray = new JSONArray(json);
			for (int i = 0; i < jsonArray.length(); i++) {
		        JSONObject jObj = jsonArray.getJSONObject(i);
		        Calendar calendar = new Calendar(jObj.getString("Date"));
		        JSONArray entriesArray = jObj.getJSONArray("Entries");
		        for(int j = 0; j <entriesArray.length(); j++){
		        	JSONObject jsonEntrie = entriesArray.getJSONObject(j);
		        	calendar.createEntrie(
		        			new TvShowSynopse(
		        					jsonEntrie.getJSONObject("TvShow").getString("Id"),
		        					jsonEntrie.getJSONObject("TvShow").getString("Name"),
		        					jsonEntrie.getJSONObject("TvShow").getString("Uri"),
		        					jsonEntrie.getJSONObject("TvShow").getString("Poster")
		        					), 
		        			_episodeSerializer.deserialize(jsonEntrie.getJSONArray("Episodes").toString()));
		        }
		        calendarEntries.add(calendar);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return calendarEntries;
	}
}
