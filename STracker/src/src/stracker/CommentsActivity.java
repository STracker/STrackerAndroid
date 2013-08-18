package src.stracker;

import java.util.ArrayList;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import roboguice.activity.RoboListActivity;
import roboguice.inject.ContentView;
import src.stracker.adapters.CommentsAdapter;
import src.stracker.asynchttp.CommentsRequest;
import src.stracker.asynchttp.MyRunnable;
import src.stracker.model.Comment;
import src.stracker.utils.Utils;

@ContentView(R.layout.activity_list)
public class CommentsActivity extends RoboListActivity {

	private CommentsAdapter _adapter;
	private ArrayList<Comment> _comments;
	private String _uri;
	
	/**
	 * @see roboguice.activity.RoboListActivity#onCreate(android.os.Bundle)
	 */
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		String uri = getIntent().getStringExtra("uri");
		new CommentsRequest(this, new MyRunnable() {
			@Override
			public void run() {
				Toast.makeText(CommentsActivity.this, R.string.error_comment, Toast.LENGTH_SHORT).show();
			}
			@Override
			public <T> void runWithArgument(T response) {
				setTitle("Comments");
				_comments = (ArrayList<Comment>) response;
				_uri = getIntent().getStringExtra("uri");
				_adapter = new CommentsAdapter(CommentsActivity.this, _comments);
				setListAdapter(_adapter);
			}
		}).get(uri);
	}
	
	/**
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent(this, CommentActivity.class);
		intent.putExtra("comment", _comments.get(position));
		startActivity(intent);
	}
	
	/**
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.comments, menu);   
        return true;
    }
	
	/**
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	switch(item.getItemId()){
    	case R.id.action_add_comment:
    		Utils.addComment(_uri, this);
    		break; 
    	}
    	return true;
    }
}
