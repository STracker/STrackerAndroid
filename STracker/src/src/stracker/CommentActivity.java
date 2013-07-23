package src.stracker;

import android.app.Activity;
import android.content.Context;
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
import src.stracker.asynchttp.UserRequest;
import src.stracker.model.Comment;
import src.stracker.utils.Utils;

@ContentView(R.layout.activity_comment)
public class CommentActivity extends RoboActivity {

	@InjectView(R.id.act_comment_user) TextView _userName;
	@InjectView(R.id.comment_text_act) TextView _commentText;
	@InjectView(R.id.act_comment_profile) Button _profileBtn;
  
	private STrackerApp _app;
	private Comment _comment;
	private Context _context;
	
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		_context = this;
		_app = (STrackerApp) getApplication();
		_comment = getIntent().getParcelableExtra("comment");
		_userName.setText(_comment.getUserName());
		_commentText.setText(_comment.getBody());
		_profileBtn.setOnClickListener(btnHandler);
	}	
	
	View.OnClickListener btnHandler = new View.OnClickListener() {
	    public void onClick(View v) {
	    	if(!Utils.checkLogin((Activity)_context, _app))
				return;
	    	new UserRequest(_context).authorizedGet(getString(R.string.uri_host_api) + _comment.getUserUri());
	    }
	  };
	  
    /**
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.comment, menu);   
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
    	case R.id.action_delete_comment:
    		if(!Utils.checkLogin(this, _app))
    			break;
    		if(_app.getFbUser().getId().equals(_comment.getUserId())){
    			new DummyRequest(this).authorizedDelete(getString(R.string.uri_host_api) + _comment.getUri());
    			finish();
    		} else {
    			Toast.makeText(this, "You don't have permission to delete this comment!", Toast.LENGTH_SHORT).show();
    		}
    	}
    	return true;
    }
}
