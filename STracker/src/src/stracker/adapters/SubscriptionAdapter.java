package src.stracker.adapters;

import java.util.List;

import src.stracker.R;
import src.stracker.model.Subscription;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * @author diogomatos
 * This class represents an adapter to a list of subscriptions
 */
public class SubscriptionAdapter extends ArrayAdapter<Subscription> {
	
	private LayoutInflater _inflater;
	
	/**
	 * @param context - Represents the context of an Activity
	 * @param elems - Represents a list of Subscriptions
	 */
	public SubscriptionAdapter(Context context, List<Subscription> elems){
		super(context, R.layout.subscription_row, elems);
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
            vi = _inflater.inflate(R.layout.subscription_row, null);
            holder.tvshowName =  (TextView)  vi.findViewById(R.id.subs_tvshow_name);
            holder.episodesWatched = (TextView) vi.findViewById(R.id.subs_watched);
            vi.setTag(holder);
        }
        else
        	holder = (ValueHolder) vi.getTag();
        	        
        Subscription subscription = getItem(position);	        
        // Setting all values in listview
        holder.tvshowName.setText(subscription.getTvShowSynope().getName());
        holder.episodesWatched.setText(subscription.getWatchedEpisodes().size()+"");
        return vi;
	}	
	
	/**
	 * This class is used to save the properties of a row in the list view
	 */
	private static class ValueHolder{
		TextView tvshowName;
		TextView episodesWatched;
	}
}
