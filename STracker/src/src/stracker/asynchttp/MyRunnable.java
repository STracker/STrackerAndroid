package src.stracker.asynchttp;

public interface MyRunnable extends Runnable {
	<T> void runWithArgument(T response);
}
