package src.stracker;

import roboguice.activity.RoboListActivity;
import roboguice.inject.ContentView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
  
@ContentView(R.layout.activity_main)
public class MainActivity extends RoboListActivity {
    
      
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);   
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	switch(item.getItemId()){
    	case R.id.action_profile:
    		break;
    	case R.id.action_series:
    		break;
    	case R.id.action_messages:
    		break;
    	case R.id.action_friends:
    		break;
    	case R.id.action_settings:
    		break;
    	case R.id.form_friend:
    		break;
    	case R.id.form_genre:
    		startActivity(new Intent(this,GenreActivity.class));
    		break;
    	case R.id.form_name:
    		break;
    	}
    	return true;
    }
}
