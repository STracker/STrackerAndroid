package src.stracker;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import roboguice.inject.ContentView;
import src.stracker.adapters.MainListAdapter;
import src.stracker.asynchttp.CalendarRequest;
import src.stracker.asynchttp.MyRunnable;
import src.stracker.first_activity.EntryType;
import src.stracker.first_activity.HeaderEntry;
import src.stracker.first_activity.IEntry;
import src.stracker.first_activity.ItemEntry;
import src.stracker.model.EpisodeSynopse;

/**
 * @author diogomatos
 * This activity represents the calendar of episodes of tv shows
 */
@ContentView(R.layout.activity_list)
public class CalendarActivity extends BaseListActivity {

	private ArrayList<EpisodeSynopse> _arrayList;
	private MainListAdapter _adapter;
	
	/**
	 * @see src.stracker.BaseListActivity#onCreate(android.os.Bundle)
	 */
	@Override  
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new CalendarRequest(this, new MyRunnable() {
			@Override
			public void run() {
				Toast.makeText(CalendarActivity.this, R.string.error_calendar, Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public <T> void runWithArgument(T response) {
				_arrayList = (ArrayList<EpisodeSynopse>) response;
				List<IEntry> items = buildCalendarView();
		        _adapter = new MainListAdapter(CalendarActivity.this, items); 
		        _listView.setAdapter(_adapter);
			}
		}).authorizedGet(getString(R.string.uri_user_newepisodes));
	} 
	
	/**
	 * This method return a list of entries to show in calendar view
	 * @return list of IEntry items
	 */
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
	
	/**
	 * This method construct a string title with episode information
	 * @param episode - episode synopse
	 * @return string - episode title
	 */
	private String buildEpisodePrefix(EpisodeSynopse episode){
		return "S"+
			   ((episode.getSeasonNumber() < 10) ? "0" : "") + episode.getSeasonNumber() + 
			   "E" +
			   ((episode.getNumber() < 10) ? "0" : "") + episode.getNumber();
	}

	/**
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> adapt, View view, int position, long id) {
		if(_adapter.getItem(position).getViewType() == EntryType.HEADER_ITEM.ordinal())
			return;
		//EpisodeSynopse episode = _arrayList.get(position);
		//new EpisodeRequest(this).get(episode.getUri());
	}
}
