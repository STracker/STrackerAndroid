package src.stracker.adapters;

import java.util.List;

import com.loopj.android.image.SmartImageView;

import src.stracker.R;
import src.stracker.model.UserSynopse;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class UserAdapter extends ArrayAdapter<UserSynopse> {

private LayoutInflater _inflater;
	
	/**
	 * @param context - Represents the context of an Activity
	 * @param elems - Represents a list of Users
	 */
	public UserAdapter(Context context, List<UserSynopse> elems){
		super(context, R.layout.user_row, elems);
		_inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/**
	 * (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		ValueHolder holder = new ValueHolder();
        //If the convertView is not yet constructed, affect the holder and save it in a Tag inside the view
		if(convertView == null){
            vi = _inflater.inflate(R.layout.user_row, null);
            holder.photo = (SmartImageView) vi.findViewById(R.id.user_photo); 
            holder.name =  (TextView)  vi.findViewById(R.id.user_name);
            vi.setTag(holder);
        }
        else 
        	holder = (ValueHolder) vi.getTag();
        	        
        UserSynopse user = getItem(position);	        
        // Setting all values in listview
        holder.name.setText(user.getName());
        holder.photo.setImageUrl("http://graph.facebook.com/"+user.getId()+"/picture?type=small");
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
