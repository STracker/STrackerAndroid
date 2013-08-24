package src.stracker.asynchttp;

import java.util.HashMap;

import android.content.Context;
import android.widget.Toast;
import src.stracker.R;
import src.stracker.STrackerApp;
import src.stracker.json.CommentsSerializer;
import src.stracker.json.JSONLocator;
import src.stracker.model.Comment;
import src.stracker.utils.Utils;

public class CommentRequests {

	private static CommentsSerializer commentsSerializer = (CommentsSerializer) JSONLocator.getInstance().getSerializer(Comment.class);
	
	public static void getComments(Context context, MyRunnable runnable, String uri){
		AsyncHttpRequest.get(context, runnable, commentsSerializer, uri);
	}
	
	public static void postComment(Context context, MyRunnable runnable, String uri, String comment){
		if(!Utils.checkLogin((STrackerApp)context.getApplicationContext())) return;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("", comment);
		AsyncHttpRequest.authorizedPost(context, runnable, commentsSerializer, uri, params);
	}
	
	public static void deleteComment(Context context, MyRunnable runnable, Comment comment){
		if(!Utils.checkLogin((STrackerApp)context.getApplicationContext())) return;
		if(((STrackerApp)context.getApplicationContext()).getFbUser().getId().equals(comment.getUserId())){
			AsyncHttpRequest.authorizedDelete(context, runnable, commentsSerializer, comment.getUri());
		} else {
			Toast.makeText(context, R.string.comment_permission, Toast.LENGTH_SHORT).show();
		}	
	}
	
}
