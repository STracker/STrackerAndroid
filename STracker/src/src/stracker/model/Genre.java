package src.stracker.model;

import java.util.ArrayList;

public class Genre {

	private String id;
	private ArrayList<TvShowSynopse> tvShows;
	
	public Genre(String id, ArrayList<TvShowSynopse> tvShows) {
		this.id = id;
		this.tvShows = tvShows;
	}
	
	public String getId() {
		return id;
	}
	
	public ArrayList<TvShowSynopse> getTvShows() {
		return tvShows;
	}
}
