package src.stracker.asynchttp;

import java.util.ArrayList;
import src.stracker.json.GenresSerializer;
import src.stracker.json.JSONLocator;
import src.stracker.model.GenreSynopse;
import android.content.Context;

/**
 * @author diogomatos
 * This implementation represents a request to a list of genres
 */
public class GenresRequest extends AbstractAsyncHttp {

	private GenresSerializer _serializer;
	
	/**
	 * The constructor of the genres request 
	 * @param context - activity context
	 * @param runnable - callback to be invoked
	 */
	public GenresRequest(Context context, MyRunnable runnable) {
		super(context, runnable);
		_serializer = (GenresSerializer) JSONLocator.getInstance().getSerializer(GenreSynopse.class);
	}

	/**
	 * @see src.stracker.asynchttp.AbstractAsyncHttp#onSuccessHook(java.lang.String)
	 * @param response - string with the Http Response 
	 */
	@Override
	protected void onSuccessHook(String response) {
		ArrayList<GenreSynopse> list = _serializer.deserialize(response);
		_runnable.runWithArgument(list);
	}
}
