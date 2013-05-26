package src.stracker.tasks;

import java.util.List;

import roboguice.inject.InjectView;
import src.stracker.R;
import src.stracker.json.JSONLocator;
import src.stracker.json.TvShowSerializer;
import src.stracker.model.Genre;
import src.stracker.model.TvShow;
import android.content.Context;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
/*
public class TvShowRequestTask extends AbstractBaseTask<String> {
	
	@InjectView(R.id.btn_start)   Button btnGetInfo; 
	@InjectView(R.id.txt_name)    TextView serieName;
	@InjectView(R.id.txt_desc)    TextView serieDesc;
	@InjectView(R.id.txt_genres)  TextView serieGenre;
	@InjectView(R.id.txt_airday)  TextView serieAirday;
	@InjectView(R.id.txt_runtime) TextView serieRuntime;
	@InjectView(R.id.rating_bar)  RatingBar ratingBar;  
	
	private TvShowSerializer _tvShowSerializer;
	
	public TvShowRequestTask(Context context) {
		super(context);
		_tvShowSerializer = (TvShowSerializer) JSONLocator.getInstance().getSerializer(TvShow.class);
	}
	
	@Override
	public void onResult(String result) {
		try {
			TvShow tvShow = _tvShowSerializer.deserialize(result); 
			serieName.setText("Name: " + tvShow.getName());
			serieDesc.setText("Description: " + tvShow.getDescription());
			ratingBar.setRating(tvShow.getRating());
			serieRuntime.setText("Runtime: " + tvShow.getRuntime() + "  min");
			serieAirday.setText("Airday: " + tvShow.getAirday());
			serieGenre.setText("Genres: " + listToString( tvShow.getGenres()));
		} catch (Exception e) {
			onError(e.getClass().getSimpleName());
		} 
		btnGetInfo.setEnabled(true);
	}

	@Override
	public void onError(String error) {
		Toast.makeText(context, error, Toast.LENGTH_SHORT).show();  
	}
	
	private String listToString(List<Genre> genres){
    	StringBuilder ret = new StringBuilder();
    	for(int i = 0; i < genres.size(); i++){
    		if (i != genres.size() - 1)
    			ret.append(genres.get(i)+",");
    		else ret.append(genres.get(i)+"");
    	}
    	return ret.toString();
    }
    
}
*/