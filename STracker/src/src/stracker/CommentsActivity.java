package src.stracker;

import java.util.ArrayList;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import roboguice.event.Observes;
import roboguice.inject.ContentView;
import src.stracker.actions.SharedActions;
import src.stracker.adapters.CommentsAdapter;
import src.stracker.asynchttp.CommentRequests;
import src.stracker.asynchttp.MyRunnable;
import src.stracker.model.Comment;
import src.stracker.utils.ShakeDetector;

/**
 * @author diogomatos
 * This class represents a list of comments.
 */
@ContentView(R.layout.activity_list)
public class CommentsActivity extends BaseListActivity {

	private CommentsAdapter _adapter;
	private ArrayList<Comment> _comments;
	private String _uri;	
	
	/**
	 * @see roboguice.activity.RoboListActivity#onCreate(android.os.Bundle)
	 */
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		performRequest();
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
    		SharedActions.addComment(_uri, this);
    		break; 
    	}
    	return true;
    }
	
	/**
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> adapt, View view, int position, long id) {
		Intent intent = new Intent(this, CommentActivity.class);
		intent.putExtra(COMMENT_PARAM, _comments.get(position));
		startActivity(intent);
	}
	
	/**
	 * Event associated to shake motion
	 * @param event - shake event
	 */
	public void handleShake(@Observes ShakeDetector.OnShakeEvent event) {
		performRequest();
	}
	
	/**
	 * This method is used to perform the HTTP request command
	 */
	private void performRequest(){
		CommentRequests.getComments(this, new MyRunnable() {
			@Override
			public void run() {
				Toast.makeText(CommentsActivity.this, R.string.error_comment, Toast.LENGTH_SHORT).show();
			}
			@SuppressWarnings("unchecked")
			@Override
			public <T> void runWithArgument(T response) {
				setTitle(getString(R.string.subitem_comments));
				_comments = (ArrayList<Comment>) response;
				_uri = getIntent().getStringExtra(URI_PARAM);
				_adapter = new CommentsAdapter(CommentsActivity.this, _comments);
				_listView.setAdapter(_adapter);
			}
		}, getIntent().getStringExtra(URI_PARAM));
	}
}
