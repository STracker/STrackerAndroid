package src.stracker;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import roboguice.activity.RoboListActivity;
import roboguice.inject.ContentView;
import src.stracker.model.TvShowSynopse;

@ContentView(R.layout.activity_result)
public class ResultActivity extends RoboListActivity {

	private ArrayAdapter<String> _adapter;
	
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
        ArrayList<TvShowSynopse> arrayList = getIntent().getParcelableArrayListExtra("list");
        List<String> elems = new ArrayList<String>();
        for(TvShowSynopse synopse : arrayList)
        	elems.add(synopse.getName());
        _adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, elems);
        setListAdapter(_adapter);
	}
}
