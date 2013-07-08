package src.stracker.asynchttp;

import src.stracker.EpisodeActivity;
import src.stracker.json.JSONLocator;
import src.stracker.json.RatingsSerializer;
import src.stracker.model.Ratings;
import android.content.Context;
import android.widget.Toast;

public class EpisodeRatingRequest extends AbstractAsyncHttp {

	private RatingsSerializer _serializer;
	
	public EpisodeRatingRequest(Context context) {
		super(context);
		_serializer = (RatingsSerializer) JSONLocator.getInstance().getSerializer(Ratings.class);
	}

	@Override
	protected void onSuccessHook(String response) {
		Ratings rating = _serializer.deserialize(response);
		((EpisodeActivity) _context).onRatingSuccess(rating);
	}

	@Override
	protected void onErrorHook(Throwable e, String response) {
		Toast.makeText(_context, "No results found", Toast.LENGTH_SHORT).show();
	}
}
