package src.stracker;

import java.util.ArrayList;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BinaryHttpResponseHandler;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import src.stracker.model.GenreSynopse;
import src.stracker.model.TvShow;

@ContentView(R.layout.activity_tvshow)
public class TvShowActivity extends RoboActivity {

	@InjectView(R.id.title_description) TextView _description;
	@InjectView(R.id.poster_id) ImageView _poster;
	@InjectView(R.id.serie_airday) TextView _airday;
	@InjectView(R.id.serie_runtime) TextView _runtime;	
	@InjectView(R.id.serie_genre) TextView _genres;
	//private STrackerApp _app;
	private TvShow _tvshow;
	
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		//_app = (STrackerApp) getApplication();
		
		_tvshow = getIntent().getParcelableExtra("tvshow");
		setTitle(_tvshow.getName());
		_description.setText(_tvshow.getDescription());
		_airday.setText("Airday: " + _tvshow.getAirday());
		_runtime.setText("Runtime: " + _tvshow.getRuntime() + " min");
		_genres.setText(genreToString(_tvshow.getGenres()));
		showPoster(_tvshow);
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tvshow, menu);   
        return true;
    }
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	switch(item.getItemId()){
    	case R.id.form_seasons:
    		Intent intent_seasons = new Intent(this,ResultActivity.class);
    		intent_seasons.putExtra("type", "SEASONSYNOPSE");
    		intent_seasons.putExtra("list", _tvshow.getSeasons());
			startActivity(intent_seasons);
    		break;
    	case R.id.form_cast:
    		Intent intent_cast = new Intent(this,ActorsActivity.class);
    		intent_cast.putExtra("list", _tvshow.getActors());
    		startActivity(intent_cast);
    		break;
    	case R.id.form_comments:
    		break;
    	}
    	return true;
    }
	
	private void showPoster(TvShow tvshow){
		AsyncHttpClient client = new AsyncHttpClient();
		String[] allowedContentTypes = new String[] { "image/png", "image/jpeg" };
		client.get(tvshow.getPosterUrl(), new BinaryHttpResponseHandler(allowedContentTypes) {
		    @Override
		    public void onSuccess(byte[] fileData) {
		        Bitmap result = BitmapFactory.decodeByteArray(fileData,0,fileData.length);
		    	_poster.setImageBitmap(Bitmap.createScaledBitmap(result, 168, 251, false));
		    }
		});
	}
	
	private String genreToString(ArrayList<GenreSynopse> genres){
		StringBuilder ret = new StringBuilder("Genres: ");
		for(int i = 0; i < genres.size(); i++){
			ret.append(genres.get(i).getId());
			if(i != (genres.size() - 1))
				ret.append(", ");
		}
		return ret.toString();
	}
}
