package src.stracker;

import java.util.ArrayList;
import com.loopj.android.image.SmartImageView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import roboguice.event.Observes;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import src.stracker.actions.SharedActions;
import src.stracker.actions.TvShowActions;
import src.stracker.actions.UserActions;
import src.stracker.asynchttp.MyRunnable;
import src.stracker.asynchttp.RatingRequests;
import src.stracker.asynchttp.TvShowRequests;
import src.stracker.asynchttp.UserRequests;
import src.stracker.model.GenreSynopse;
import src.stracker.model.Ratings;
import src.stracker.model.Subscription;
import src.stracker.model.TvShow;
import src.stracker.utils.ShakeDetector;

/**
 * @author diogomatos
 * This Activity is used to show all information about a television show.
 */
@ContentView(R.layout.activity_tvshow)
public class TvShowActivity extends BaseActivity {

	@InjectView(R.id.title_description)   TextView _description;
	@InjectView(R.id.poster_id) 		  SmartImageView _poster;
	@InjectView(R.id.serie_airday) 		  TextView _airday;
	@InjectView(R.id.serie_runtime) 	  TextView _runtime;	
	@InjectView(R.id.serie_genre) 		  TextView _genres;
	@InjectView(R.id.serie_date) 		  TextView _date;
	@InjectView(R.id.ratingBarTvShow) 	  RatingBar _rating;
	@InjectView(R.id.rating_tvshow_avg)   TextView _ratingAvg;
	@InjectView(R.id.rating_tvshow_total) TextView _ratingTotal;
	private TvShow _tvshow;
	
	/**
	 * @see src.stracker.BaseActivity#onCreate(android.os.Bundle)
	 */
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		String tvShowUri = getIntent().getStringExtra(TVSHOW_URI_PARAM);
		
		TvShowRequests.getTvShow(this, new MyRunnable() {
			@Override
			public void run() {
				Toast.makeText(TvShowActivity.this, R.string.unexpected, Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public <T> void runWithArgument(T response) {
				_tvshow = (TvShow) response;
				setTitle(_tvshow.getName());		
				_description.setText(_tvshow.getDescription());
				_airday.setText(getString(R.string.serie_airday) + _tvshow.getAirday());
				_runtime.setText(getString(R.string.serie_runtime) + _tvshow.getRuntime() + getString(R.string.min));
				_genres.setText(genreToString(_tvshow.getGenres()));
				_poster.setImageUrl(_tvshow.getPosterUrl());
				_poster.setLayoutParams(new LayoutParams(168,251));
				_date.setText(getString(R.string.serie_date)+ _tvshow.getFirstAired());
				_rating.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {	
					@Override
					public void onRatingChanged(RatingBar ratingBar, float rating,
							boolean fromUser) {
						if(_application.getUserManager().get(TvShowActivity.this) == null) return;
						SharedActions.initRatingSubmission(getString(R.string.uri_tvshow_rating)
														    .replace(TVSHOW_ID_PARAM, _tvshow.getId()), 
													        TvShowActivity.this, 
													        (int) rating
													      );
					}
				});
				//Rating request
				performRatingRequest();
			}
		}, tvShowUri);
	}
	
	/**
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tvshow, menu);   
        return true;
    }
	
	/**
	 * This method defines the callback's when a button of the menu is pressed
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	switch(item.getItemId()){
    	case R.id.form_seasons: 
    		Intent intent_seasons = new Intent(this,SeasonSynopsisActivity.class);
    		intent_seasons.putExtra(LIST_PARAM, _tvshow.getSeasons());
			startActivity(intent_seasons);
    		break;
    	case R.id.form_cast:
    		Intent intent_cast = new Intent(this,ActorsActivity.class);
    		intent_cast.putExtra(LIST_PARAM, _tvshow.getActors());
    		startActivity(intent_cast);
    		break;
    	case R.id.form_comments:
    		Intent intent_comments = new Intent(this, CommentsActivity.class);
    		intent_comments.putExtra(URI_PARAM, getString(R.string.uri_tvshow_comments).replace(TVSHOW_ID_PARAM, _tvshow.getId()));
    		startActivity(intent_comments);
    		break; 
    	case R.id.form_subscribe_tvshow:
    		if(_application.getUserManager().get(this) == null) break;
    		handleSubscription();
    		break; 
    	case R.id.form_suggest_tvshow:
    		if(_application.getUserManager().get(this) == null) break;
    		Intent intent_suggest = new Intent(this, MyFriendSuggestActivity.class);
    		intent_suggest.putExtra(TVSHOW_ID_PARAM, _tvshow.getId());
    		startActivity(intent_suggest);
    		break;
    	}
    	return true;
    }
	
	/**
	 * Auxiliary method to handle user subscriptions to the television show.
	 */
	private void handleSubscription(){
		//Search in subscriptions if this television show is already subscribed
		for(Subscription subscription : _application.getUserManager().get(this).getSubscriptions()){
			if(subscription.getTvShowSynope().getId().equals(_tvshow.getId())){
				TvShowActions.unsubscribeTvShow(this, _tvshow);
				return;
			}
		}
		//If isn't subscribed 
		UserRequests.postSubscription(this, new MyRunnable() {
			@Override
			public void run() {
				Toast.makeText(TvShowActivity.this, R.string.error_subscribe, Toast.LENGTH_SHORT).show();
			}
			@Override
			public <T> void runWithArgument(T response) {
				Toast.makeText(TvShowActivity.this, R.string.success_subscribe, Toast.LENGTH_SHORT).show();
				UserActions.addSubscriptionToUser(TvShowActivity.this, _tvshow);
			}
		}, _tvshow.getId());
	}
	
	/**
	 * This method creates a string with all the Genres of a television show
	 * @param genres - Receive a list of genre synopses
	 */
	private String genreToString(ArrayList<GenreSynopse> genres){
		StringBuilder ret = new StringBuilder(getString(R.string.serie_genres));
		for(int i = 0; i < genres.size(); i++){
			ret.append(genres.get(i).getId());
			if(i != (genres.size() - 1))
				ret.append(", ");
		}
		return ret.toString();
	}
	
	/**
	 * Event associated to shake motion
	 * @param event - shake event
	 */
	public void handleShake(@Observes ShakeDetector.OnShakeEvent event) {
		performRatingRequest();
	}
	 
	/**
	 * This method is used to perform the HTTP request command
	 */
	private void performRatingRequest(){
		RatingRequests.getTvShowRating(this, new MyRunnable() {
			@Override
			public void run() {
				Toast.makeText(TvShowActivity.this, R.string.error_rating, Toast.LENGTH_SHORT).show();
			}
			@Override
			public <T> void runWithArgument(T response) {
				Ratings rating = (Ratings) response;
				_ratingAvg.setText(getString(R.string.rating_tvshow_avg) + rating.getRating());
				_ratingTotal.setText(getString(R.string.from) + rating.getTotal() + getString(R.string.users));
			}
		}, _tvshow.getId());
	}
}
