package src.stracker.model;

import java.util.ArrayList;

public class User{
	
	private String Name;
	private String Id;
	private String PhotoUrl;
	private String Email;
	private int Version;
	private ArrayList<Subscription> Subscriptions;
	private ArrayList<UserSynopse>  Friends;
	private ArrayList<UserSynopse>  FriendRequests;
	private ArrayList<Suggestion>   Suggestions;
	
	public User(String name, String id, String email, int version, ArrayList<Subscription> subscriptions,
			ArrayList<UserSynopse> friends, ArrayList<UserSynopse> friendRequests, ArrayList<Suggestion> suggestions){
		Name = name;
		Id = id;
		Email = email;
		Version = version;
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

	public int getVersion(){
		return Version;
	}
	
	public void incVersion(){
		Version++;
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
	
	public void addSubscription(Subscription sub){
		Subscriptions.add(sub);
	}
	
	public void removeSubscription(Subscription sub){
		for(Subscription s : Subscriptions){
			if(s.getTvShowSynope().getId().equals(sub.getTvShowSynope().getId())){
				Subscriptions.remove(s);
				return;
			}
		}
	}
	
	public void addFriend(UserSynopse user){
		Friends.add(user);
	}
	
	public void removeFriend(UserSynopse user){
		for(UserSynopse s : Friends){
			if(s.getId().equals(user.getId())){
				Friends.remove(s);
				return;
			}
		}
	}
	
	public void addSuggestion(Suggestion sug){
		Suggestions.add(sug);
	}
	
	public void removeSuggestion(Suggestion sug){
		for(Suggestion s : Suggestions){
			if(s.getTvShow().getId().equals(sug.getTvShow().getId()) && 
				s.getUser().getId().equals(sug.getUser().getId())){
				Suggestions.remove(s);
				return;
			}
		}
	}
	
	public void addFriendRequest(UserSynopse user){
		FriendRequests.add(user);
	}
	
	public void removeFriendRequest(UserSynopse user){
		for(UserSynopse s : FriendRequests){
			if(s.getId().equals(user.getId())){
				FriendRequests.remove(s);
				return;
			}
		}
	}
	
	public UserSynopse generateSynopse(String uri){
		return new UserSynopse(Name, Id, uri.replace("userId", Id));
	}
}
