package src.stracker;

import src.stracker.asynchttp.MyRunnable;
import src.stracker.asynchttp.TvShowRequests;
import src.stracker.model.UserSynopse;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

/**
 * @author diogomatos
 * This activity represents the suggestions of a friend
 */
public class MyFriendSuggestActivity extends MyFriendsActivity {
	/**
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> adapt, View view, int position, long id) {
		UserSynopse user = _users.get(position);
		String tvShowId = getIntent().getStringExtra(TVSHOW_ID_PARAM);
		TvShowRequests.postSuggestion(this, new MyRunnable() {
			@Override
			public void run() {
				Toast.makeText(MyFriendSuggestActivity.this, R.string.error_suggestion, Toast.LENGTH_SHORT).show();
			}
			@Override
			public <T> void runWithArgument(T response) {
				Toast.makeText(MyFriendSuggestActivity.this, R.string.success_suggestion, Toast.LENGTH_SHORT).show();
			}
		}, tvShowId, user.getId());
	}
}
