package src.stracker;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import roboguice.activity.RoboListActivity;
import roboguice.inject.ContentView;
import src.stracker.tasks.SearchByGenreRequest;
 
@ContentView(R.layout.activity_genre)
public class GenreActivity extends RoboListActivity {
	
	private STrackerApp _app;
	private ArrayAdapter<String> _adapter;
	private final String[] _elems = { "Action", "Adventure", "Animation", "Biography", "Comedy", "Crime", 
			"Documentary", "Drama", "Family", "Fantasy", "Film-Noir", "History", "Horror", "Music", 
			"Musical", "Mystery", "Romance","Sci-Fi", "Short", "Sport", "Thriller", "War", "Western"
	};
	
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
        _app = (STrackerApp) getApplication();
        _adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, _elems);
        setListAdapter(_adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		new SearchByGenreRequest(this).execute(_app.getURL()+"tvshows?genre="+_elems[position]);
	}
}