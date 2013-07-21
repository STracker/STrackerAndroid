package src.stracker.model;

import android.os.Parcel;
import android.os.Parcelable;

public class UserSynopse implements Parcelable {

	private String Name;
	private String Id;
	private String Uri;
	
	public UserSynopse(String name, String id, String uri) {
		super();
		Name = name;
		Id = id;
		Uri = uri;
	}
	
	public UserSynopse(Parcel in){
		readFromParcel(in);
	}
	
	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
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
	public void writeToParcel(Parcel dest, int flag) {
		dest.writeString(Id);
		dest.writeString(Name);
		dest.writeString(Uri);
	}
	
	public void readFromParcel(Parcel in){
		setId(in.readString());
		setName(in.readString());
		setUri(in.readString());
	}
	
	public static final Parcelable.Creator<UserSynopse> CREATOR =
		    new Parcelable.Creator<UserSynopse>() {
	            public UserSynopse createFromParcel(Parcel in) {
	                return new UserSynopse(in);
	            }
	 
	            public UserSynopse[] newArray(int size) {
	                return new UserSynopse[size];
	            }
	        };
}
