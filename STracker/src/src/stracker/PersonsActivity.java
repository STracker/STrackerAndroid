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
import src.stracker.model.Person;

@ContentView(R.layout.activity_result)
public class PersonsActivity extends RoboListActivity {

	private ArrayAdapter<String> _adapter;
	private ArrayList<Person> _arrayList;
	
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		_arrayList = getIntent().getParcelableArrayListExtra("list");
		List<String> elems = new ArrayList<String>();
		for(Person person : _arrayList)
        	elems.add(person.getName());
		
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
}
