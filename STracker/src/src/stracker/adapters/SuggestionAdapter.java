package src.stracker.adapters;

import java.util.List;
import src.stracker.R;
import src.stracker.asynchttp.MyRunnable;
import src.stracker.asynchttp.UserRequests;
import src.stracker.model.Suggestion;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/** 
 * This class represents the adapter to a list of suggestions
 */
public class SuggestionAdapter extends ArrayAdapter<Suggestion> {

	private LayoutInflater _inflater;
	
	/**
	 * @param context - Represents the context of an Activity
	 * @param elems - Represents a list of suggestions
	 */
	public SuggestionAdapter(Context context, List<Suggestion> elems){
		super(context, R.layout.suggestion_row, elems);
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
            vi = _inflater.inflate(R.layout.suggestion_row, null);
            holder.deleteButton    = (Button)   vi.findViewById(R.id.suggestion_del_btn); 
            holder.suggestion_name = (TextView) vi.findViewById(R.id.suggestion_name);
            holder.suggestion_user = (TextView) vi.findViewById(R.id.suggestion_user);
            vi.setTag(holder);
        }
        else
        	holder = (ValueHolder) vi.getTag();
        	        
        final Suggestion suggestion = getItem(position);	        
        // Setting all values in listview
        holder.suggestion_name.setText(suggestion.getTvShow().getName());
        holder.suggestion_user.setText(suggestion.getUser().getName());
        holder.deleteButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				UserRequests.deleteSuggestion(getContext(), new MyRunnable() {
					@Override
					public void run() {
						Toast.makeText(getContext(), R.string.error_suggestion, Toast.LENGTH_SHORT).show();
					}
					@Override
					public <T> void runWithArgument(T response) {
						Toast.makeText(getContext(), R.string.suggestion_rejected, Toast.LENGTH_SHORT).show();
						remove(suggestion);
					}
				}, suggestion.getTvShow().getId(), suggestion.getUser().getId());
			}
		});
        return vi;
	}
	
	/**
	 * This class is used to save the properties of a row in the list view
	 */
	private static class ValueHolder{
		Button deleteButton;
		TextView suggestion_name;
		TextView suggestion_user;
	}
}
