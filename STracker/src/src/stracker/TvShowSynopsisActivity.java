package src.stracker;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import src.stracker.model.TvShowSynopse;
/**
 * @author diogomatos 
 * This class represents a list of television show synopsis.
 */
public abstract class TvShowSynopsisActivity extends SynopsisActivity<TvShowSynopse> {
	
	/**
	 * When a list result is pressed make the specific request according the type of the results
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> adapt, View view, int position, long id) {
		Intent intent = new Intent(this, TvShowActivity.class);
		intent.putExtra("tvShowUri", _synopses.get(position).getUri());
		startActivity(intent);
	}
}
