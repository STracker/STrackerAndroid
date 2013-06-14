/**
 * 
 */
package src.stracker.asynchttp;

import java.util.ArrayList;
import src.stracker.ResultActivity;
import src.stracker.json.EpisodeSynopseSerializer;
import src.stracker.json.JSONLocator;
import src.stracker.model.EpisodeSynopse;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @author diogomatos
 * This implementation represents a request to a list of episodes of a season from a tv show
 */
public class EpisodesRequest extends AbstractAsyncHttp {

	private EpisodeSynopseSerializer _serializer;
	private int _seasonNumber;
	
	/**
	 * @param context
	 */
	public EpisodesRequest(Context context, int seasonNumber) {
		super(context);
		_serializer = (EpisodeSynopseSerializer) JSONLocator.getInstance().getSerializer(EpisodeSynopse.class);
		_seasonNumber = seasonNumber;
	}

	/** 
	 * (non-Javadoc)
	 * @see src.stracker.asynchttp.AbstractAsyncHttp#onSuccessHook(java.lang.String)
	 */
	@Override
	protected void onSuccessHook(String response) {
		try{
			ArrayList<EpisodeSynopse> list = _serializer.deserialize(response);
			Intent intent = new Intent(_context,ResultActivity.class);
			intent.putExtra("type", "EPISODESYNOPSE");
			intent.putExtra("list", list);
			intent.putExtra("seasonNumber", _seasonNumber);
			_context.startActivity(intent);
		}catch(Exception e){
			onErrorHook(e,e.getClass().getSimpleName());
		}
	}

	/** 
	 * (non-Javadoc)
	 * @see src.stracker.asynchttp.AbstractAsyncHttp#onErrorHook(java.lang.Throwable, java.lang.String)
	 */
	@Override
	protected void onErrorHook(Throwable e, String response) {
		Toast.makeText(_context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();  
	}
}