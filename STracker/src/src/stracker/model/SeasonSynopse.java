package src.stracker.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SeasonSynopse implements Parcelable{

	private int Number;
	private String Uri;
	private String Name;
	
    public SeasonSynopse(Parcel in){
    	readFromParcel(in);
    }
	
	public SeasonSynopse(int number, String uri, String name) {
		super();
		Number = number;
		Uri = uri;
		Name = name;
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

	public int getNumber() {
		return Number;
	}

	public void setNumber(int number) {
		Number = number;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int arg1) {
		dest.writeInt(Number);
		dest.writeString(Uri);
		dest.writeString(Name);
	}
	
	public void readFromParcel(Parcel in){
		setNumber(in.readInt());
		setUri(in.readString());
		setName(in.readString());
	}

	public static final Parcelable.Creator<SeasonSynopse> CREATOR =
		    new Parcelable.Creator<SeasonSynopse>() {
	            public SeasonSynopse createFromParcel(Parcel in) {
	                return new SeasonSynopse(in);
	            }
	 
	            public SeasonSynopse[] newArray(int size) {
	                return new SeasonSynopse[size];
	            }
	        };
}
