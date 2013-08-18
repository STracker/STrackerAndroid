package src.stracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import src.stracker.asynchttp.DummyRequest;
import src.stracker.asynchttp.MyRunnable;
import src.stracker.model.Comment;

@ContentView(R.layout.activity_comment)
public class CommentActivity extends RoboActivity {

	@InjectView(R.id.act_comment_user) TextView _userName;
	@InjectView(R.id.comment_text_act) TextView _commentText;
	@InjectView(R.id.act_comment_profile) Button _profileBtn;
  
	private STrackerApp _app;
	private Comment _comment;
	
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		_app = (STrackerApp) getApplication();
		_comment = getIntent().getParcelableExtra("comment");
		_userName.setText(_comment.getUserName());
		_commentText.setText(_comment.getBody());
		_profileBtn.setOnClickListener(btnHandler);
	}	
	
	View.OnClickListener btnHandler = new View.OnClickListener() {
	    public void onClick(View v) {
	    	Intent intent = new Intent(CommentActivity.this, ProfileActivity.class);
	    	intent.putExtra("uri", _comment.getUserUri());
	    	startActivity(intent);
	    }
	  };
	  
    /**
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.comment, menu);   
        return true;
    }
	
	/**
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	switch(item.getItemId()){
    	case R.id.action_delete_comment:
    		if(_app.getFbUser().getId().equals(_comment.getUserId())){
    			new DummyRequest(this, new MyRunnable() {
					@Override
					public void run() {
						Toast.makeText(CommentActivity.this, R.string.comment_error, Toast.LENGTH_SHORT).show();
					}
					@Override
					public <T> void runWithArgument(T response) {
						Toast.makeText(CommentActivity.this, R.string.comment_success, Toast.LENGTH_SHORT).show();
					}
				}).authorizedDelete(_comment.getUri());
    			finish();
    		} else {
    			Toast.makeText(this, R.string.comment_permission, Toast.LENGTH_SHORT).show();
    		}
    	}
    	return true;
    }
}
