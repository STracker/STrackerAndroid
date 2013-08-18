package src.stracker;

import java.util.ArrayList;
import java.util.List;
import android.widget.Toast;
import src.stracker.asynchttp.MyRunnable;
import src.stracker.asynchttp.SearchByGenreRequest;
import src.stracker.model.TvShowSynopse;

public class TvShowsByGenreActivity extends TvShowSynopsisActivity {

	@Override
	protected void fetchRequest(String uri) {
		new SearchByGenreRequest(this, new MyRunnable() {
			@Override
			public void run() {
				Toast.makeText(TvShowsByGenreActivity.this, R.string.error_search_by_genre, Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public <T> void runWithArgument(T response) {
				_synopses = (ArrayList<TvShowSynopse>) response;
				List<String> elems = new ArrayList<String>();
	        	for(TvShowSynopse synopse : _synopses)
	            	elems.add(synopse.getName());
	        	setAdapter(elems);
			}
		});
	}
}
