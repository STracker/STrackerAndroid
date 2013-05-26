package src.stracker.tasks;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import src.stracker.TvShowActivity;
import src.stracker.json.JSONLocator;
import src.stracker.json.TvShowSerializer;
import src.stracker.model.TvShow;

public class SearchByNameRequest extends AbstractBaseTask<TvShow> {

	private TvShowSerializer _serializer;
	
	public SearchByNameRequest(Context context){
		super(context);
		_serializer = (TvShowSerializer) JSONLocator.getInstance().getSerializer(TvShow.class);
	}
	
	@Override
	public void onResult(String result) {
		TvShow tvshow = _serializer.deserialize(result);
		Intent intent = new Intent(context,TvShowActivity.class);
		intent.putExtra("tvshow", tvshow);
		context.startActivity(intent);
	}

	@Override
	public void onError(String error) {
		Toast.makeText(context, error, Toast.LENGTH_SHORT).show(); 
	}
}
