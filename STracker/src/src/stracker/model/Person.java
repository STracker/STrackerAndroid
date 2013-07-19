package src.stracker.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable{
	protected String Name;

	public Person(String name) {
		Name = name;
	}
	
	public Person(Parcel in){
		readFromParcel(in);
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
	public void writeToParcel(Parcel in, int flag) {
		in.writeString(Name);
	}
	
	public void readFromParcel(Parcel in){
		setName(in.readString());
	}
	
	public static final Parcelable.Creator<Person> CREATOR =
		    new Parcelable.Creator<Person>() {
	            public Person createFromParcel(Parcel in) {
	                return new Person(in);
	            }
	 
	            public Person[] newArray(int size) {
	                return new Person[size];
	            }
	        };
}
