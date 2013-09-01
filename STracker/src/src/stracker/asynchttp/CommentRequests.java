package src.stracker.asynchttp;

import java.util.HashMap;
import android.content.Context;
import android.widget.Toast;
import src.stracker.R;
import src.stracker.STrackerApp;
import src.stracker.json.CommentsSerializer;
import src.stracker.json.JSONLocator;
import src.stracker.model.Comment;

/**
 * @author diogomatos
 * This class have all the HTTP requests for a Comment.
 */
public class CommentRequests {

	private static CommentsSerializer commentsSerializer = (CommentsSerializer) JSONLocator.getInstance().getSerializer(Comment.class);
	
	/**
	 * This method represents a request to a comment.
	 * @param context - context of the Activity where the request occur
	 * @param runnable - callback that will be called after the HTTP request
	 * @param uri - URI of the resource
	 */
	public static void getComments(Context context, MyRunnable runnable, String uri){
		AsyncHttpRequest.get(context, runnable, commentsSerializer, uri);
	}
	
	/**
	 * This method represents a post of a new comment.
	 * @param context - context of the Activity where the request occur
	 * @param runnable - callback that will be called after the HTTP request
	 * @param uri - URI of the resource
	 * @param comment - reference to the comment.
	 */
	public static void postComment(Context context, MyRunnable runnable, String uri, String comment){
		if(((STrackerApp)context.getApplicationContext()).getUserManager().get(context) == null) return;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("", comment);
		AsyncHttpRequest.authorizedPost(context, runnable, commentsSerializer, uri, params);
	}
	
	/**
	 * This method represents a delete of a comment.
	 * @param context - context of the Activity where the request occur
	 * @param runnable - callback that will be called after the HTTP request
	 * @param comment - reference to the comment.
	 */
	public static void deleteComment(Context context, MyRunnable runnable, Comment comment){
		if(((STrackerApp)context.getApplicationContext()).getUserManager().get(context) == null) return;
		if(((STrackerApp)context.getApplicationContext()).getUserManager().get(context).getId().equals(comment.getUserId())){
			AsyncHttpRequest.authorizedDelete(context, runnable, commentsSerializer, comment.getUri());
		} else {
			Toast.makeText(context, R.string.comment_permission, Toast.LENGTH_SHORT).show();
		}	
	}
	
}
