package src.stracker;

import java.util.ArrayList;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import roboguice.inject.ContentView;
import src.stracker.adapters.ActorsAdapter;
import src.stracker.model.Actor;

/**
 * @author diogomatos
 * This Activity represents the list of Actors from a television show
 */
@ContentView(R.layout.activity_list)
public class ActorsActivity extends BaseListActivity {
	
	private ArrayList<Actor> _actors;
	private ActorsAdapter _adapter;
	
	/**
	 * @see src.stracker.BaseListActivity#onCreate(android.os.Bundle)
	 */
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		_actors = getIntent().getParcelableArrayListExtra("list");
		_adapter = new ActorsAdapter(this, _actors);
		_listView.setAdapter(_adapter);
	}

	/**
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {}
}
