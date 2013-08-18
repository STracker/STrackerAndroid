package src.stracker;

import java.util.ArrayList;
import android.os.Bundle;
import roboguice.activity.RoboListActivity;
import roboguice.inject.ContentView;
import src.stracker.adapters.ActorsAdapter;
import src.stracker.model.Actor;

/**
 * @author diogomatos
 * This Activity represents the list of Actors from a telvision show
 */
@ContentView(R.layout.activity_list)
public class ActorsActivity extends RoboListActivity {
	
	private ArrayList<Actor> _actors;
	private ActorsAdapter _adapter;
	
	/**
	 * @see roboguice.activity.RoboListActivity#onCreate(android.os.Bundle)
	 */
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		_actors = getIntent().getParcelableArrayListExtra("list");
		_adapter = new ActorsAdapter(this, _actors);
		setListAdapter(_adapter);
	}
}
