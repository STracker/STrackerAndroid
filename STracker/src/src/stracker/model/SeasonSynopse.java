package src.stracker.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SeasonSynopse implements Parcelable{

	private int Number;
	
    public SeasonSynopse(Parcel in){
    	readFromParcel(in);
    }
	
	public SeasonSynopse(int number) {
		super();
		Number = number;
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
	}
	
	public void readFromParcel(Parcel in){
		setNumber(in.readInt());
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
