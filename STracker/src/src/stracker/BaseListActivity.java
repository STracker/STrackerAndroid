package src.stracker;

import android.os.Bundle;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import roboguice.inject.InjectView;

public abstract class BaseListActivity extends BaseActivity implements OnItemClickListener{
	protected @InjectView(android.R.id.list) ListView _listView;
	
	@Override
	public void onCreate(Bundle state){
		super.onCreate(state);
		_listView.setOnItemClickListener(this);
	}
}
