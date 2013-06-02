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

@ContentView(R.layout.activity_result)
public class ResultActivity extends RoboListActivity {

	private ArrayAdapter<String> _adapter;
	private STrackerApp _app;
	private ArrayList<TvShowSynopse> _arrayList;
	private ArrayList<SeasonSynopse> _seasonList;
	private ArrayList<EpisodeSynopse> _episodeList;
	private String _tvShowId;
	private int _seasonNumber;
	
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
        	_tvShowId = getIntent().getStringExtra("tvShowId");
        	_seasonList = getIntent().getParcelableArrayListExtra("list");
        	for(SeasonSynopse synopse : _seasonList)
            	elems.add("Season " + synopse.getNumber());
        }
           
        //List of episodes synopses
        if(getIntent().getStringExtra("type").equals("EPISODESYNOPSE")){
        	_seasonNumber = getIntent().getIntExtra("seasonNumber", 999);
        	setTitle("Season " + _seasonNumber);
        	_tvShowId = getIntent().getStringExtra("tvShowId");
        	_episodeList = getIntent().getParcelableArrayListExtra("list");
        	for(EpisodeSynopse synopse : _episodeList)
        		elems.add(synopse.getNumber() + ". " + synopse.getName());
        }
        
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
		if(_arrayList != null){
			TvShowSynopse tvshow = _arrayList.get(position);
			new TvShowRequest(this).execute(_app.getURL()+"tvshows/"+tvshow.getId());
		}
		else if(_seasonList != null){
			SeasonSynopse season = _seasonList.get(position);
			new EpisodesRequest(this, season.getNumber()).execute(_app.getURL()+"tvshows/" + _tvShowId + "/seasons/"+ season.getNumber());
		}
		else if(_episodeList != null){
			EpisodeSynopse episode = _episodeList.get(position);
			new EpisodeRequest(this).execute(_app.getURL()+"tvshows/" + _tvShowId + "/seasons/" + _seasonNumber + "/episodes/" + episode.getNumber());
		}
	}
}
