package src.stracker.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TvShowSynopse implements Parcelable{
	private String Id;
	private String Name;
	
	public TvShowSynopse(String id, String name) {
		super();
		Id = id;
		Name = name;
	}

	public TvShowSynopse(Parcel in){
		readFromParcel(in);
	}
	
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(Id);
		dest.writeString(Name);
		
	}
	
	public void readFromParcel(Parcel in){
		setId(in.readString());
		setName(in.readString());
	}
	
	public static final Parcelable.Creator<TvShowSynopse> CREATOR =
	    new Parcelable.Creator<TvShowSynopse>() {
            public TvShowSynopse createFromParcel(Parcel in) {
                return new TvShowSynopse(in);
            }
 
            public TvShowSynopse[] newArray(int size) {
                return new TvShowSynopse[size];
            }
        };
}