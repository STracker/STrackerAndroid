package src.stracker;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import roboguice.activity.RoboListActivity;
import roboguice.inject.ContentView;
import src.stracker.model.TvShowSynopse;
import src.stracker.asynchttp.TvShowRequest;

@ContentView(R.layout.activity_result)
public class ResultActivity extends RoboListActivity {

	private ArrayAdapter<String> _adapter;
	private STrackerApp _app;
	private ArrayList<TvShowSynopse> _arrayList;
	
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		_app = (STrackerApp) getApplication();
        _arrayList = getIntent().getParcelableArrayListExtra("list");
        List<String> elems = new ArrayList<String>();
        for(TvShowSynopse synopse : _arrayList)
        	elems.add(synopse.getName());
        _adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, elems);
        setListAdapter(_adapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		TvShowSynopse tvshow = _arrayList.get(position);
		new TvShowRequest(this).execute(_app.getURL()+"tvshows/"+tvshow.getId());
	}
}
