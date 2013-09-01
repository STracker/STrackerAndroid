package src.stracker.model;

import java.util.ArrayList;
import android.os.Parcel;
import android.os.Parcelable;

public class Subscription implements Parcelable{

	private TvShowSynopse TvShowSynopse;
	private ArrayList<EpisodeSynopse> WatchedEpisodes;
	
	public Subscription(TvShowSynopse tvshow, ArrayList<EpisodeSynopse> episodes){
		TvShowSynopse = tvshow;
		WatchedEpisodes = episodes;
	}
	
	public Subscription(Parcel in){
		readFromParcel(in);
	}
	
	public TvShowSynopse getTvShowSynope() {
		return TvShowSynopse;
	}

	public void setTvShowSynopses(TvShowSynopse tvShowSynopse) {
		TvShowSynopse = tvShowSynopse;
	}

	public ArrayList<EpisodeSynopse> getWatchedEpisodes() {
		return WatchedEpisodes;
	}

	public void setWatchedEpisodes(ArrayList<EpisodeSynopse> watchedEpisodes) {
		WatchedEpisodes = watchedEpisodes;
	}
	
	public void addEpisode(EpisodeSynopse episode){
		WatchedEpisodes.add(episode);
	}
	
	public void removeEpisode(EpisodeSynopse episode){
		for(EpisodeSynopse synopse : WatchedEpisodes){
			if(synopse.getNumber() == episode.getNumber() && 
				synopse.getSeasonNumber() == episode.getSeasonNumber())
				WatchedEpisodes.remove(synopse);
		}
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flag) {
		dest.writeParcelable(TvShowSynopse, PARCELABLE_WRITE_RETURN_VALUE);
		dest.writeTypedList(WatchedEpisodes);
	}

	public void readFromParcel(Parcel in){
		setTvShowSynopses((TvShowSynopse) in.readParcelable(TvShowSynopse.class.getClassLoader()));
		WatchedEpisodes = new ArrayList<EpisodeSynopse>();
		in.readTypedList(WatchedEpisodes, EpisodeSynopse.CREATOR);
	}

	public static final Parcelable.Creator<Subscription> CREATOR =
		    new Parcelable.Creator<Subscription>() {
	            public Subscription createFromParcel(Parcel in) {
	                return new Subscription(in);
	            }

	            public Subscription[] newArray(int size) {
	                return new Subscription[size];
	            }
	        };
}
