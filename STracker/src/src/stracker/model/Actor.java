package src.stracker.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Actor implements Parcelable{
	
	private String Name;
	private String CharacterName;
	private String PhotoUrl;

	public Actor(String name, String characterName, String photoUrl) {
		super();
		Name = name;
		CharacterName = characterName;
		PhotoUrl = photoUrl;
	}
	
	public Actor(Parcel in){
		readFromParcel(in);
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPhotoUrl() {
		return PhotoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		PhotoUrl = photoUrl;
	}

	public String getCharacterName() {
		return CharacterName;
	}

	public void setCharacterName(String characterName) {
		CharacterName = characterName;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(Name);
		dest.writeString(CharacterName);
		dest.writeString(PhotoUrl);
	}
	
	public void readFromParcel(Parcel in){
		setName(in.readString());
		setCharacterName(in.readString());
		setPhotoUrl(in.readString());
	}
	
	public static final Parcelable.Creator<Actor> CREATOR =
		    new Parcelable.Creator<Actor>() {
	            public Actor createFromParcel(Parcel in) {
	                return new Actor(in);
	            }
	 
	            public Actor[] newArray(int size) {
	                return new Actor[size];
	            }
	        };
}
