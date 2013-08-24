package src.stracker.model;

import java.util.ArrayList;

public class User{
	
	private String Name;
	private String Id;
	private String PhotoUrl;
	private String Email;
	private ArrayList<Subscription> Subscriptions;
	private ArrayList<UserSynopse>  Friends;
	private ArrayList<UserSynopse>  FriendRequests;
	private ArrayList<Suggestion>   Suggestions;
	
	public User(String name, String id, String email, ArrayList<Subscription> subscriptions,
			ArrayList<UserSynopse> friends, ArrayList<UserSynopse> friendRequests, ArrayList<Suggestion> suggestions){
		Name = name;
		Id = id;
		Email = email;
		PhotoUrl = "http://graph.facebook.com/"+id+"/picture?type=large";
		Subscriptions = subscriptions;
		Friends = friends;
		FriendRequests = friendRequests;
		Suggestions = suggestions;
	}
	
	public String getName() {
		return Name;
	}

	public String getId() {
		return Id;
	}

	public String getPhotoUrl() {
		return PhotoUrl;
	}

	public String getEmail() {
		return Email;
	}

	public ArrayList<Subscription> getSubscriptions() {
		return Subscriptions;
	}

	public ArrayList<UserSynopse> getFriends() {
		return Friends;
	}

	public ArrayList<UserSynopse> getFriendRequests() {
		return FriendRequests;
	}

	public ArrayList<Suggestion> getSuggestions() {
		return Suggestions;
	}
}
