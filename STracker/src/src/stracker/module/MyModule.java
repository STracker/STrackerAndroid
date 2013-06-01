package src.stracker.module;

import android.util.Log;
import com.google.inject.AbstractModule;

public class MyModule extends AbstractModule {

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		Log.d("TAG", "bind");
		//bind(MainActivity.class).to(MainActivity.class);
	}
}
