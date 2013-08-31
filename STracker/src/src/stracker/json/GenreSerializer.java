package src.stracker.json;

import org.json.JSONObject;
import src.stracker.model.Genre;

public class GenreSerializer implements ISerialize<Genre>{

	private TvShowSynopseSerializer serializer = new TvShowSynopseSerializer();
	
	@Override
	public Genre deserialize(String json) {
		Genre genre = null;
		try{
			JSONObject jOBj = new JSONObject(json);
			genre = new Genre( jOBj.getString("Id")
								, serializer.deserialize(jOBj.getJSONArray("Tvshows").toString()));
		}catch(Exception e){
			e.printStackTrace();
		}
		return genre;
	}
}
