package src.stracker;

import java.util.Arrays;
import java.util.HashMap;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

import roboguice.inject.ContentView;
import src.stracker.asynchttp.DummyRequest;
import src.stracker.asynchttp.MyRunnable;
import src.stracker.model.User;

/**
 * The login activity.
 * @author diogomatos
 */
@ContentView(R.layout.activity_login)
public class FbLoginActivity extends BaseActivity {
	
	private UiLifecycleHelper uiHelper;
	private ProgressDialog _dialog;
	LoginButton _loginBtn;
	
	private Session.StatusCallback callback = new Session.StatusCallback() {
	    @Override
	    public void call(Session session, SessionState state, Exception exception) {
	        onSessionStateChange(session, state, exception);
	    }
	};
	
	/**
	 * @see src.stracker.BaseActivity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    uiHelper = new UiLifecycleHelper(this, callback);
	    uiHelper.onCreate(savedInstanceState);
	    _dialog = new ProgressDialog(this);
	    _loginBtn = (LoginButton) findViewById(R.id.loginBtn);
	    _loginBtn.setReadPermissions(Arrays.asList("basic_info","email"));
	    _loginBtn.setSessionStatusCallback(callback);
	}
	
	/**
	 * This method is a callback to handle the facebook session state
	 * @param session - facebook user session
	 * @param state - new state
	 * @param exception - exception
	 */
	private void onSessionStateChange(Session session, SessionState state, Exception exception) {
	    //if logged in then
		if (state.isOpened()) {
			_dialog.setMessage("logging in...");
			_dialog.show();
			Request.executeMeRequestAsync(session, new Request.GraphUserCallback() {
				@Override
				public void onCompleted(GraphUser user, Response response) {
					if(user != null){
						_application.setFbUser(new User(user.getName(),user.getId(), user.asMap().get("email").toString()));
					}
					_dialog.dismiss();
					HashMap<String, String> params = new HashMap<String, String>();
					params.put("Name", _application.getFbUser().getName());
					params.put("Email", _application.getFbUser().getEmail());
					params.put("Photo", _application.getFbUser().getPhotoUrl());
					new DummyRequest(FbLoginActivity.this, new MyRunnable() {
						@Override
						public void run() {
							Toast.makeText(FbLoginActivity.this, R.string.error_login, Toast.LENGTH_SHORT).show();
						}
						@Override
						public <T> void runWithArgument(T response) {
							Toast.makeText(FbLoginActivity.this, R.string.success_login, Toast.LENGTH_SHORT).show();
						}
					}).authorizedPost(getString(R.string.uri_users), params);
					finish();		
				}
			});
	    }
		//if logged out then
		else if (state.isClosed()) {
	    	_application.logout();
	    }
	}

	/**
	 * @see roboguice.activity.RoboActivity#onResume()
	 */
	@Override
	public void onResume() {
	    super.onResume();
	    uiHelper.onResume();
	}

	/**
	 * @see roboguice.activity.RoboActivity#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	/**
	 * @see roboguice.activity.RoboActivity#onPause()
	 */
	@Override
	public void onPause() {
	    super.onPause();
	    uiHelper.onPause();
	}

	/**
	 * @see roboguice.activity.RoboActivity#onDestroy()
	 */
	@Override
	public void onDestroy() {
	    super.onDestroy();
	    uiHelper.onDestroy();
	}

	/**
	 * @see android.app.Activity#onSaveInstanceState(android.os.Bundle)
	 */
	@Override
	public void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    uiHelper.onSaveInstanceState(outState);
	}
}
