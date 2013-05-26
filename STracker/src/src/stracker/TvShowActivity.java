package src.stracker;

import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
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
	
		new ImageRequest().execute(tvshow.getUrl());	
	}
	
	private class ImageRequest extends AsyncTask<String, Integer, Bitmap> {
		
		@Override
		protected Bitmap doInBackground(String... params) {
			URL newurl;
	    	try{
	        	newurl = new URL(params[0]);
				return BitmapFactory.decodeStream(newurl.openConnection() .getInputStream()); 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }	
	    	return null;
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
			_poster.setImageBitmap(Bitmap.createScaledBitmap(result, 168, 251, false));
		}
	}
}
