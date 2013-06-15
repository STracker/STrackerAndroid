package src.stracker;

import java.util.ArrayList;
import com.loopj.android.image.SmartImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import src.stracker.model.GenreSynopse;
import src.stracker.model.TvShow;

/**
 * This Activity is used to show all information about a tv show.
 */
@ContentView(R.layout.activity_tvshow)
public class TvShowActivity extends RoboActivity {

	@InjectView(R.id.title_description) TextView _description;
	@InjectView(R.id.poster_id) SmartImageView _poster;
	@InjectView(R.id.serie_airday) TextView _airday;
	@InjectView(R.id.serie_runtime) TextView _runtime;	
	@InjectView(R.id.serie_genre) TextView _genres;
	@InjectView(R.id.serie_date) TextView _date;
	//private STrackerApp _app;
	private TvShow _tvshow;
	
	/**
	 * (non-Javadoc)
	 * @see roboguice.activity.RoboActivity#onCreate(android.os.Bundle)
	 */
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
		_poster.setImageUrl(_tvshow.getPosterUrl());
		_poster.setLayoutParams(new LayoutParams(168,251));
		_date.setText("Date: "+ _tvshow.getFirstAired() + " " + _tvshow.getAirTime());
	}
	
	/**
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tvshow, menu);   
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
	
	/**
	 * This method creates a string with all the Genres of a tv show
	 * @param genres - Receive a list of genre synopses
	 */
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
