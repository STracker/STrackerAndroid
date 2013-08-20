package src.stracker;

import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import src.stracker.asynchttp.GenresRequest;
import src.stracker.asynchttp.MyRunnable;
import src.stracker.model.GenreSynopse;

public class GenreSynopsisActivity extends SynopsisActivity<GenreSynopse> {

	/**
	 * @see src.stracker.SynopsisActivity#fetchRequest(java.lang.String)
	 */
	@Override
	protected void fetchRequest(String uri) {
		new GenresRequest(this, new MyRunnable() {
			@Override
			public void run() {
				Toast.makeText(GenreSynopsisActivity.this, R.string.error_genres, Toast.LENGTH_SHORT).show();
			}
			@Override
			public <T> void runWithArgument(T response) {
				_synopses = (ArrayList<GenreSynopse>) response;
				List<String> elems = new ArrayList<String>();
				for(GenreSynopse synopse : _synopses)
		    		elems.add(synopse.getId());
		    	setAdapter(elems);
			}
		});
	}

	/**
	 * When a genre of the list is pressed, create a request to get all television shows from that genre
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> adapt,  View v, int position, long id) {
		Intent intent = new Intent(this,TvShowsByGenreActivity.class);
		intent.putExtra("uri", _synopses.get(position).getUri());
		startActivity(intent);
	}
}
