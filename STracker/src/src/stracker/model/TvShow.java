package src.stracker.model;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class TvShow implements Parcelable{
	
	private String Id;
	private String Name;
	private String Description;
	private String Airday;
	private int Runtime;
	private int Rating;
	private String Url;
	private List<String> Genres;
	
	public TvShow(Parcel in){
		readFromParcel(in);
	}
	
	public TvShow(String id, String name, String description, String airday,
			int runtime, int rating, String url, List<String> genres) {
		super();
		Id = id;
		Name = name;
		Description = description;
		Airday = airday;
		Runtime = runtime;
		Rating = rating;
		Url = url;
		Genres = genres;
	}

	/*
	private List<Artwork> Artworks;
	private List<Actor> Actors;
	private List<Genre> Genres;
	private Person Creator;
	
	public TvShow(String id, String name, String description,
			List<Genre> genres, int rating, List<Artwork> artworks,
			List<Actor> actors, String airday, int runtime, Person creator) {
		super();
		Id = id;
		Name = name;
		Description = description;
		Genres = genres;
		Rating = rating;
		Artworks = artworks;
		Actors = actors;
		Airday = airday;
		Runtime = runtime;
		Creator = creator;
	}
    */
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public int getRating() {
		return Rating;
	}

	public void setRating(int rating) {
		Rating = rating;
	}

	public String getAirday() {
		return Airday;
	}

	public void setAirday(String airday) {
		Airday = airday;
	}

	public int getRuntime() {
		return Runtime;
	}

	public void setRuntime(int runtime) {
		Runtime = runtime;
	}
	
	public List<String> getGenres() {
		return Genres;
	}

	public void setGenres(List<String> genres) {
		Genres = genres;
	}
	/*
	public List<Artwork> getArtworks() {
		return Artworks;
	}

	public void setArtworks(List<Artwork> artworks) {
		Artworks = artworks;
	}

	public List<Actor> getActors() {
		return Actors;
	}

	public void setActors(List<Actor> actors) {
		Actors = actors;
	}
	
	public List<Genre> getGenres() {
		return Genres;
	}

	public void setGenres(List<Genre> genres) {
		Genres = genres;
	}

	public Person getCreator() {
		return Creator;
	}

	public void setCreator(Person creator) {
		Creator = creator;
	}
	*/

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(Id);
		dest.writeString(Name);
		dest.writeString(Description);
		dest.writeString(Airday);
		dest.writeInt(Runtime);
		dest.writeInt(Rating);
		dest.writeString(Url);
	    dest.writeStringList(Genres);
	}	
	
	public void readFromParcel(Parcel in){
		setId(in.readString());
		setName(in.readString());
		setDescription(in.readString());
		setAirday(in.readString());
		setRuntime(in.readInt());
		setRating(in.readInt());
		setUrl(in.readString());
		List<String> stringList = new ArrayList<String>();
		in.readStringList(stringList);
		setGenres(stringList);
	}
	
	public static final Parcelable.Creator<TvShow> CREATOR =
		    new Parcelable.Creator<TvShow>() {
	            public TvShow createFromParcel(Parcel in) {
	                return new TvShow(in);
	            }
	 
	            public TvShow[] newArray(int size) {
	                return new TvShow[size];
	            }
	        };
}
