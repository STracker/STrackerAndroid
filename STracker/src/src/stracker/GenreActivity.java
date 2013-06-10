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
import src.stracker.asynchttp.SearchByGenreRequest;
import src.stracker.model.GenreSynopse;
 
@ContentView(R.layout.activity_genre)
public class GenreActivity extends RoboListActivity {
	
	private STrackerApp _app;
	private ArrayAdapter<String> _adapter;
	private ArrayList<GenreSynopse> _arrayList;
	private List<String> _elems;
	
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setTitle("List of Genres");
        _app = (STrackerApp) getApplication();
    	_arrayList = getIntent().getParcelableArrayListExtra("list");
    	_elems = new ArrayList<String>();
    	
    	//Populate list
    	for(GenreSynopse synopse : _arrayList){
    		_elems.add(synopse.getId());
    	}
    	
        _adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, _elems){
        	@Override
        	public View getView(int position, View convertView, ViewGroup parent){
        		View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                textView.setTextColor(Color.WHITE);
                return view;
        	}
        };
        setListAdapter(_adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		new SearchByGenreRequest(this,_elems.get(position)).execute(_app.getURL()+_arrayList.get(position).getUri());
	}
}
