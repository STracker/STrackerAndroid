package src.stracker;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.loopj.android.image.SmartImageView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.Toast;
import roboguice.event.Observes;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import src.stracker.asynchttp.EpisodeRequests;
import src.stracker.asynchttp.MyRunnable;
import src.stracker.asynchttp.RatingRequests;
import src.stracker.model.Episode;
import src.stracker.model.EpisodeSynopse;
import src.stracker.model.Ratings;
import src.stracker.model.Subscription;
import src.stracker.utils.ShakeDetector;
import src.stracker.utils.Utils;

/**
 * @author diogomatos
 * This Activity represents the information about an episode of a tv show
 */
@ContentView(R.layout.activity_episode)
public class EpisodeActivity extends BaseActivity {

	@InjectView(R.id.episode_info) 		   TextView _episodeInfo;
	@InjectView(R.id.episode_date) 		   TextView _date;
	@InjectView(R.id.episode_desc) 		   TextView _description;
	@InjectView(R.id.banner_id)            SmartImageView _banner;
	@InjectView(R.id.ratingBarEpisode)     RatingBar _rating;
	@InjectView(R.id.rating_episode_avg)   TextView _ratingAvg;
	@InjectView(R.id.rating_episode_total) TextView _ratingTotal;
	private Episode _episode;
	
	/**
	 * @see src.stracker.BaseActivity#onCreate(android.os.Bundle)
	 */
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		String episodeUri = getIntent().getStringExtra("uri");
		EpisodeRequests.getEpisode(this, new MyRunnable() {	
			@Override
			public void run() {
				Toast.makeText(EpisodeActivity.this, R.string.error_episode, Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public <T> void runWithArgument(T response) {
				_episode = (Episode) response;
				setTitle(_episode.getName());
				_episodeInfo.setText(getString(R.string.episode_season) + _episode.getSeasonNumber() + getString(R.string.episode) + _episode.getNumber());
				_description.setText(_episode.getDescription());
				_banner.setImageUrl(_episode.getPosterUrl());
				_date.setText(getString(R.string.episode_date) + _episode.getDate());
				_rating.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {	
					@Override
					public void onRatingChanged(RatingBar ratingBar, float rating,
							boolean fromUser) {
						String uri = getString(R.string.uri_episode_rating)
											.replace("tvShowId", _episode.getTvShowId())
											.replace("seasonNumber", _episode.getSeasonNumber()+"")
											.replace("episodeNumber", _episode.getNumber()+"");
						Utils.initRatingSubmission(uri , EpisodeActivity.this, (int) rating);
					}
				});
				//Get rating information
				performRatingRequest();
			}
		}, episodeUri);
	}
	
	/**
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.episode, menu);   
        return true;
    }
	
	/**
	 * This method defines the callback's when a button of the menu is pressed
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	switch(item.getItemId()){
	    	case R.id.form_directors:
	    		Intent intent_directors = new Intent(this,PersonsActivity.class);
	    		intent_directors.putExtra("list", _episode.getDirectors());
				startActivity(intent_directors);
	    		break;
	    	case R.id.form_guest_actors:
	    		Intent intent_guest_actors = new Intent(this,PersonsActivity.class);
	    		intent_guest_actors.putExtra("list", _episode.getGuestActors());
	    		startActivity(intent_guest_actors);
	    		break;
	    	case R.id.form_episode_comments:
	    		String uri = getString(R.string.uri_episode_comments)
									.replace("tvShowId", _episode.getTvShowId())
									.replace("seasonNumber", _episode.getSeasonNumber()+"")
									.replace("episodeNumber", _episode.getNumber()+"");
	    		Intent intent_comments = new Intent(this, CommentsActivity.class);
	    		intent_comments.putExtra("uri", uri);
	    		startActivity(intent_comments);
	    		break; 
	    	case R.id.action_ep_watched:
	    		if(!Utils.checkLogin(this)) break;
	    		performWatchedEpisode();
	    		break;
	    } 
    	return true;
    }
	
	/**
	 * This method has the logic of the watched episode
	 */
	private void performWatchedEpisode() {
		SimpleDateFormat sdm = new SimpleDateFormat("yyyy-MM-dd");
		Date current_date = new Date();
		try {
			if(current_date.compareTo(sdm.parse(_episode.getDate())) <= 0){
				Toast.makeText(this, R.string.error_ew_date, Toast.LENGTH_SHORT).show();
				return;
			}
			//Verify if the user is subscribed
			Subscription subscription = null;
			for(Subscription sub : _application.getFbUser().getSubscriptions()){
				if(sub.getTvShowSynope().getId().equals(_episode.getTvShowId())){
					subscription = sub;
					break;
				}
			}
			if(subscription == null){ 
				Toast.makeText(this, R.string.error_ew_subscribed, Toast.LENGTH_SHORT).show();
				return;
			}
			//Verify if the user already watched this episode
			for(EpisodeSynopse epi : subscription.getWatchedEpisodes()){
				if(epi.getSeasonNumber() == _episode.getSeasonNumber() && epi.getNumber() == _episode.getNumber()){
					Utils.unwatchEpisode(this, _episode);
					return;
				}
			}
			//Ask to watch the episode
			Utils.watchEpisode(this, _episode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Event associated to shake motion
	 * @param event - shake event
	 */
	public void handleShake(@Observes ShakeDetector.OnShakeEvent event) {
		performRatingRequest();
	}
	
	/**
	 * This method is used to perform the HTTP request command
	 */
	private void performRatingRequest(){
		RatingRequests.getEpisodeRating(this, new MyRunnable() {
			@Override
			public void run() {
				Toast.makeText(EpisodeActivity.this, R.string.error_rating, Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public <T> void runWithArgument(T response) {
				Ratings rating = (Ratings) response;
				_ratingAvg.setText(getString(R.string.rating_episode_avg) + rating.getRating());
				_ratingTotal.setText(getString(R.string.rating_episode_total) + rating.getTotal());
			}
		}, _episode);
	}
}
