package src.stracker;

import java.util.ArrayList;
import java.util.List;
import android.widget.Toast;
import src.stracker.asynchttp.MyRunnable;
import src.stracker.asynchttp.TvShowRequests;
import src.stracker.model.TvShowSynopse;
/**
 * @author diogomatos
 * This class represents a list of television show synopsis obtained by it's name.
 */
public class TvShowsByNameActivity extends TvShowSynopsisActivity {

	/**
	 * In this method the URI isn't used. Instead the parameter is used to 
	 * keep the name in television show search
	 * @see src.stracker.SynopsisActivity#fetchRequest(java.lang.String)
	 */
	@Override
	protected void fetchRequest(String uri) {
		TvShowRequests.getTvShowByName(this, new MyRunnable() {
			@Override
			public void run() {
				Toast.makeText(TvShowsByNameActivity.this, R.string.error_search_by_name, Toast.LENGTH_SHORT).show();
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
