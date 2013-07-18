package src.stracker;

import com.loopj.android.image.SmartImageView;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import src.stracker.asynchttp.EpisodeRatingRequest;
import src.stracker.model.Episode;
import src.stracker.model.Ratings;

/**
 * This Activity represents the information about an episode of a tv show
 */
@ContentView(R.layout.activity_episode)
public class EpisodeActivity extends RoboActivity {

	@InjectView(R.id.episode_info) TextView _episodeInfo;
	@InjectView(R.id.episode_date) TextView _date;
	@InjectView(R.id.episode_desc) TextView _description;
	@InjectView(R.id.banner_id) SmartImageView _banner;
	@InjectView(R.id.ratingBarEpisode) RatingBar _rating;
	@InjectView(R.id.rating_episode_avg) TextView _ratingAvg;
	@InjectView(R.id.rating_episode_total) TextView _ratingTotal;
	private Episode _episode;
	private STrackerApp _app; 
	
	/**
	 * (non-Javadoc)
	 * @see roboguice.activity.RoboActivity#onCreate(android.os.Bundle)
	 */
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		_app = (STrackerApp) getApplication();
		_episode = getIntent().getParcelableExtra("episode");
		setTitle(_episode.getName());
		new EpisodeRatingRequest(this).get(_app.getApiURL() + "tvshows/" + _episode.getTvShowId() + 
																  "/seasons/" + _episode.getSeasonNumber() + 
																  "/episodes/" + _episode.getNumber() + 
																  "/ratings");
		_episodeInfo.setText("Season: " + _episode.getSeasonNumber() + " Episode: " + _episode.getNumber());
		_description.setText(_episode.getDescription());
		_banner.setImageUrl(_episode.getPosterUrl());
		_date.setText("Release Date: " + _episode.getDate());
	}
	
	public void onRatingSuccess(Ratings rating){
		_ratingAvg.setText("Average: " + rating.getRating());
		_ratingTotal.setText("Total: " + rating.getTotal());
	}
}
