package src.stracker.asynchttp;

import java.util.ArrayList;

import src.stracker.CommentsActivity;
import src.stracker.json.CommentsSerializer;
import src.stracker.json.JSONLocator;
import src.stracker.model.Comment;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CommentsRequest extends AbstractAsyncHttp {

	private CommentsSerializer _serializer;
	private String _tvshowId;
	
	public CommentsRequest(Context context, String tvshowId){
		super(context);
		_tvshowId = tvshowId;
		_serializer = (CommentsSerializer) JSONLocator.getInstance().getSerializer(Comment.class);
	}
	
	@Override
	protected void onSuccessHook(String response) {
		try{
			ArrayList<Comment> list = _serializer.deserialize(response);
			Intent intent = new Intent(_context,CommentsActivity.class);
			intent.putExtra("list", list);
			intent.putExtra("id", _tvshowId);
			_context.startActivity(intent);
		}catch(Exception e){
			onErrorHook(e,e.getClass().getSimpleName());
		}
	}

	@Override
	protected void onErrorHook(Throwable e, String response) {
		Toast.makeText(_context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
	}
}
