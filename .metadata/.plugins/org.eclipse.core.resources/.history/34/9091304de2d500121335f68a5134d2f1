package src.stracker;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BinaryHttpResponseHandler;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
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
	@InjectView(R.id.episode_rating) TextView _rating;
	@InjectView(R.id.episode_desc) TextView _description;
	@InjectView(R.id.banner_id) ImageView _banner;
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
		showBanner(_episode);
	}
	
	/**
	 * This method makes an asynchronous http request to get the banner of the episode received by parameter
	 * @param episode - Represent an episode from a tv show
	 */
	private void showBanner(Episode episode){
		AsyncHttpClient client = new AsyncHttpClient();
		String[] allowedContentTypes = new String[] { "image/png", "image/jpeg" };
		client.get(episode.getPosterUrl(), new BinaryHttpResponseHandler(allowedContentTypes) {
		    @Override
		    public void onSuccess(byte[] fileData) {
		        Bitmap result = BitmapFactory.decodeByteArray(fileData,0,fileData.length);
		    	_banner.setImageBitmap(Bitmap.createScaledBitmap(result, 400, 250, false));
		    }
		});
	}
}
