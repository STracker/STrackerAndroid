package src.stracker;

import java.util.ArrayList;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import roboguice.activity.RoboListActivity;
import roboguice.inject.ContentView;
import src.stracker.adapters.CommentsAdapter;
import src.stracker.model.Comment;
import src.stracker.utils.Utils;

@ContentView(R.layout.activity_list)
public class CommentsActivity extends RoboListActivity {

	private CommentsAdapter _adapter;
	private ArrayList<Comment> _comments;
	private STrackerApp _app;
	private String _uri;
	
	/**
	 * (non-Javadoc)
	 * @see roboguice.activity.RoboListActivity#onCreate(android.os.Bundle)
	 */
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		_app = (STrackerApp) getApplication();
		setTitle("Comments");
		_comments = getIntent().getParcelableArrayListExtra("list");
		_uri = getIntent().getStringExtra("uri");
		_adapter = new CommentsAdapter(this, _comments);
		setListAdapter(_adapter);
	}
	
	/**
	 * (non-Javadoc)
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		if(!Utils.checkLogin(this, _app))
			return;
		Intent intent = new Intent(this, CommentActivity.class);
		intent.putExtra("comment", _comments.get(position));
		startActivity(intent);
	}
	
	/**
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.comments, menu);   
        return true;
    }
	
	/**
	 * (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	switch(item.getItemId()){
    	case R.id.action_add_comment:
    		if(!Utils.checkLogin(this, _app))
    			break;
    		Utils.addComment(getString(R.string.uri_host_api) + _uri, this);
    		break; 
    	}
    	return true;
    }
}
