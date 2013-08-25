package src.stracker;

import java.util.ArrayList;

import roboguice.inject.ContentView;
import src.stracker.adapters.SuggestionAdapter;
import src.stracker.model.Suggestion;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

/**
 * @author diogomatos
 * This activity represents a list of suggestions
 */
@ContentView(R.layout.activity_list)
public class SuggestionActivity extends BaseListActivity {

	private ArrayList<Suggestion> _suggestions;
	private SuggestionAdapter _adapter;
	
	/**
	 * @see src.stracker.BaseListActivity#onCreate(android.os.Bundle)
	 */
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setTitle(getString(R.string.profile_suggestions));
		_suggestions = _application.getFbUser().getSuggestions();
		_adapter = new SuggestionAdapter(this, _suggestions);
		_listView.setAdapter(_adapter);
	}
	
	/**
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> adapt, View view, int position, long id) {
		Intent intent = new Intent(this,TvShowActivity.class);
		intent.putExtra("tvShowUri", _suggestions.get(position).getTvShow().getUri());
		startActivity(intent);
	}
}
