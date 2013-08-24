package src.stracker;

import java.util.ArrayList;
import java.util.List;
import android.widget.Toast;
import src.stracker.asynchttp.MyRunnable;
import src.stracker.asynchttp.TvShowRequests;
import src.stracker.model.TvShowSynopse;

public class TvShowsByGenreActivity extends TvShowSynopsisActivity {

	@Override
	protected void fetchRequest(String uri) {
		TvShowRequests.getGenre(this, new MyRunnable() {
			@Override
			public void run() {
				Toast.makeText(TvShowsByGenreActivity.this, R.string.error_search_by_genre, Toast.LENGTH_SHORT).show();
			}
			@SuppressWarnings("unchecked")
			@Override
			public <T> void runWithArgument(T response) {
				_synopses = (ArrayList<TvShowSynopse>) response;
				List<String> elems = new ArrayList<String>();
	        	for(TvShowSynopse synopse : _synopses)
	            	elems.add(synopse.getName());
	        	setAdapter(elems);
			}
		}, uri);
	}
}
