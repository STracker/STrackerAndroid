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
 *
 */
public class SearchByNameRequest extends AbstractAsyncHttp {

	private TvShowSerializer _serializer;
	
	/**
	 * @param context
	 */
	public SearchByNameRequest(Context context) {
		super(context);
		_serializer = (TvShowSerializer) JSONLocator.getInstance().getSerializer(TvShow.class);
	}

	/* (non-Javadoc)
	 * @see src.stracker.asynchttp.AbstractAsyncHttp#onSuccessHook(java.lang.String)
	 */
	@Override
	protected void onSuccessHook(String response) {
		TvShow tvshow = _serializer.deserialize(response);
		Intent intent = new Intent(_context,TvShowActivity.class);
		intent.putExtra("tvshow", tvshow);
		_context.startActivity(intent);
	}

	/* (non-Javadoc)
	 * @see src.stracker.asynchttp.AbstractAsyncHttp#onErrorHook(java.lang.Throwable, java.lang.String)
	 */
	@Override
	protected void onErrorHook(Throwable e, String response) {
		Toast.makeText(_context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show(); 
	}
}