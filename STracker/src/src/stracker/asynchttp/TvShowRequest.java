/**
 * 
 */
package src.stracker.asynchttp;

import src.stracker.TvShowActivity;
import src.stracker.json.JSONLocator;
import src.stracker.json.TvShowSerializer;
import src.stracker.model.TvShow;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @author diogomatos
 * This implementation represents a request to a tv show
 */
public class TvShowRequest extends AbstractAsyncHttp {
	
	private TvShowSerializer _tvShowSerializer;
	
	/**
	 * @param context
	 */
	public TvShowRequest(Context context) {
		super(context);
		_tvShowSerializer = (TvShowSerializer) JSONLocator.getInstance().getSerializer(TvShow.class);
	}

	/** 
	 * (non-Javadoc)
	 * @see src.stracker.asynchttp.AbstractAsyncHttp#onSuccess(java.lang.String)
	 */
	@Override
	protected void onSuccessHook(String response) {
		final TvShow tvshow = _tvShowSerializer.deserialize(response);
		final Intent intent = new Intent(_context,TvShowActivity.class);
		intent.putExtra("tvshow", tvshow);
		_context.startActivity(intent);	
	}

	/** 
	 * (non-Javadoc)
	 * @see src.stracker.asynchttp.AbstractAsyncHttp#onError(java.lang.Throwable, java.lang.String)
	 */
	@Override
	protected void onErrorHook(Throwable e, String response) {
		Toast.makeText(_context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();  
	}
}
