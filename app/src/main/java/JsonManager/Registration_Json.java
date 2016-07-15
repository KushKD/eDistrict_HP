package JsonManager;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kuush on 6/16/2016.
 */
public class Registration_Json {

    public static String RegisterME_Error(String s) {

        String g_Table = null;
        try {
            JSONObject obj = new JSONObject(s);
            g_Table = obj.optString("message");
            return g_Table;

        } catch (JSONException e) {
            e.printStackTrace();
            return g_Table = "Something went wrong.";
        }
    }

    public static String Update_User_Message(String s) {

        String g_Table = null;
        try {
            JSONObject obj = new JSONObject(s);
            g_Table = obj.optString("message");
            return g_Table;

        } catch (JSONException e) {
            e.printStackTrace();
            return g_Table = "Something went wrong.";
        }
    }



}
