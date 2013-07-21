package src.stracker;

import com.loopj.android.image.SmartImageView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.RatingBar.OnRatingBarChangeListener;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import src.stracker.asynchttp.CommentsRequest;
import src.stracker.asynchttp.EpisodeRatingRequest;
import src.stracker.model.Episode;
import src.stracker.model.Ratings;
import src.stracker.utils.Utils;

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
	private Activity _context;
	
	/**
	 * (non-Javadoc)
	 * @see roboguice.activity.RoboActivity#onCreate(android.os.Bundle)
	 */
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		_app = (STrackerApp) getApplication();
		_episode = getIntent().getParcelableExtra("episode");
		_context = this;
		setTitle(_episode.getName());
		//build rating uri
		String uri = getString(R.string.uri_episode_rating);
		uri = uri.replace("tvShowId", _episode.getTvShowId());
		uri = uri.replace("seasonNumber", _episode.getSeasonNumber()+"");
		uri = uri.replace("episodeNumber", _episode.getNumber()+"");
		
		new EpisodeRatingRequest(this).get(getString(R.string.uri_host_api) + uri);
		_episodeInfo.setText("Season: " + _episode.getSeasonNumber() + " Episode: " + _episode.getNumber());
		_description.setText(_episode.getDescription());
		_banner.setImageUrl(_episode.getPosterUrl());
		_date.setText("Release Date: " + _episode.getDate());
		
		_rating.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {	
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				if(!Utils.checkLogin(_context, _app))
					return;
				String uri = getString(R.string.uri_episode_rating);
				uri = uri.replace("tvShowId", _episode.getTvShowId());
				uri = uri.replace("seasonNumber", _episode.getSeasonNumber()+"");
				uri = uri.replace("episodeNumber", _episode.getNumber()+"");
				Utils.initRatingSubmission(getString(R.string.uri_host_api) + uri , _context, _app, (int) rating);
			}
		});
	}
	
	public void onRatingSuccess(Ratings rating){
		_ratingAvg.setText("Average: " + rating.getRating());
		_ratingTotal.setText("Total: " + rating.getTotal());
	}
	
	/**
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.episode, menu);   
        return true;
    }
	/**
	 * (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 * This method defines the callback's when a button of the menu is pressed
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
    		String uri = getString(R.string.uri_episode_comments);
    		uri = uri.replace("tvShowId", _episode.getTvShowId());
    		uri = uri.replace("seasonNumber", _episode.getSeasonNumber()+"");
    		uri = uri.replace("episodeNumber", _episode.getNumber()+"");;
    		new CommentsRequest(this,uri).get(getString(R.string.uri_host_api) + uri);
    		break; 
    	}
    	return true;
    }
}
