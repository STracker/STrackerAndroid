package src.stracker.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Episode implements Parcelable {

	private String Name;
	private int Number;
	private String Date;
	private String TvShowId;
	private String Description;
	private String PosterUrl;
	private int SeasonNumber;
	
	public Episode(Parcel in){
		readFromParcel(in);
	}
	
	public Episode(String name, int number, String date, String tvShowId,
			String description, String posterUrl, int seasonNumber) {
		super();
		Name = name;
		Number = number;
		Date = date;
		TvShowId = tvShowId;
		Description = description;
		PosterUrl = posterUrl;
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

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
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

	public String getPosterUrl() {
		return PosterUrl;
	}

	public void setPosterUrl(String uRL) {
		PosterUrl = uRL;
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
		dest.writeString(Date);
		dest.writeString(TvShowId);
		dest.writeString(Description);
		dest.writeString(PosterUrl);
		dest.writeInt(SeasonNumber);
	}
	
	public void readFromParcel(Parcel in){
		setName(in.readString());
		setNumber(in.readInt());
		setDate(in.readString());
		setTvShowId(in.readString());
		setDescription(in.readString());
		setPosterUrl(in.readString());
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