package src.stracker.asynchttp;
 
/**
 * @author diogomatos
 * This interface is used to define a new implementation of a runnable.
 */
public interface MyRunnable extends Runnable {
	/**
	 * This method is used to create a callback with a generic parameter
	 * @param response - HTTP response after serialization
	 */
	<T> void runWithArgument(T response);
}
