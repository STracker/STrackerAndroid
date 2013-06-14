package src.stracker.adapters;

import java.util.List;

import src.stracker.R;
import src.stracker.model.Actor;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BinaryHttpResponseHandler;

public class ActorsAdapter extends ArrayAdapter<Actor>{

	private LayoutInflater _inflater;
	
	public ActorsAdapter(Context context, List<Actor> elems){
		super(context, R.layout.actor_row, elems);
		_inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi=convertView;
		ValueHolder holder = new ValueHolder();
        if(convertView==null){
            vi = _inflater.inflate(R.layout.actor_row, null);
            holder.photo = (ImageView) vi.findViewById(R.id.actor_photo); 
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
        showPhoto(holder.photo, actor.getPhotoUrl());
        return vi;
	}	
	
	private void showPhoto(final ImageView photo, String url){
		AsyncHttpClient client = new AsyncHttpClient();
		String[] allowedContentTypes = new String[] { "image/png", "image/jpeg" };
		client.get(url, new BinaryHttpResponseHandler(allowedContentTypes) {
		    @Override
		    public void onSuccess(byte[] fileData) {
		    	if (fileData != null){
		    		Bitmap result = BitmapFactory.decodeByteArray(fileData,0,fileData.length);
		    		photo.setImageBitmap(Bitmap.createScaledBitmap(result, 80, 100, false));
		    	}
		    }
		});
	}
	
	private static class ValueHolder{
		ImageView photo;
		TextView name;
		TextView characterName;
	}
}