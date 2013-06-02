/**
 * 
 */
package src.stracker.asynchttp;

import java.util.ArrayList;
import src.stracker.ResultActivity;
import src.stracker.json.JSONLocator;
import src.stracker.json.SeasonSynopseSerializer;
import src.stracker.model.SeasonSynopse;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @author diogomatos
 *
 */
public class SeasonsRequest extends AbstractAsyncHttp {

	private SeasonSynopseSerializer _serializer;
	private String _tvShowId;
	
	/**
	 * @param context
	 */
	public SeasonsRequest(Context context, String tvShowId) {
		super(context);
		_serializer = (SeasonSynopseSerializer) JSONLocator.getInstance().getSerializer(SeasonSynopse.class);
		_tvShowId = tvShowId;
	}

	/* (non-Javadoc)
	 * @see src.stracker.asynchttp.AbstractAsyncHttp#onSuccessHook(java.lang.String)
	 */
	@Override
	protected void onSuccessHook(String response) {
		try{
			ArrayList<SeasonSynopse> list = _serializer.deserialize(response);
			Intent intent = new Intent(_context,ResultActivity.class);
			intent.putExtra("type", "SEASONSYNOPSE");
			intent.putExtra("list", list);
			intent.putExtra("tvShowId", _tvShowId);
			_context.startActivity(intent);
		}catch(Exception e){
			onErrorHook(e,e.getClass().getSimpleName());
		}
	}

	/* (non-Javadoc)
	 * @see src.stracker.asynchttp.AbstractAsyncHttp#onErrorHook(java.lang.Throwable, java.lang.String)
	 */
	@Override
	protected void onErrorHook(Throwable e, String response) {
		Toast.makeText(_context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();  
	}
}
