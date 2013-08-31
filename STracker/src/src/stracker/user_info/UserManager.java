package src.stracker.user_info;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import src.stracker.FbLoginActivity;
import src.stracker.model.Subscription;
import src.stracker.model.Suggestion;
import src.stracker.model.User;
import src.stracker.model.UserSynopse;

public class UserManager {
	
	private User _user;
	
	@SuppressWarnings("unchecked")
	public User getUser(Context context){
		//verify in memory if exists
		if(_user != null) return _user;
		//verify db. In this table is always only one user
		Cursor cursor = context.getContentResolver().query(UserTableContract.USER_TABLE_URI, null, null, null, null);
		if(cursor.moveToNext()){
			_user = new User(cursor.getString(cursor.getColumnIndex(UserTableContract.USER_ID)), 
					         cursor.getString(cursor.getColumnIndex(UserTableContract.NAME)),
					         cursor.getString(cursor.getColumnIndex(UserTableContract.EMAIL)),
					         (ArrayList<Subscription>) deserialize(cursor.getBlob(cursor.getColumnIndex(UserTableContract.SUBSCRIPTIONS))),
					         (ArrayList<UserSynopse>)  deserialize(cursor.getBlob(cursor.getColumnIndex(UserTableContract.FRIENDS))),
					         (ArrayList<UserSynopse>)  deserialize(cursor.getBlob(cursor.getColumnIndex(UserTableContract.FRIEND_REQUESTS))),
					         (ArrayList<Suggestion>)   deserialize(cursor.getBlob(cursor.getColumnIndex(UserTableContract.SUGGESTIONS))));
			return _user;
		}
		//if there's no user in db then login 
		context.startActivity(new Intent(context, FbLoginActivity.class));
		return null;
	}
	
	public void syncUser(Context context){
		//TODO
	}
	
	public void updateUser(Context context, User user){
		
	}
	
	public void deleteUser(Context context){
		//TODO: logout
	}
	
	private static Object deserialize(byte[] data) {
	    try{
			ByteArrayInputStream in = new ByteArrayInputStream(data);
		    ObjectInputStream is = new ObjectInputStream(in);
		    return is.readObject();
	    } catch (Exception e){
	    	Log.d("BLOB: deserialize", e.getMessage());
	    	return null;
	    }
	}
	
	public static byte[] serialize(Object obj) {
	    try{
			ByteArrayOutputStream out = new ByteArrayOutputStream();
		    ObjectOutputStream os = new ObjectOutputStream(out);
		    os.writeObject(obj);
		    return out.toByteArray();
		} catch (Exception e){
			Log.d("BLOB: serialize", e.getMessage());
	    	return null;
	    }
	}
}
