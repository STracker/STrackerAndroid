package src.stracker;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import src.stracker.model.Comment;

@ContentView(R.layout.activity_comment)
public class CommentActivity extends RoboActivity {

	@InjectView(R.id.act_comment_user) TextView _userName;
	@InjectView(R.id.comment_text) TextView _commentText;
	@InjectView(R.id.act_comment_profile) Button _profileBtn;

	private STrackerApp _app;
	private Comment _comment;
	
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		_app = (STrackerApp) getApplication();
		
	}	
}
