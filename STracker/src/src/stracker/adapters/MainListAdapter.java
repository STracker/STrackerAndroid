package src.stracker.adapters;

import java.util.List;
import src.stracker.first_activity.EntryType;
import src.stracker.first_activity.IEntry;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * @author diogomatos
 * Adapter to list of episodes releases by date
 */
public class MainListAdapter extends ArrayAdapter<IEntry> {
	
    private LayoutInflater _mInflater;
    private List<IEntry> _items;

    public MainListAdapter(Context context, List<IEntry> items) {
        super(context, 0, items);
        _items = items;
        _mInflater = LayoutInflater.from(context);
    }

    /**
     * @see android.widget.BaseAdapter#getViewTypeCount()
     */
    @Override
    public int getViewTypeCount() {
        return EntryType.values().length;

    }

    /**
     * @see android.widget.BaseAdapter#getItemViewType(int)
     */
    @Override
    public int getItemViewType(int position) {
        return _items.get(position).getViewType();
    }

    /**
     * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return _items.get(position).getView(_mInflater, convertView);
    }
}
