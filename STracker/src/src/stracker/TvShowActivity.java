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
import src.stracker.model.TvShow;

@ContentView(R.layout.activity_tvshow)
public class TvShowActivity extends RoboActivity {

	@InjectView(R.id.title_name) TextView _name;
	@InjectView(R.id.title_description) TextView _description;
	@InjectView(R.id.poster_id) ImageView _poster;
	
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		TvShow tvshow = getIntent().getParcelableExtra("tvshow");
		_name.setText(tvshow.getName());
		_description.setText(tvshow.getDescription());
		showPoster(tvshow);
	}
	
	private void showPoster(TvShow tvshow){
		AsyncHttpClient client = new AsyncHttpClient();
		String[] allowedContentTypes = new String[] { "image/png", "image/jpeg" };
		client.get(tvshow.getUrl(), new BinaryHttpResponseHandler(allowedContentTypes) {
		    @Override
		    public void onSuccess(byte[] fileData) {
		        Bitmap result = BitmapFactory.decodeByteArray(fileData,0,fileData.length);
		    	_poster.setImageBitmap(Bitmap.createScaledBitmap(result, 168, 251, false));
		    }
		});
	}
}
