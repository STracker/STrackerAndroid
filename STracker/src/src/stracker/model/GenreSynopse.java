package src.stracker.model;

import android.os.Parcel;
import android.os.Parcelable;

public class GenreSynopse implements Parcelable{
	
	private String Id;
	private String Uri;
	
	public GenreSynopse(String id, String uri) {
		super();
		Id = id;
		Uri = uri;
	}

	public GenreSynopse(Parcel in){
		readFromParcel(in);
	}
	
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getUri() {
		return Uri;
	}

	public void setUri(String uri) {
		Uri = uri;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(Id);
		dest.writeString(Uri);
	}
	
	public void readFromParcel(Parcel in){
		setId(in.readString());
		setUri(in.readString());
	}
	
	public static final Parcelable.Creator<GenreSynopse> CREATOR =
		    new Parcelable.Creator<GenreSynopse>() {
	            public GenreSynopse createFromParcel(Parcel in) {
	                return new GenreSynopse(in);
	            }
	 
	            public GenreSynopse[] newArray(int size) {
	                return new GenreSynopse[size];
	            }
	        };
}
