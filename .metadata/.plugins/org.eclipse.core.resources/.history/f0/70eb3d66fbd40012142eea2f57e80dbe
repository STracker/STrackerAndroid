package src.stracker;

import java.util.ArrayList;
import android.os.Bundle;
import roboguice.activity.RoboListActivity;
import roboguice.inject.ContentView;
import src.stracker.adapters.ActorsAdapter;
import src.stracker.model.Actor;
    
@ContentView(R.layout.activity_actors)
public class ActorsActivity extends RoboListActivity {
	
	private ArrayList<Actor> _actors;
	private ActorsAdapter _adapter;
	
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		_actors = getIntent().getParcelableArrayListExtra("list");
		_adapter = new ActorsAdapter(this, _actors);
		setListAdapter(_adapter);
	}
}
