package src.stracker.adapters;

import java.util.List;

import src.stracker.BaseActivity;
import src.stracker.R;
import src.stracker.actions.UserActions;
import src.stracker.asynchttp.MyRunnable;
import src.stracker.asynchttp.UserRequests;
import src.stracker.model.UserSynopse;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author diogomatos
 * This class represents an adapter to a list of friend requests.
 */
public class FriendReqAdapter extends ArrayAdapter<UserSynopse> {

	private LayoutInflater _inflater;
	
	/**
	 * @param context - Represents the context of an Activity
	 * @param elems - Represents a list of suggestions
	 */
	public FriendReqAdapter(Context context, List<UserSynopse> elems){
		super(context, R.layout.friend_rquest_row, elems);
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
            vi = _inflater.inflate(R.layout.friend_rquest_row, null);
            holder.deleteButton = (Button)   vi.findViewById(R.id.friend_req_del_btn); 
            holder.addButton    = (Button)   vi.findViewById(R.id.friend_req_add_btn);
            holder.user_name    = (TextView) vi.findViewById(R.id.friend_req_name);
            vi.setTag(holder);
        }
        else
        	holder = (ValueHolder) vi.getTag();
        	        
        final UserSynopse synopse = getItem(position);	
   
        // Setting all values in listview
        holder.user_name.setText(synopse.getName());
        //Reject button
        holder.deleteButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				UserRequests.deleteFriendRequest(getContext(), new MyRunnable() {
					@Override
					public void run() {
						Toast.makeText(getContext(), R.string.error_friend_req, Toast.LENGTH_SHORT).show();
					}
					@Override
					public <T> void runWithArgument(T response) {
						Toast.makeText(getContext(), R.string.friend_req_reject, Toast.LENGTH_SHORT).show();
						UserActions.removeFriendFromRequests((BaseActivity) getContext(), synopse);
						remove(synopse);
					}
				}, synopse.getId());
			}
		});
        //Accept button
        holder.addButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				UserRequests.postAcceptFriendRequest(getContext(), new MyRunnable() {
					@Override
					public void run() {
						Toast.makeText(getContext(), R.string.error_friend_req, Toast.LENGTH_SHORT).show();
					}
					@Override
					public <T> void runWithArgument(T response) {
						Toast.makeText(getContext(), R.string.friend_req_accept, Toast.LENGTH_SHORT).show();
						UserActions.acceptFriendRequest((BaseActivity) getContext(), synopse);
						remove(synopse);
					}
				}, synopse.getId());
			}
		});
        return vi;
	}
	
	/**
	 * This class is used to save the properties of a row in the list view
	 */
	private static class ValueHolder{
		Button deleteButton;
		Button addButton;
		TextView user_name;
	}
}
