package src.stracker.tasks;

import java.util.ArrayList;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import src.stracker.ResultActivity;
import src.stracker.json.JSONLocator;
import src.stracker.json.TvShowSynopseSerializer;
import src.stracker.model.TvShowSynopse;

public class SearchByGenreRequest extends AbstractBaseTask<TvShowSynopse> {

	private TvShowSynopseSerializer _serializer;
	
	public SearchByGenreRequest(Context context) {
		super(context);
		_serializer = (TvShowSynopseSerializer) JSONLocator.getInstance().getSerializer(TvShowSynopse.class);
	}
	
	@Override
	public void onResult(String result) {
		try{
			ArrayList<TvShowSynopse> list = _serializer.deserialize(result);
			Intent intent = new Intent(context,ResultActivity.class);
			intent.putExtra("list", list);
			context.startActivity(intent);
		}catch(Exception e){
			onError(e.getClass().getSimpleName());
		}
	}

	@Override
	public void onError(String error) {
		Toast.makeText(context, error, Toast.LENGTH_SHORT).show(); 
	}
}
