package src.stracker.model;

import android.os.Parcel;
import android.os.Parcelable;

public class EpisodeSynopse implements Parcelable {

	private int Number;
	private String Name;
	
	public EpisodeSynopse(Parcel in){
		readFromParcel(in);
	}
	
	public EpisodeSynopse(int number, String name) {
		super();
		Number = number;
		Name = name;
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

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(Number);
		dest.writeString(Name);
	}
	
	public void readFromParcel(Parcel in){
		setNumber(in.readInt());
		setName(in.readString());
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
