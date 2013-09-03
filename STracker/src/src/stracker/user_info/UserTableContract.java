package src.stracker.user_info;

import android.net.Uri;
import android.provider.BaseColumns; 

public class UserTableContract {

	//Table name
	public static final String USER_TABLE = "USER";

	//Provider Content URI
	public static final Uri USER_TABLE_URI = Uri.withAppendedPath(UserInfoProvider.CONTENT_URI, USER_TABLE);

	//Columns Names
	public static final String _ID      = BaseColumns._ID,
			 				   USER     = "USER",
			                   CALENDAR = "CALENDAR";

}
