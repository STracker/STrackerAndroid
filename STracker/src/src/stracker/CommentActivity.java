package src.stracker;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import src.stracker.asynchttp.UserRequest;
import src.stracker.model.Comment;

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
	}	
	
	View.OnClickListener btnHandler = new View.OnClickListener() {
	    public void onClick(View v) {
	    	new UserRequest(_context).authorizedGet(_app.getApiURL() + _comment.getUserUri(), _app);
	    }
	  };
}
