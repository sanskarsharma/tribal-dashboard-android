package sanskar.dev.com.tribaldashboardcg.Utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;

import sanskar.dev.com.tribaldashboardcg.Models.UserModel;
import sanskar.dev.com.tribaldashboardcg.TribalMonitorCG;

/**
 * Created by Sanskar on 10-Dec-17.
 */

public class Utils {

    public static final String KEY_USERID = "key_userid";
    private static final String KEY_PASSWORD = "key_password";
    public static final String KEY_DEPARTMENT = "key_department";
    private static final String KEY_ROLE = "key_role";
    public static final String KEY_NAME = "key_name";

    private static final String KEY_isLogged = "key_islogged";

    private static final String KEY_nullStringforlogout = "----nope----";

    public static void logoutUser(){

        Utils.saveData(KEY_USERID,KEY_nullStringforlogout);
        Utils.saveData(KEY_PASSWORD,KEY_nullStringforlogout);
        Utils.saveData(KEY_DEPARTMENT,KEY_nullStringforlogout);
        Utils.saveData(KEY_ROLE,KEY_nullStringforlogout);
        Utils.saveData(KEY_NAME,KEY_nullStringforlogout);

        Utils.saveData(KEY_isLogged,false);

    }

    public static boolean loginUser(UserModel user){

        Utils.saveData(KEY_USERID,user.getUserid());
        Utils.saveData(KEY_PASSWORD,user.getPassword());
        Utils.saveData(KEY_DEPARTMENT,user.getDepartment());
        Utils.saveData(KEY_ROLE,user.getRole());
        Utils.saveData(KEY_NAME,user.getName());

        Utils.saveData(KEY_isLogged,true);

        return true;

    }

    public static boolean isLoggedIn(){

       return Utils.getData(KEY_isLogged,false);

    }

    public static String getDeviceId(Context context) {
        String deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return deviceId;
    }


    public static void saveData(String id, String value) {
        try {

            SharedPreferences preferences = Utils.getPreferencesInstance(null);
            preferences.edit().putString(id, value).commit();

        } catch (Exception e) {
            Log.i("Exception", "Exception in save data" + e);
            e.printStackTrace();
        }


    }

    public static void saveData(String id, long value) {
        try {

            SharedPreferences preferences = Utils.getPreferencesInstance(null);
            preferences.edit().putLong(id, value).commit();

            Log.i("email", "Save Data : " + id + " : " + value);
        } catch (Exception e) {
            Log.i("Exception", "Exception in save data" + e);
            e.printStackTrace();
        }


    }

    public static void saveData(String id, int value) {
        try {
            SharedPreferences preferences = Utils.getPreferencesInstance(null);

            preferences.edit().putInt(id, value).commit();
        } catch (Exception e) {
            Log.i("Exception", "Exception in save data" + e);
            e.printStackTrace();
        }
    }

    public static void saveData(String id, boolean value) {
        try {
            SharedPreferences preferences = Utils.getPreferencesInstance(null);

            preferences.edit().putBoolean(id, value).commit();
        } catch (Exception e) {
            Log.i("Exception", "Exception in save data" + e);
            e.printStackTrace();
        }
    }

    public static boolean getData(String id, boolean value) {

        if (id != null && id.trim().length() > 0) {
            SharedPreferences preferences = Utils.getPreferencesInstance(null);
            value = preferences.getBoolean(id, value);
        }
        return value;
    }

    public static int getData(String id, int value) {

        if (id != null && id.trim().length() > 0) {
            SharedPreferences preferences = Utils.getPreferencesInstance(null);
            value = preferences.getInt(id, value);
        }
        return value;
    }

    public static long getData(String id, long value) {
        if (id != null && id.trim().length() > 0) {
            SharedPreferences preferences = Utils.getPreferencesInstance(null);
            value = preferences.getLong(id, value);
        }
        return value;
    }

    public static String getData(String id, String value) {
        if (id != null && id.trim().length() > 0) {

            SharedPreferences preferences = Utils.getPreferencesInstance(null);
            value = preferences.getString(id, value);
        }
        Log.i("email", "get data : " + id + " : " + value);
        return value;
    }
    public static SharedPreferences getPreferencesInstance(SharedPreferences preferences) {

        if (preferences == null) {

            return PreferenceManager.getDefaultSharedPreferences(TribalMonitorCG.getContext());
        }

        return preferences;
    }


}
