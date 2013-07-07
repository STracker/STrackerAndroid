package src.stracker.model;

public class FBUser {
	
	private String Name;
	private String Id;
	private String PhotoUrl;
	private String Email;
	
	public FBUser(String name, String id, String email){
		Name = name;
		Id = id;
		Email = email;
		PhotoUrl = "http://graph.facebook.com/"+id+"/picture?type=large";
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
}
