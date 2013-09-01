package src.stracker;

import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import roboguice.inject.ContentView;
import src.stracker.actions.EpisodeActions;
import src.stracker.adapters.MainListAdapter;
import src.stracker.asynchttp.MyRunnable;
import src.stracker.asynchttp.UserRequests;
import src.stracker.first_activity.EntryType;
import src.stracker.first_activity.HeaderEntry;
import src.stracker.first_activity.IEntry;
import src.stracker.first_activity.ItemEntry;
import src.stracker.model.Calendar;
import src.stracker.model.Calendar.CalendarEntry;
import src.stracker.model.EpisodeSynopse;

/** 
 * @author diogomatos
 * This activity represents the calendar of episodes of television shows
 */
@ContentView(R.layout.activity_list)
public class CalendarActivity extends BaseListActivity {

	private ArrayList<Calendar> _arrayList;
	private MainListAdapter _adapter;
	private ArrayList<String> _uris;
	
	/**
	 * @see src.stracker.BaseListActivity#onCreate(android.os.Bundle)
	 */
	@Override  
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		UserRequests.getCalendar(this, new MyRunnable() {
			@Override
			public void run() {
				Toast.makeText(CalendarActivity.this, R.string.error_calendar, Toast.LENGTH_SHORT).show();
			}
			@SuppressWarnings("unchecked")
			@Override
			public <T> void runWithArgument(T response) {
				_arrayList = (ArrayList<Calendar>) response;
				_uris = new ArrayList<String>();
				List<IEntry> items = buildCalendarView();
		        _adapter = new MainListAdapter(CalendarActivity.this, items); 
		        _listView.setAdapter(_adapter);
			}
		});
	} 
	
	/**
	 * This method return a list of entries to show in calendar view
	 * @return list of IEntry items
	 */
	private List<IEntry> buildCalendarView(){
		List<IEntry> items = new ArrayList<IEntry>();
		for(Calendar calendar : _arrayList){
			items.add(new HeaderEntry(calendar.getDate()));
			_uris.add("");
			for(CalendarEntry entrie : calendar.getEntries()){
				for(EpisodeSynopse synopse : entrie.getEpisodes()){
					items.add(new ItemEntry(EpisodeActions.buildEpisodePrefix(synopse), entrie.getTvShow().getName()));
					_uris.add(synopse.getUri());
				}
			}
		}
		return items;
	}

	/**
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> adapt, View view, int position, long id) {
		if(_adapter.getItem(position).getViewType() == EntryType.HEADER_ITEM.ordinal())
			return;
		Intent intent = new Intent(this, EpisodeActivity.class);
		intent.putExtra(URI_PARAM, _uris.get(position));
		startActivity(intent);
	}
}
