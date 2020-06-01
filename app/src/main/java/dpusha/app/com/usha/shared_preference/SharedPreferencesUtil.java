package dpusha.app.com.usha.shared_preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import dpusha.app.com.usha.MyUtils;

public class SharedPreferencesUtil {

    public static void setAuthToken(Context context, String token) {
        try {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("token",token);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String getAuthToken(Context context) {
        String val = "";
        try {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            val = preferences.getString("token", "");
            return val;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void setUserId(Context context, String id) {
        try {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("user_id",id);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String getUserId(Context context) {
        String val = "";
        try {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            val = preferences.getString("user_id", "");
            return val;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    public static void setPassword(Context context, String password) {
        try {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("password",password);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String getPassword(Context context) {
        String val = "";
        try {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            val = preferences.getString("password", "");
            return val;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean clearPrefernce(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
       boolean res = editor.clear().commit();


        return res;
    }
    public static boolean clearCartItems(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        boolean res =   editor.remove("CartItems").commit();; // will delete key key_name4

        return res;
    }

    public static void setCartItems(Context context, String jsonCartItem) {
        try {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("CartItems",jsonCartItem);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String getCartItems(Context context) {
        String val = "";
        try {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            val = preferences.getString("CartItems", "");
            return val;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


}
