package src.stracker.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TvShowSynopse implements Parcelable{
	private String Id;
	private String Name;
	private String Uri;
	private String PosterUrl;
	
	public TvShowSynopse(String id, String name, String uri, String posterUrl) {
		super();
		Id = id;
		Name = name;
		Uri = uri;
		PosterUrl = posterUrl;
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

	public String getUri() {
		return Uri;
	}

	public void setUri(String uri) {
		Uri = uri;
	}

	public String getPosterUrl() {
		return PosterUrl;
	}

	public void setPosterUrl(String posterUrl) {
		PosterUrl = posterUrl;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(Id);
		dest.writeString(Name);
		dest.writeString(Uri);
		dest.writeString(PosterUrl);
	}
	
	public void readFromParcel(Parcel in){
		setId(in.readString());
		setName(in.readString());
		setUri(in.readString());
		setPosterUrl(in.readString());
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
