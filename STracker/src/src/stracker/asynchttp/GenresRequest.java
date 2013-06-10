/**
 * 
 */
package src.stracker.asynchttp;

import java.util.ArrayList;

import src.stracker.GenreActivity;
import src.stracker.json.GenresSerializer;
import src.stracker.json.JSONLocator;
import src.stracker.model.GenreSynopse;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @author diogomatos
 *
 */
public class GenresRequest extends AbstractAsyncHttp {

	private GenresSerializer _serializer;
	
	/**
	 * @param context
	 */
	public GenresRequest(Context context) {
		super(context);
		_serializer = (GenresSerializer) JSONLocator.getInstance().getSerializer(GenreSynopse.class);
	}

	/* (non-Javadoc)
	 * @see src.stracker.asynchttp.AbstractAsyncHttp#onSuccessHook(java.lang.String)
	 */
	@Override
	protected void onSuccessHook(String response) {
		try{
			ArrayList<GenreSynopse> list = _serializer.deserialize(response);
			Intent intent = new Intent(_context,GenreActivity.class);
			intent.putExtra("list", list);
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
