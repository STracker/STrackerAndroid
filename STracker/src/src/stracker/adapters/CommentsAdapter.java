package src.stracker.adapters;

import java.util.List;

import src.stracker.R;
import src.stracker.model.Comment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * @author diogomatos
 * This class represents the adapter to a list of comments
 */
public class CommentsAdapter extends ArrayAdapter<Comment>{

	private LayoutInflater _inflater;
	
	/**
	 * @param context - Represents the context of an Activity
	 * @param elems - Represents a list of Comments
	 */
	public CommentsAdapter(Context context, List<Comment> elems){
		super(context, R.layout.comment_row, elems);
		_inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	/**
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		ValueHolder holder = new ValueHolder();
        //If the convertView is not yet constructed, affect the holder and save it in a Tag inside the view
		if(convertView == null){
            vi = _inflater.inflate(R.layout.comment_row, null);
            holder.comment =  (TextView)  vi.findViewById(R.id.comment_text);
            holder.userName = (TextView) vi.findViewById(R.id.comment_user);
            vi.setTag(holder);
        }
        else
        	holder = (ValueHolder) vi.getTag();
        	        
        Comment comment = getItem(position);	        
        // Setting all values in listview
        holder.comment.setText(comment.getBody());
        holder.userName.setText(comment.getUserName());
        return vi;
	}	
	
	/**
	 * This class is used to save the properties of a row in the list view
	 */
	private static class ValueHolder{
		TextView comment;
		TextView userName;
	}
}
