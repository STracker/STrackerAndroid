package src.stracker;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
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
        _adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, elems){
        	@Override
        	public View getView(int position, View convertView, ViewGroup parent){
        		View view =super.getView(position, convertView, parent);
                TextView textView=(TextView) view.findViewById(android.R.id.text1);
                textView.setTextColor(Color.WHITE);
                return view;
        	}
        };
        setListAdapter(_adapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		TvShowSynopse tvshow = _arrayList.get(position);
		new TvShowRequest(this).execute(_app.getURL()+"tvshows/"+tvshow.getId());
	}
}
