package src.stracker;

import com.loopj.android.image.SmartImageView;
import android.os.Bundle;
import android.widget.TextView;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import src.stracker.model.Episode;

/**
 * This Activity represents the information about an episode of a tv show
 */
@ContentView(R.layout.activity_episode)
public class EpisodeActivity extends RoboActivity {

	@InjectView(R.id.episode_info) TextView _episodeInfo;
	@InjectView(R.id.episode_date) TextView _date;
	@InjectView(R.id.episode_desc) TextView _description;
	@InjectView(R.id.banner_id) SmartImageView _banner;
	private Episode _episode;
	
	/**
	 * (non-Javadoc)
	 * @see roboguice.activity.RoboActivity#onCreate(android.os.Bundle)
	 */
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		_episode = getIntent().getParcelableExtra("episode");
		setTitle(_episode.getName());
		_episodeInfo.setText("Season: " + _episode.getSeasonNumber() + " Episode: " + _episode.getNumber());
		_description.setText(_episode.getDescription());
		_banner.setImageUrl(_episode.getPosterUrl());
		_date.setText("Release Date: " + _episode.getDate());
	}
}
