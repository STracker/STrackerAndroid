package src.stracker.model;

public class Suggestion {

	private UserSynopse User;
	private TvShowSynopse TvShow;
	
	public Suggestion(UserSynopse user, TvShowSynopse tvshow){
		User = user;
		TvShow = tvshow;
	}

	public UserSynopse getUser() {
		return User;
	}

	public TvShowSynopse getTvShow() {
		return TvShow;
	}
}
