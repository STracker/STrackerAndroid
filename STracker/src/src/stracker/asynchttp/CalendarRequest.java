package src.stracker.asynchttp;

import java.util.ArrayList;

import src.stracker.CalendarActivity;
import src.stracker.json.CalendarSerializer;
import src.stracker.model.EpisodeSynopse;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CalendarRequest extends AbstractAsyncHttp {

	private CalendarSerializer _serializer;
	
	/**
	 * @param context
	 */
	public CalendarRequest(Context context) {
		super(context);
		_serializer = new CalendarSerializer();
	}
	
	@Override
	protected void onSuccessHook(String response) {
		try{
			ArrayList<EpisodeSynopse> list = _serializer.deserialize(response);
			Intent intent = new Intent(_context,CalendarActivity.class);
			intent.putExtra("list", list);
			_context.startActivity(intent);
		}catch(Exception e){
			onErrorHook(e,e.getClass().getSimpleName());
		}
	}

	@Override
	protected void onErrorHook(Throwable e, String response) {
		Toast.makeText(_context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();  
	}
}
