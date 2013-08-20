/**
 * Third-party code
 * Source: https://gist.github.com/alecplumb/1302404
 */
package src.stracker.utils;

import roboguice.activity.event.OnPauseEvent;
import roboguice.activity.event.OnResumeEvent;
import roboguice.event.EventManager;
import roboguice.event.Observes;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.google.inject.Inject;
 
/**
 * A shake detector that can be added to any {@link roboguice.activity.RoboActivity RoboActivity}.
 * <br/>
 * 
 * <h1>Usage:</h1>
 * <pre>
 * class MyActivity extends RoboActivity {
 *     &#64;Inject ShakeDetector shakeDetector;
 *
 *     ...
 *
 *     public void handleShake(&#64;Observes {@link ShakeDetector.OnShakeEvent} event) {
 *         Toast.makeText(this, "Shake!", Toast.LENGTH_SHORT).show();
 *     }
 *
 *     public void handleShakeNotSupported(&#64;Observes {@link ShakeDetector.OnShakeNotSupportedEvent} event) {
 *         Toast.makeText(this, "Shake not supported!", Toast.LENGTH_SHORT).show();
 *     }
 * }</pre>
 *
 * Derived from <a href="http://android.hlidskialf.com/blog/code/android-shake-detection-listener">
 * Android Shake Detection Listener</a> by "Wiggins".
 * <br/> <br/>
 *
 * @author <a href="mailto:alec@etherwalker.com">Alec B. Plumb</a>
 */
public class ShakeDetector implements SensorEventListener {
 
	private static final int FORCE_THRESHOLD = 350;
	private static final int TIME_THRESHOLD = 100;
	private static final int SHAKE_TIMEOUT = 500;
	private static final int SHAKE_DURATION = 1000;
	private static final int SHAKE_COUNT = 3;
 
	private SensorManager mSensorManager;
	private EventManager mEventManager;
	private Sensor mAccelerometer;
 
	private float mLastX = -1.0f, mLastY = -1.0f, mLastZ = -1.0f;
	private long mLastTime;
	private int mShakeCount = 0;
	private long mLastShake;
	private long mLastForce;
	private boolean mShakeNotSupported = false;
	
	@Inject
	public ShakeDetector(SensorManager sensorManager, EventManager eventManager) {
		super();
		this.mSensorManager = sensorManager;
		this.mEventManager = eventManager;
		if(sensorManager != null) {
			mAccelerometer = sensorManager.getDefaultSensor(SensorManager.SENSOR_ACCELEROMETER);
		}
	}
 
	public void handleResume( @Observes OnResumeEvent event ) {
		if(mShakeNotSupported) {
			return;
		}
		if(mSensorManager == null) {
			doShakeNotSupported();
			return;
		}
		boolean supported = mSensorManager.registerListener(this,
				mAccelerometer,
				SensorManager.SENSOR_DELAY_GAME);
		if (!supported) {
			mSensorManager.unregisterListener(this);
			doShakeNotSupported();
		}
	}
 
	public void handlePause( @Observes OnPauseEvent event) {
		if(!mShakeNotSupported) {
			mSensorManager.unregisterListener(this);
		}
	}
 
	@Override
	public void onSensorChanged(SensorEvent event) {
		long now = System.currentTimeMillis();
		final float[] values = event.values;
 
		if ((now - mLastForce) > SHAKE_TIMEOUT) {
			mShakeCount = 0;
		}
 
		if ((now - mLastTime) > TIME_THRESHOLD) {
			long diff = now - mLastTime;
			float speed = Math.abs(values[SensorManager.DATA_X]
					+ values[SensorManager.DATA_Y]
					+ values[SensorManager.DATA_Z] - mLastX - mLastY - mLastZ)
					/ diff * 10000;
			if (speed > FORCE_THRESHOLD) {
				if ((++mShakeCount >= SHAKE_COUNT)
						&& (now - mLastShake > SHAKE_DURATION)) {
					mLastShake = now;
					mShakeCount = 0;
					doShake();
				}
				mLastForce = now;
			}
			mLastTime = now;
			mLastX = values[SensorManager.DATA_X];
			mLastY = values[SensorManager.DATA_Y];
			mLastZ = values[SensorManager.DATA_Z];
		}
	}
 
	public void doShake() {
		if(mEventManager != null) {
			mEventManager.fire(new OnShakeEvent());
		}
	}
	
	public void doShakeNotSupported() {
		if(!mShakeNotSupported) {
			// only fire the event once. After that we already know it
			// is not supported.
			mShakeNotSupported = true;
			if(mEventManager != null) {
				mEventManager.fire(new OnShakeNotSupportedEvent());
			}
		}
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// don't care
	}
 
	/**
	 * Implement a method that @{@link Observes} this class to be called
	 * when the {@link ShakeDetector} detects a shake.
	 *
	 */
	public static class OnShakeEvent{}
 
	/**
	 * Implement a method that @{@link Observes} this class to be called
	 * if shake detection is not supported (e.g., the device does not
	 * have an accelerometer). The event will be fired while handling an
	 * {@link OnResumeEvent} and will only be fired once per instance
	 * of the ShakeDetector.
	 */
	public static class OnShakeNotSupportedEvent{}
 
}
