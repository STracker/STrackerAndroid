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
	 * @param context - activity where the information is needed
	 * @return information
	 */
	public E get(Context context);
	
	/**
	 * Synchronize information from the server
	 * @param context - activity where the information is needed
	 */
	public void sync(Context context);
	
	/**
	 * Update information changed by the user
	 * @param elem - new information
	 */
	public void update(E elem);
	
	/**
	 * Delete information
	 * @param context - activity where the information is needed
	 */
	public void delete();
	
	/**
	 * Save information in memory
	 * @param elem - information to save
	 */
	public void savePersistently(E elem);
}
