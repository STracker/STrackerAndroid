package src.stracker.user_info;

import android.net.Uri;
import android.provider.BaseColumns; 

public class UserTableContract {

	//Table name
	public static final String USER_TABLE = "USER";
	
	//Provider Content URI
	public static final Uri USER_TABLE_URI = Uri.withAppendedPath(UserInfoProvider.CONTENT_URI, USER_TABLE);
	
	//Columns Names
	public static final String _ID             = BaseColumns._ID,
			 				   USER_ID         = "USER_ID",
			                   NAME            = "NAME",
			                   PHOTO_URL       = "PHOTO_URL",
			                   EMAIL           = "EMAIL",
			                   VERSION         = "VERSION",
			                   SUBSCRIPTIONS   = "SUBSCRIPTIONS",
			                   SUGGESTIONS     = "SUGGESTIONS",
			                   FRIENDS         = "FRIENDS",
			                   FRIEND_REQUESTS = "FRIEND_REQUESTS";
			
}
