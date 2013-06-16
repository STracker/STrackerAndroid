package src.stracker.asynchttp;

import java.util.ArrayList;

import android.content.Context;
import android.widget.Toast;
import src.stracker.MainActivity;
import src.stracker.json.JSONLocator;
import src.stracker.json.TvShowSynopseSerializer;
import src.stracker.model.TvShowSynopse;

public class TopRatedRequest extends AbstractAsyncHttp {

	private TvShowSynopseSerializer _serializer;
	
	/**
	 * @param context
	 */
	public TopRatedRequest(Context context) {
		super(context);
		_serializer = (TvShowSynopseSerializer) JSONLocator.getInstance().getSerializer(TvShowSynopse.class);
	}
	
	@Override
	protected void onSuccessHook(String response) {
		try{
 			ArrayList<TvShowSynopse> list = _serializer.deserialize(response);
			((MainActivity)_context).onTopRatedCompleted(list);
		}catch(Exception e){
			onErrorHook(e,e.getClass().getSimpleName());
		}
	}

	@Override
	protected void onErrorHook(Throwable e, String response) {
		Toast.makeText(_context, "No results found", Toast.LENGTH_SHORT).show(); 
	}
}
