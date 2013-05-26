package src.stracker;

import roboguice.activity.RoboListActivity;
import roboguice.inject.ContentView;
import src.stracker.tasks.SearchByNameRequest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
  
@ContentView(R.layout.activity_main)
public class MainActivity extends RoboListActivity {
    
	private STrackerApp _app;
	private Context _context;
        
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		_app = (STrackerApp) getApplication();
		_context = (this);
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
    		initSearchByName();
    		break;
    	}
    	return true;
    }
    
    private void initSearchByName(){
    	AlertDialog.Builder adBuilder = new AlertDialog.Builder(this);
        adBuilder.setMessage("Enter TvShow's Name:");
        adBuilder.setCancelable(true);
        // Set an EditText view to get user input 
        final EditText input = new EditText(this);
        adBuilder.setView(input);
        adBuilder.setPositiveButton("Cancel",
                new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int id) {
	                    dialog.cancel();
	                }
        });
        adBuilder.setNegativeButton("Search",
        		new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int id) {
	                	String url = _app.getURL()+"tvshows?name="+input.getText();
	                    new SearchByNameRequest(_context).execute(url.replaceAll(" ", "+"));
	                }
        });
        AlertDialog alertDialog = adBuilder.create();
        alertDialog.show();
    }
}