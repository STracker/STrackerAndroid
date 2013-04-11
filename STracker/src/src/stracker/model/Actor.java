package src.stracker.model;

public class Actor extends Person{
	private String CharacterName;

	public Actor(int id, String name, Artwork photo, String characterName) {
		super(id, name, photo);
		CharacterName = characterName;
	}

	public String getCharacterName() {
		return CharacterName;
	}

	public void setCharacterName(String characterName) {
		CharacterName = characterName;
	}
}
