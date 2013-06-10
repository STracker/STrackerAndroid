package src.stracker.model;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class TvShow implements Parcelable{
	
	private String Id;
	private String Name;
	private String Description;
	private String Airday;
	private int Runtime;
	private String AirTime;
	private String FirstAired;
	private String PosterUrl;
	private ArrayList<GenreSynopse> Genres;
	private ArrayList<SeasonSynopse> Seasons;
	private ArrayList<Actor> Actors;
	
	public TvShow(Parcel in){
		readFromParcel(in);
	}
	
	public TvShow(String id, String name, String description, String airday, int runtime, 
			String airtime, String firstaired, String url, ArrayList<GenreSynopse> genres, 
			ArrayList<SeasonSynopse> seasons, ArrayList<Actor> actors) {
		super();
		Id = id;
		Name = name;
		Description = description;
		Airday = airday;
		Runtime = runtime;
		AirTime = airtime;
		FirstAired = firstaired;
		PosterUrl = url;
		Genres = genres;
		Seasons = seasons;
		Actors = actors;
	}

	public ArrayList<Actor> getActors() {
		return Actors;
	}

	public void setActors(ArrayList<Actor> actors) {
		Actors = actors;
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

	public String getAirTime() {
		return AirTime;
	}

	public void setAirTime(String airTime) {
		AirTime = airTime;
	}

	public String getFirstAired() {
		return FirstAired;
	}

	public void setFirstAired(String firstAired) {
		FirstAired = firstAired;
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
	
	public ArrayList<GenreSynopse> getGenres() {
		return Genres;
	}

	public void setGenres(ArrayList<GenreSynopse> genres) {
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

	public ArrayList<SeasonSynopse> getSeasons() {
		return Seasons;
	}

	public void setSeasons(ArrayList<SeasonSynopse> seasons) {
		Seasons = seasons;
	}

	public String getPosterUrl() {
		return PosterUrl;
	}

	public void setPosterUrl(String url) {
		PosterUrl = url;
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
		dest.writeString(AirTime);
		dest.writeString(FirstAired);
		dest.writeString(PosterUrl);
	    dest.writeTypedList(Genres);
	    dest.writeTypedList(Seasons);
	    dest.writeTypedList(Actors);
	}	
	
	public void readFromParcel(Parcel in){
		setId(in.readString());
		setName(in.readString());
		setDescription(in.readString());
		setAirday(in.readString());
		setRuntime(in.readInt());
		setAirTime(in.readString());
		setFirstAired(in.readString());
		setPosterUrl(in.readString());
		//Initialize list before passing the elements
		Genres = new ArrayList<GenreSynopse>();
		in.readTypedList(Genres, GenreSynopse.CREATOR);
		Seasons = new ArrayList<SeasonSynopse>();
		in.readTypedList(Seasons, SeasonSynopse.CREATOR);
		Actors = new ArrayList<Actor>();
		in.readTypedList(Actors, Actor.CREATOR);
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
