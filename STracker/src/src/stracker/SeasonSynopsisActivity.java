package src.stracker;

import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import src.stracker.model.SeasonSynopse;

/**
 * @author diogomatos
 * This class represents a list of season synopses
 */
public class SeasonSynopsisActivity extends SynopsisActivity<SeasonSynopse> {

	/** 
	 * @see src.stracker.SynopsisActivity#fetchRequest(java.lang.String)
	 */
	@Override
	protected void fetchRequest(String uri) {
		_synopses = getIntent().getParcelableArrayListExtra(LIST_PARAM);
		List<String> elems = new ArrayList<String>();
		for(SeasonSynopse synopse : _synopses)
        	elems.add(synopse.getName());
		setAdapter(elems);
	}
	
	/**
	 * When a list result is pressed make the specific request according the type of the results
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> adapt, View view, int position, long id) {
		Intent intent = new Intent(this, EpisodeSynopsisActivity.class);
		intent.putExtra(URI_PARAM, _synopses.get(position).getUri());
		startActivity(intent);
	}
}
