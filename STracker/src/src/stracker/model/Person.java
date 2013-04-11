package src.stracker.model;

public class Person {
	protected int Id;
	protected String Name;
	protected Artwork Photo;

	public Person(int id, String name, Artwork photo) {
		super();
		Id = id;
		Name = name;
		Photo = photo;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Artwork getPhoto() {
		return Photo;
	}

	public void setPhoto(Artwork photo) {
		Photo = photo;
	}
}
