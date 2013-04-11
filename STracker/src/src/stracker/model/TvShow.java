package src.stracker.model;

import java.util.List;

public class TvShow {
	
	private String Id;
	private String Name;
	private String Description;
	private List<Genre> Genres;
	private int Rating;
	private List<Artwork> Artworks;
	private List<Actor> Actors;
	private String Airday;
	private int Runtime;
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

	public List<Genre> getGenres() {
		return Genres;
	}

	public void setGenres(List<Genre> genres) {
		Genres = genres;
	}

	public int getRating() {
		return Rating;
	}

	public void setRating(int rating) {
		Rating = rating;
	}

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

	public Person getCreator() {
		return Creator;
	}

	public void setCreator(Person creator) {
		Creator = creator;
	}	
}
