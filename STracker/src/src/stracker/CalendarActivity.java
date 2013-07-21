package src.stracker;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import roboguice.activity.RoboListActivity;
import roboguice.inject.ContentView;
import src.stracker.adapters.MainListAdapter;
import src.stracker.asynchttp.EpisodeRequest;
import src.stracker.first_activity.HeaderEntry;
import src.stracker.first_activity.IEntry;
import src.stracker.first_activity.ItemEntry;
import src.stracker.model.EpisodeSynopse;

/**
 * This activity represents the calendar of episodes of tv shows
 */
@ContentView(R.layout.activity_list)
public class CalendarActivity extends RoboListActivity {

	private ArrayList<EpisodeSynopse> _arrayList;
	
	/**
	 * (non-Javadoc)
	 * @see roboguice.activity.RoboListActivity#onCreate(android.os.Bundle)
	 */
	@Override  
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		_arrayList = getIntent().getParcelableArrayListExtra("list");
		List<IEntry> items = buildCalendarView();
        MainListAdapter adapter = new MainListAdapter(this, items); 
        setListAdapter(adapter);
	} 
	
	/**
	 * (non-Javadoc)
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 * When a list result is pressed make the specific request according the type of the results
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		EpisodeSynopse episode = _arrayList.get(position);
		new EpisodeRequest(this).get(getString(R.string.uri_host_api)+episode.getUri());
	}
	
	private List<IEntry> buildCalendarView(){
		List<IEntry> items = new ArrayList<IEntry>();
		String last = null, current = null;
		for(int i = 0; i<_arrayList.size(); i++){
			EpisodeSynopse episode = _arrayList.get(i);
			current = episode.getDate();
			if(!current.equals(last))
				items.add(new HeaderEntry(episode.getDate()));
			items.add(new ItemEntry(buildEpisodePrefix(episode), episode.getName()));
			last = current;
		}
		return items;
	}
	
	private String buildEpisodePrefix(EpisodeSynopse episode){
		return "S"+
			   ((episode.getSeasonNumber() < 10) ? "0" : "") + 
			   episode.getSeasonNumber() + 
			   "E" +
			   ((episode.getNumber() < 10) ? "0" : "") +
			   episode.getNumber();
	}
}
