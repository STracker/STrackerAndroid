package src.stracker.tasks;

import src.stracker.TvShowActivity;
import src.stracker.json.JSONLocator;
import src.stracker.json.TvShowSerializer;
import src.stracker.model.TvShow;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class TvShowRequest extends AbstractBaseTask<String> {
	
	private TvShowSerializer _tvShowSerializer;
	
	public TvShowRequest(Context context) {
		super(context);
		_tvShowSerializer = (TvShowSerializer) JSONLocator.getInstance().getSerializer(TvShow.class);
	}
	
	@Override
	public void onResult(String result) {
		final TvShow tvshow = _tvShowSerializer.deserialize(result);
		final Intent intent = new Intent(context,TvShowActivity.class);
		intent.putExtra("tvshow", tvshow);
		context.startActivity(intent);
	}

	@Override
	public void onError(String error) {
		Toast.makeText(context, error, Toast.LENGTH_SHORT).show();  
	}
}
