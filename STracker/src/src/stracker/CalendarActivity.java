package src.stracker;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import roboguice.activity.RoboListActivity;
import roboguice.inject.ContentView;
import src.stracker.adapters.MainListAdapter;
import src.stracker.first_activity.HeaderEntry;
import src.stracker.first_activity.IEntry;
import src.stracker.first_activity.ItemEntry;

/**
 * This activity represents the calendar of episodes of tv shows
 */
@ContentView(R.layout.activity_calendar)
public class CalendarActivity extends RoboListActivity {

	/**
	 * (non-Javadoc)
	 * @see roboguice.activity.RoboListActivity#onCreate(android.os.Bundle)
	 */
	@Override  
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		List<IEntry> items = new ArrayList<IEntry>();
        items.add(new HeaderEntry("Saturday, June 15, 2013"));
        items.add(new ItemEntry("S02E04", "Game of Thrones"));
        items.add(new ItemEntry("S02E05", "Revolution"));
        items.add(new ItemEntry("S02E06", "Supernatural"));
        items.add(new ItemEntry("S02E07", "Falling Skies"));
        items.add(new HeaderEntry("Sunday, June 16, 2013"));
        items.add(new ItemEntry("S02E08", "Castle"));
        items.add(new ItemEntry("S02E09", "How I Met Your Mother"));
        items.add(new ItemEntry("S02E10", "Franklin & Bash"));
        items.add(new ItemEntry("S02E11", "Boardwalk Empire"));

        MainListAdapter adapter = new MainListAdapter(this, items);
        setListAdapter(adapter);
	} 	
}
