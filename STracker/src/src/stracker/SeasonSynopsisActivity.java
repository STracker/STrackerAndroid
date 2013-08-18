package src.stracker;

import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import src.stracker.model.SeasonSynopse;

public class SeasonSynopsisActivity extends SynopsisActivity<SeasonSynopse> {

	@Override
	protected void fetchRequest(String uri) {
		_synopses = getIntent().getParcelableArrayListExtra("list");
		List<String> elems = new ArrayList<String>();
		for(SeasonSynopse synopse : _synopses)
        	elems.add(synopse.getName());
		setAdapter(elems);
	}

	/**
	 * When a list result is pressed make the specific request according the type of the results
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent(this, EpisodeSynopsisActivity.class);
		intent.putExtra("uri", _synopses.get(position).getUri());
		startActivity(intent);
	}
}
