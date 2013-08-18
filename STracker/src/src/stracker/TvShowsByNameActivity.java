package src.stracker;

import java.util.ArrayList;
import java.util.List;

import android.widget.Toast;
import src.stracker.asynchttp.MyRunnable;
import src.stracker.asynchttp.SearchByNameRequest;
import src.stracker.model.TvShowSynopse;

public class TvShowsByNameActivity extends TvShowSynopsisActivity {

	@Override
	protected void fetchRequest(String uri) {
		new SearchByNameRequest(this, new MyRunnable() {
			@Override
			public void run() {
				Toast.makeText(TvShowsByNameActivity.this, R.string.error_search_by_name, Toast.LENGTH_SHORT).show();
			}
			@Override
			public <T> void runWithArgument(T response) {
				_synopses = (ArrayList<TvShowSynopse>) response;
				List<String> elems = new ArrayList<String>();
	        	for(TvShowSynopse synopse : _synopses)
	            	elems.add(synopse.getName());
	        	setAdapter(elems);
			}
		}).get(uri);
	}
}
