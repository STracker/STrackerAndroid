package src.stracker;

import java.util.ArrayList;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import roboguice.activity.RoboListActivity;
import roboguice.inject.ContentView;
import src.stracker.adapters.CommentsAdapter;
import src.stracker.model.Comment;

@ContentView(R.layout.activity_comments)
public class CommentsActivity extends RoboListActivity {

	private CommentsAdapter _adapter;
	private ArrayList<Comment> _comments;
	
	/**
	 * (non-Javadoc)
	 * @see roboguice.activity.RoboListActivity#onCreate(android.os.Bundle)
	 */
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setTitle("Comments");
		_comments = getIntent().getParcelableArrayListExtra("list");
		_adapter = new CommentsAdapter(this, _comments);
		setListAdapter(_adapter);
	}
	
	/**
	 * (non-Javadoc)
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
	}
}
