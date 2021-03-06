package src.stracker.first_activity;

import src.stracker.R;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * This class represents an item in listview 
 */
public class ItemEntry implements IEntry {
	
    private final String _episode;
    private final String _tvshowName;

    public ItemEntry(String episode, String tvshowName) {
    	_episode = episode;
    	_tvshowName = tvshowName;
    }

    /**
     * @see src.stracker.first_activity.IEntry#getViewType()
     */
    @Override
    public int getViewType() {
        return EntryType.LIST_ITEM.ordinal();
    }

    /**
     * @see src.stracker.first_activity.IEntry#getView(android.view.LayoutInflater, android.view.View)
     */
    @Override
    public View getView(LayoutInflater inflater, View convertView) {
        View view = convertView;
        ValueHolder holder = new ValueHolder();
        if (convertView == null) {
            view = (View) inflater.inflate(R.layout.item_entry, null);
            holder.episode = (TextView) view.findViewById(R.id.list_episode);
            holder.tvshowName = (TextView) view.findViewById(R.id.list_tvshowName);
            view.setTag(holder);
        } else {
            holder = (ValueHolder) view.getTag();
        }
        holder.episode.setText(_episode);
        holder.tvshowName.setText(_tvshowName);
        return view;
    }
    
    /**
	 * This class is used to save the properties of a row in the list view
	 */
    private static class ValueHolder{
    	TextView episode;
    	TextView tvshowName;
    }
}
