package src.stracker.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Comment implements Parcelable {

	private String Id;
	private String Body;
	private String Uri;
	private String UserName;
	private String UserUri;
	private String UserId;
	
	public Comment(String id, String body, String uri, String userName, String userUri, String userId) {
		super();
		Id = id;
		Body = body;
		Uri = uri;
		UserName = userName;
		UserUri = userUri;
		UserId = userId;
	}
	
	public String getUri() {
		return Uri;
	}

	public void setUri(String uri) {
		Uri = uri;
	}

	public Comment(Parcel in){
		readFromParcel(in);
	}
	
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getBody() {
		return Body;
	}

	public void setBody(String body) {
		Body = body;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getUserUri() {
		return UserUri;
	}

	public void setUserUri(String userUri) {
		UserUri = userUri;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel in, int flag) {
		in.writeString(Id);
		in.writeString(Body);
		in.writeString(Uri);
		in.writeString(UserName);
		in.writeString(UserUri);
		in.writeString(UserId);
	}
	
	public void readFromParcel(Parcel in){
		setId(in.readString());
		setBody(in.readString());
		setUri(in.readString());
		setUserName(in.readString());
		setUserUri(in.readString());
		setUserId(in.readString());
	}
	
	public static final Parcelable.Creator<Comment> CREATOR =
		    new Parcelable.Creator<Comment>() {
	            public Comment createFromParcel(Parcel in) {
	                return new Comment(in);
	            }
	 
	            public Comment[] newArray(int size) {
	                return new Comment[size];
	            }
	        };
}
