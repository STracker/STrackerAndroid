package src.stracker;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import src.stracker.asynchttp.EpisodesRequest;
import src.stracker.asynchttp.MyRunnable;
import src.stracker.model.EpisodeSynopse;

public class EpisodeSynopsisActivity extends SynopsisActivity<EpisodeSynopse> {

	@Override
	protected void fetchRequest(String uri) {
		new EpisodesRequest(this, new MyRunnable() {
			
			@Override
			public void run() {
				Toast.makeText(EpisodeSynopsisActivity.this, R.string.error_epi_synopses, Toast.LENGTH_SHORT).show();
			}
			@Override
			public <T> void runWithArgument(T response) {
				_synopses = (ArrayList<EpisodeSynopse>) response;
				List<String> elems = new ArrayList<String>();
				for(EpisodeSynopse synopse : _synopses)
	        		elems.add(synopse.getNumber() + getString(R.string.separator) + synopse.getName());
				setAdapter(elems);
			}
		}).get(uri);
	}
	
	/**
	 * When a list result is pressed make the specific request according the type of the results
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent(this, EpisodeActivity.class);
		intent.putExtra("uri", _synopses.get(position).getUri());
		startActivity(intent);
	}
}
