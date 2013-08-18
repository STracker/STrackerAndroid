package src.stracker;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import src.stracker.model.TvShowSynopse;

public abstract class TvShowSynopsisActivity extends SynopsisActivity<TvShowSynopse> {
	
	/**
	 * When a list result is pressed make the specific request according the type of the results
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent(this, TvShowActivity.class);
		intent.putExtra("tvShowUri", _synopses.get(position).getUri());
		startActivity(intent);
	}
}
