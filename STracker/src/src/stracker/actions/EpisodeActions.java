package src.stracker.actions;

import src.stracker.BaseActivity;
import src.stracker.R;
import src.stracker.STrackerApp;
import src.stracker.asynchttp.EpisodeRequests;
import src.stracker.asynchttp.MyRunnable;
import src.stracker.model.Episode;
import src.stracker.model.EpisodeSynopse;
import src.stracker.model.Subscription;
import src.stracker.model.User;
import src.stracker.user_info.UserManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

/**
 * @author diogomatos
 * This class aggregate the actions associated with an episode.
 */
public class EpisodeActions {

	/**
	 * This method pop's up an AlertDialog to ask for clear watched in an episode.
	 * @param activity - Activity where the method is called.
	 * @param episode - episode to unwatch
	 */
	public static void unwatchEpisode(final BaseActivity activity, final Episode episode){
		AlertDialog.Builder adBuilder = new AlertDialog.Builder(activity);
		adBuilder.setMessage(activity.getString(R.string.error_ew_already_watched));
		adBuilder.setCancelable(true);
		adBuilder.setNegativeButton(activity.getString(R.string.button_cancel),
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		}); 
		adBuilder.setPositiveButton(activity.getString(R.string.button_remove),
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				EpisodeRequests.deleteEpisodeWatched(activity, new MyRunnable() {
					@Override
					public void run() {
						Toast.makeText(activity, R.string.error_ew_remove, Toast.LENGTH_SHORT).show();
					}
					@Override
					public <T> void runWithArgument(T response) {
						Toast.makeText(activity, R.string.success_ew_remove, Toast.LENGTH_SHORT).show();
						removeEpisodeFromUser(activity, episode);
					}
				}, episode);
			}
		});
		AlertDialog alertDialog = adBuilder.create();
		alertDialog.show();
	}
	
	/**
	 * Auxiliary method to remove watched episode from user profile
	 * @param activity - Activity where the method is called.
	 * @param episode - episode to remove
	 */
	private static void removeEpisodeFromUser(BaseActivity activity, Episode episode){
		UserManager manager = ((STrackerApp) activity.getApplication()).getUserManager();
		User user = manager.get(activity);
		for(Subscription subs : user.getSubscriptions()){
			if(subs.getTvShowSynope().getId().equals(episode.getTvShowId())){
				subs.removeEpisode(episode.generateSynopse(activity.getString(R.string.uri_episode)));
				manager.update(user);
			}
		}
	}
	
	/**
	 * Auxiliary method to add watched episode to user profile
	 * @param activity - Activity where the method is called.
	 * @param episode - episode to add
	 */
	private static void AddEpisodeToUser(BaseActivity activity, Episode episode){
		UserManager manager = ((STrackerApp) activity.getApplication()).getUserManager();
		User user = manager.get(activity);
		for(Subscription subs : user.getSubscriptions()){
			if(subs.getTvShowSynope().getId().equals(episode.getTvShowId())){
				subs.addEpisode(episode.generateSynopse(activity.getString(R.string.uri_episode)));
				manager.update(user);
			}
		}
	}
	
	/**
	 * This method pop's up an AlertDialog to ask for watch an episode.
	 * @param activity - Activity where the method is called.
	 * @param episode - episode to watch
	 */
	public static void watchEpisode(final BaseActivity activity, final Episode episode){
		AlertDialog.Builder adBuilder = new AlertDialog.Builder(activity);
		adBuilder.setMessage(activity.getString(R.string.info_ew_watch));
		adBuilder.setCancelable(true);
		adBuilder.setNegativeButton(activity.getString(R.string.button_cancel),
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		}); 
		adBuilder.setPositiveButton(activity.getString(R.string.button_watch),
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				EpisodeRequests.postEpisodeWatched(activity, new MyRunnable() {
					@Override
					public void run() {
						Toast.makeText(activity, R.string.error_ew_watch, Toast.LENGTH_SHORT).show();
					}
					@Override
					public <T> void runWithArgument(T response) {
						Toast.makeText(activity, R.string.success_ew_episode, Toast.LENGTH_SHORT).show();
						AddEpisodeToUser(activity, episode);
					}
				}, episode);
			}
		});
		AlertDialog alertDialog = adBuilder.create();
		alertDialog.show();
	}
	
	/**
	 * This method construct a string title with episode information
	 * @param episode - episode synopse
	 * @return string - episode title
	 */
	public static String buildEpisodePrefix(EpisodeSynopse episode){
		return "S"+
			   ((episode.getSeasonNumber() < 10) ? "0" : "") + episode.getSeasonNumber() + 
			   "E" +
			   ((episode.getNumber() < 10) ? "0" : "") + episode.getNumber();
	}
}
