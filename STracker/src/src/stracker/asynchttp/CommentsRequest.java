package src.stracker.asynchttp;

import java.util.ArrayList;

import src.stracker.json.CommentsSerializer;
import src.stracker.json.JSONLocator;
import src.stracker.model.Comment;
import android.content.Context;

/**
 * @author diogomatos
 * This implementation represents a request to the comments of a television show or an episode
 */
public class CommentsRequest extends AbstractAsyncHttp {

	private CommentsSerializer _serializer;
	
	/**
	 * The constructor of the comments request 
	 * @param context - activity context
	 * @param runnable - callback to be invoked
	 */
	public CommentsRequest(Context context, MyRunnable runnable){
		super(context, runnable);
		_serializer = (CommentsSerializer) JSONLocator.getInstance().getSerializer(Comment.class);
	}
	
	/**
	 * @see src.stracker.asynchttp.AbstractAsyncHttp#onSuccessHook(java.lang.String)
	 * @param response - string with the Http Response 
	 */
	@Override
	protected void onSuccessHook(String response) {
		ArrayList<Comment> list = _serializer.deserialize(response);
		_runnable.runWithArgument(list);
	}
}
