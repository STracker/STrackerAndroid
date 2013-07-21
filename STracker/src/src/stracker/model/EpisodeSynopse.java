package src.stracker.model;

import android.os.Parcel;
import android.os.Parcelable;

public class EpisodeSynopse implements Parcelable {

	private int Number;
	private String Name;
	private String Uri;
	private String Date;
	private int SeasonNumber;
	
	public EpisodeSynopse(Parcel in){
		readFromParcel(in);
	}
	
	public EpisodeSynopse(int number, String name, String uri, String date, int seasonNumber) {
		super();
		Number = number;
		Name = name;
		Uri = uri;
		Date = date;
		SeasonNumber = seasonNumber;
	}

	@Override
	public int describeContents() {
		return 0;
	}
	
	public int getNumber() {
		return Number;
	}

	public void setNumber(int number) {
		Number = number;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getUri() {
		return Uri;
	}

	public void setUri(String uri) {
		Uri = uri;
	}
	
	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public int getSeasonNumber() {
		return SeasonNumber;
	}

	public void setSeasonNumber(int seasonNumber) {
		SeasonNumber = seasonNumber;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(Number);
		dest.writeString(Name);
		dest.writeString(Uri);
		dest.writeString(Date);
		dest.writeInt(SeasonNumber);
	}
	
	public void readFromParcel(Parcel in){
		setNumber(in.readInt());
		setName(in.readString());
		setUri(in.readString());
		setDate(in.readString());
		setSeasonNumber(in.readInt());
	}
	
	public static final Parcelable.Creator<EpisodeSynopse> CREATOR =
		    new Parcelable.Creator<EpisodeSynopse>() {
	            public EpisodeSynopse createFromParcel(Parcel in) {
	                return new EpisodeSynopse(in);
	            }
	 
	            public EpisodeSynopse[] newArray(int size) {
	                return new EpisodeSynopse[size];
	            }
	        };
}
