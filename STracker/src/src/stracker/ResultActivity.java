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
import src.stracker.model.EpisodeSynopse;
import src.stracker.model.SeasonSynopse;
import src.stracker.model.TvShowSynopse;
import src.stracker.asynchttp.EpisodeRequest;
import src.stracker.asynchttp.EpisodesRequest;
import src.stracker.asynchttp.TvShowRequest;

/**
 * This Activity represents a list of synopses results.
 * When a request to the STracker Server is a list of synopses this Activity is used to show the results.
 */
@ContentView(R.layout.activity_list)
public class ResultActivity extends RoboListActivity {

	private ArrayAdapter<String> _adapter;
	private STrackerApp _app;
	private ArrayList<TvShowSynopse> _arrayList;
	private ArrayList<SeasonSynopse> _seasonList;
	private ArrayList<EpisodeSynopse> _episodeList;
	private final String _separator = ". ";
	
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		_app = (STrackerApp) getApplication();
        List<String> elems = new ArrayList<String>();
        
        //List of tvshow synopses
        if(getIntent().getStringExtra("type").equals("TVSHOWSYNOPSE")){
        	setTitle(getIntent().getStringExtra("genre"));
        	_arrayList = getIntent().getParcelableArrayListExtra("list");
        	for(TvShowSynopse synopse : _arrayList)
            	elems.add(synopse.getName());
        }
        
        //List of seasons synopses
        if(getIntent().getStringExtra("type").equals("SEASONSYNOPSE")){
        	setTitle("Season's List");
        	_seasonList = getIntent().getParcelableArrayListExtra("list");
        	for(SeasonSynopse synopse : _seasonList)
            	elems.add("Season " + synopse.getNumber());
        }
           
        //List of episodes synopses
        if(getIntent().getStringExtra("type").equals("EPISODESYNOPSE")){
        	String title = getIntent().getStringExtra("title");
        	setTitle(title);
        	_episodeList = getIntent().getParcelableArrayListExtra("list");
        	for(EpisodeSynopse synopse : _episodeList)
        		elems.add(synopse.getNumber() + _separator + synopse.getName());
        }
        
        //Create the list view adapter with the specific results
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
	
	/**
	 * (non-Javadoc)
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 * When a list result is pressed make the specific request according the type of the results
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		if(_arrayList != null){
			TvShowSynopse tvshow = _arrayList.get(position);
			new TvShowRequest(this).get(_app.getApiURL()+tvshow.getUri());
		}
		else if(_seasonList != null){
			SeasonSynopse season = _seasonList.get(position);
			new EpisodesRequest(this, season.getNumber()).get(_app.getApiURL()+season.getUri());
		}
		else if(_episodeList != null){
			EpisodeSynopse episode = _episodeList.get(position);
			new EpisodeRequest(this).get(_app.getApiURL()+episode.getUri());
		}
	}
}
