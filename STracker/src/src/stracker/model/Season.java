package src.stracker.model;

import java.util.ArrayList;

public class Season {

	private ArrayList<EpisodeSynopse> episodes;

	public Season(ArrayList<EpisodeSynopse> episodes) {
		super();
		this.episodes = episodes;
	}

	public ArrayList<EpisodeSynopse> getEpisodes() {
		return episodes;
	}
}
