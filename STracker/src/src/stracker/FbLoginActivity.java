package src.stracker;

import java.util.Arrays;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

import src.stracker.asynchttp.DummyRequest;
import src.stracker.model.FBUser;

public class FbLoginActivity extends Activity {
	
	private UiLifecycleHelper uiHelper;
	private STrackerApp _app;
	private ProgressDialog _dialog;
	LoginButton _loginBtn;
	private Context _context = this;
	
	private Session.StatusCallback callback = new Session.StatusCallback() {
	    @Override
	    public void call(Session session, SessionState state, Exception exception) {
	        onSessionStateChange(session, state, exception);
	    }
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_login);
	    uiHelper = new UiLifecycleHelper(this, callback);
	    uiHelper.onCreate(savedInstanceState);
	    _app = (STrackerApp) getApplication();
	    _dialog = new ProgressDialog(this);
	    _loginBtn = (LoginButton) findViewById(R.id.loginBtn);
	    _loginBtn.setReadPermissions(Arrays.asList("basic_info","email"));
	    _loginBtn.setSessionStatusCallback(callback);
	}
	
	private void onSessionStateChange(Session session, SessionState state, Exception exception) {
	    //if logged in then
		if (state.isOpened()) {
			_dialog.setMessage("logging in...");
			_dialog.show();
			Request.executeMeRequestAsync(session, new Request.GraphUserCallback() {
				@Override
				public void onCompleted(GraphUser user, Response response) {
					if(user != null){
						_app.setFbUser(new FBUser(user.getName(),user.getId(), user.asMap().get("email").toString()));
					}
					_dialog.dismiss();
					new DummyRequest(_context).authorizedPost(_app.getApiURL()+"user", _app);
					finish();		
				}
			});
	    }
		//if logged out then
		else if (state.isClosed()) {
	    	_app.logout();
	    }
	}

	@Override
	public void onResume() {
	    super.onResume();
	    uiHelper.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onPause() {
	    super.onPause();
	    uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
	    super.onDestroy();
	    uiHelper.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    uiHelper.onSaveInstanceState(outState);
	}
}