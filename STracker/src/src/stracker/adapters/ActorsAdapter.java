package src.stracker.adapters;

import java.util.List;
import src.stracker.R;
import src.stracker.model.Actor;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

/** 
 * This class represents the adapter to a list of Actors
 */
public class ActorsAdapter extends ArrayAdapter<Actor>{

	private LayoutInflater _inflater;
	
	/**
	 * @param context - Represents the context of an Activity
	 * @param elems - Represents a list of Actors
	 */
	public ActorsAdapter(Context context, List<Actor> elems){
		super(context, R.layout.actor_row, elems);
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
            vi = _inflater.inflate(R.layout.actor_row, null);
            holder.photo = (SmartImageView) vi.findViewById(R.id.actor_photo); 
            holder.name =  (TextView)  vi.findViewById(R.id.actor_name);
            holder.characterName = (TextView) vi.findViewById(R.id.char_name);
            vi.setTag(holder);
        }
        else
        	holder = (ValueHolder) vi.getTag();
        	        
        Actor actor = getItem(position);	        
        // Setting all values in listview
        holder.name.setText(actor.getName());
        holder.characterName.setText(actor.getCharacterName());
        holder.photo.setImageUrl(actor.getPhotoUrl());
        return vi;
	}	
	
	/**
	 * This class is used to save the properties of a row in the list view
	 */
	private static class ValueHolder{
		SmartImageView photo;
		TextView name;
		TextView characterName;
	}
}