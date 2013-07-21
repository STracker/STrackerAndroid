package src.stracker.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable{
	
	private String Name;
	private String Id;
	private String PhotoUrl;
	private String Email;
	
	public User(String name, String id, String email){
		Name = name;
		Id = id;
		Email = email;
		PhotoUrl = "http://graph.facebook.com/"+id+"/picture?type=large";
	}

	public User(Parcel in){
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

	public String getPhotoUrl() {
		return PhotoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		PhotoUrl = photoUrl;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(Id);
		dest.writeString(Name);
		dest.writeString(Email);
		dest.writeString(PhotoUrl);
	}
	
	public void readFromParcel(Parcel in){
		setId(in.readString());
		setName(in.readString());
		setEmail(in.readString());
		setPhotoUrl(in.readString());
	}
	
	public static final Parcelable.Creator<User> CREATOR =
		    new Parcelable.Creator<User>() {
	            public User createFromParcel(Parcel in) {
	                return new User(in);
	            }
	 
	            public User[] newArray(int size) {
	                return new User[size];
	            }
	        };
}
