package src.stracker;

import src.stracker.tasks.TvShowRequestTask;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.content.Context;

public class MainActivity extends Activity {
	
	private Button btnGetInfo;
	private String URL = "http://strackerserverdev.apphb.com/api/tvshows/tt1520211";
	private Context _context = (Context) this;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	         
		btnGetInfo = (Button) findViewById(R.id.btn_start);
		
		btnGetInfo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			   	btnGetInfo.setEnabled(false);
			   	new TvShowRequestTask(_context).execute(URL);
			}
		});
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
