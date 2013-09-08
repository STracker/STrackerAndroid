package src.stracker.adapters;

import java.util.List;
import com.loopj.android.image.SmartImageView;
import src.stracker.R;
import src.stracker.model.TvShowSynopse;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/** 
 * This class represents the adapter to a list of television shows synopsis
 */
public class TvShowSynopseAdapter extends ArrayAdapter<TvShowSynopse>{

	private LayoutInflater _inflater;
	
	public TvShowSynopseAdapter(Context context, List<TvShowSynopse> elems){
		super(context,R.layout.tvshowsynopse_row,elems);
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
            vi = _inflater.inflate(R.layout.tvshowsynopse_row, null);
            holder.photo = (SmartImageView) vi.findViewById(R.id.tvshow_photo_row); 
            holder.name =  (TextView)  vi.findViewById(R.id.tvshow_name_row);
            vi.setTag(holder);
        }
        else
        	holder = (ValueHolder) vi.getTag();
        	        
        TvShowSynopse synopse = getItem(position);	        
        // Setting all values in listview
        holder.name.setText(synopse.getName());
        holder.photo.setImageUrl(synopse.getPosterUrl());
        return vi;
	}	
	
	/**
	 * This class is used to save the properties of a row in the list view
	 */
	private static class ValueHolder{
		SmartImageView photo;
		TextView name;
	}
}
