package src.stracker.first_activity;

import android.view.LayoutInflater;
import android.view.View;

/**
 * This interface is the contract to the objects that will be showed in main activity
 */
public interface IEntry {
	public int getViewType();
    public View getView(LayoutInflater inflater, View convertView);
}
