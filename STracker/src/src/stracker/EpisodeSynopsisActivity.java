package src.stracker;

import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import src.stracker.asynchttp.MyRunnable;
import src.stracker.asynchttp.SeasonRequests;
import src.stracker.model.EpisodeSynopse;

/**
 * @author diogomatos
 * This class represents a list of episode synopsis.
 */
public class EpisodeSynopsisActivity extends SynopsisActivity<EpisodeSynopse> {

	/**
	 * @see src.stracker.SynopsisActivity#fetchRequest(java.lang.String)
	 */
	@Override
	protected void fetchRequest(String uri) {
		SeasonRequests.getSeasonEpisodes(this, new MyRunnable() {
			@Override
			public void run() {
				Toast.makeText(EpisodeSynopsisActivity.this, R.string.error_epi_synopses, Toast.LENGTH_SHORT).show();
			}
			@SuppressWarnings("unchecked")
			@Override
			public <T> void runWithArgument(T response) {
				_synopses = (ArrayList<EpisodeSynopse>) response;
				List<String> elems = new ArrayList<String>();
				for(EpisodeSynopse synopse : _synopses)
	        		elems.add(synopse.getNumber() + getString(R.string.separator) + synopse.getName());
				setAdapter(elems);
			}
		}, uri);
	}
	
	/**
	 * When a list result is pressed make the specific request according the type of the results
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> adapt, View v, int position, long id) {
		Intent intent = new Intent(this, EpisodeActivity.class);
		intent.putExtra("uri", _synopses.get(position).getUri());
		startActivity(intent);
	}
}
