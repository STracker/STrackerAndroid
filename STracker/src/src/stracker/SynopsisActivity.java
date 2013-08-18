package src.stracker;

import java.util.ArrayList;
import java.util.List;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import roboguice.activity.RoboListActivity;
import roboguice.inject.ContentView;

/**
 * @author diogomatos
 * This Activity represents a list of synopses results.
 * When a request to the STracker Server is a list of synopses this Activity is used to show the results.
 */
@ContentView(R.layout.activity_list)
public abstract class SynopsisActivity<T> extends RoboListActivity {

	private ArrayAdapter<String> _adapter;
	protected ArrayList<T> _synopses;
	
	/**
	 * @see roboguice.activity.RoboListActivity#onCreate(android.os.Bundle)
	 */
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		fetchRequest(getIntent().getStringExtra("uri"));
	}
	
	protected void setAdapter(List<String> elems){
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
	
	protected abstract void fetchRequest(String uri);
}
