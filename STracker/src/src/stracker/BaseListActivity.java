package src.stracker;

import android.os.Bundle;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import roboguice.inject.InjectView;

/**
 * @author diogomatos
 * This class represents a base list activity where all STracker list activities must extend.
 */
public abstract class BaseListActivity extends BaseActivity implements OnItemClickListener{
	protected @InjectView(android.R.id.list) ListView _listView;
	
	/**
	 * @see src.stracker.BaseActivity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle state){
		super.onCreate(state);
		_listView.setOnItemClickListener(this);
	}
}
