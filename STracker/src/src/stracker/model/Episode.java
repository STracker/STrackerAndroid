package src.stracker.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Episode implements Parcelable {

	private String Name;
	private int Number;
	private int Rating;
	private String TvShowId;
	private String Description;
	private String URL;
	private int SeasonNumber;
	
	public Episode(Parcel in){
		readFromParcel(in);
	}
	
	public Episode(String name, int number, int rating, String tvShowId,
			String description, String uRL, int seasonNumber) {
		super();
		Name = name;
		Number = number;
		Rating = rating;
		TvShowId = tvShowId;
		Description = description;
		URL = uRL;
		SeasonNumber = seasonNumber;
	}
	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getNumber() {
		return Number;
	}

	public void setNumber(int number) {
		Number = number;
	}

	public int getRating() {
		return Rating;
	}

	public void setRating(int rating) {
		Rating = rating;
	}

	public String getTvShowId() {
		return TvShowId;
	}

	public void setTvShowId(String tvShowId) {
		TvShowId = tvShowId;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public int getSeasonNumber() {
		return SeasonNumber;
	}

	public void setSeasonNumber(int seasonNumber) {
		SeasonNumber = seasonNumber;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(Name);
		dest.writeInt(Number);
		dest.writeInt(Rating);
		dest.writeString(TvShowId);
		dest.writeString(Description);
		dest.writeString(URL);
		dest.writeInt(SeasonNumber);
	}
	
	public void readFromParcel(Parcel in){
		setName(in.readString());
		setNumber(in.readInt());
		setRating(in.readInt());
		setTvShowId(in.readString());
		setDescription(in.readString());
		setURL(in.readString());
		setSeasonNumber(in.readInt());
	}
	
	public static final Parcelable.Creator<Episode> CREATOR =
		    new Parcelable.Creator<Episode>() {
	            public Episode createFromParcel(Parcel in) {
	                return new Episode(in);
	            }
	 
	            public Episode[] newArray(int size) {
	                return new Episode[size];
	            }
	        };
}
