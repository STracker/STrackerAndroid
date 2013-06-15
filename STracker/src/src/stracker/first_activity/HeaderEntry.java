package src.stracker.first_activity;

import src.stracker.R;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * This class represents an header in listview 
 */
public class HeaderEntry implements IEntry {

	private final String _name;

    public HeaderEntry(String name) {
        _name = name;
    }

    /**
     * (non-Javadoc)
     * @see src.stracker.first_activity.IEntry#getViewType()
     */
    @Override
    public int getViewType() {
        return EntryType.HEADER_ITEM.ordinal();
    }

    /**
     * (non-Javadoc)
     * @see src.stracker.first_activity.IEntry#getView(android.view.LayoutInflater, android.view.View)
     */
    @Override
    public View getView(LayoutInflater inflater, View convertView) {
        View view = convertView;
        ValueHolder holder = new ValueHolder();
        if (convertView == null) {
            view = (View) inflater.inflate(R.layout.header_entry, null);
            holder.headerText = (TextView) view.findViewById(R.id.header_text);
            view.setTag(holder);
        } else {
            holder = (ValueHolder) view.getTag();
        }
        holder.headerText.setText(_name);
        return view;
    }
    
    private static class ValueHolder{
    	TextView headerText;
    }
}
