package src.stracker.tasks;

import java.util.List;

import src.stracker.R;
import src.stracker.json.TvShowSerializer;
import src.stracker.model.Genre;
import src.stracker.model.TvShow;
import android.app.Activity;
import android.content.Context;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class TvShowRequestTask extends AbstractBaseTask<String> {
	
	private TextView serieName, serieDesc, serieGenre, serieAirday, serieRuntime;
	private Button btnGetInfo;
	private RatingBar ratingBar;
	private TvShowSerializer _tvShowSerializer;
	
	public TvShowRequestTask(Context context) {
		super(context);
		Activity activity = (context instanceof Activity) ? (Activity) context : null;
		serieName    = (TextView) activity.findViewById(R.id.txt_name);
		serieDesc    = (TextView) activity.findViewById(R.id.txt_desc);
		serieRuntime = (TextView) activity.findViewById(R.id.txt_runtime);
		serieAirday  = (TextView) activity.findViewById(R.id.txt_airday);
		serieGenre   = (TextView) activity.findViewById(R.id.txt_genres);
		
		btnGetInfo = (Button) activity.findViewById(R.id.btn_start);
		ratingBar = (RatingBar) activity.findViewById(R.id.rating_bar);
		_tvShowSerializer = new TvShowSerializer();
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
