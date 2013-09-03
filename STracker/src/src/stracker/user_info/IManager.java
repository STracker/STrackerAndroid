package src.stracker.user_info;

import android.content.Context;

/**
 * Interface to information managers.
 * @author diogomatos
 * @param <E> - Type of information to save
 */
public interface IManager<E> {
	
	/**
	 * Get information
	 * @param context - context of the activity where manager is needed
	 * @return information
	 */
	public E get(Context context);
	
	/**
	 * Synchronize information from the server
	 * @param runnable - action to do after the synchronization
	 */
	public void sync(Runnable runnable);
	
	/**
	 * Update information changed by the user
	 * @param elem - new information
	 */
	public void update(E elem);
	
	/**
	 * Delete information
	 */
	public void delete();
	
	/**
	 * Save information in memory
	 * @param elem - information to save
	 */
	public void savePersistently(E elem);
}
