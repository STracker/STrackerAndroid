/**
 * 
 */
package src.stracker.asynchttp;

import src.stracker.EpisodeActivity;
import src.stracker.json.EpisodeSerializer;
import src.stracker.json.JSONLocator;
import src.stracker.model.Episode;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @author diogomatos
 * This implementation represents a request to an episode of a tv show
 */
public class EpisodeRequest extends AbstractAsyncHttp {

	private EpisodeSerializer _serializer;
	
	/**
	 * @param context
	 */
	public EpisodeRequest(Context context) {
		super(context);
		_serializer = (EpisodeSerializer) JSONLocator.getInstance().getSerializer(Episode.class);
	}

	/** 
	 * (non-Javadoc)
	 * @see src.stracker.asynchttp.AbstractAsyncHttp#onSuccessHook(java.lang.String)
	 */
	@Override
	protected void onSuccessHook(String response) {
		try{
			Episode episode = _serializer.deserialize(response);
			Intent intent = new Intent(_context,EpisodeActivity.class);
			intent.putExtra("episode", episode);
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