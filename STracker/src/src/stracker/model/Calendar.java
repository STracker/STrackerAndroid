package src.stracker.model;

import java.util.ArrayList;

public class Calendar {
	
	private String date;
	private ArrayList<CalendarEntry> entries;
	
	public Calendar(String date) {
		this.date = date;
		this.entries = new ArrayList<Calendar.CalendarEntry>();
	}
	
	public String getDate() {
		return date;
	}
	
	public ArrayList<CalendarEntry> getEntries(){
		return entries;
	}
	
	public void createEntrie(TvShowSynopse tvshow, ArrayList<EpisodeSynopse> episodes){
		entries.add(new CalendarEntry(tvshow, episodes));
	}
	
	public class CalendarEntry{
		private TvShowSynopse tvshow;
		private ArrayList<EpisodeSynopse> episodes;
		
		public CalendarEntry(TvShowSynopse tvshow,
				ArrayList<EpisodeSynopse> episodes) {
			this.tvshow = tvshow;
			this.episodes = episodes;
		}

		public TvShowSynopse getTvShow() {
			return tvshow;
		}
		
		public ArrayList<EpisodeSynopse> getEpisodes() {
			return episodes;
		}
	}
}
