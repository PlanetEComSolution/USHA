package dpusha.app.com.usha;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.inputmethod.InputMethodManager;

public class util {

    Context context;

    public static void onKeyBoardDown(Context con) {
        try {
            InputMethodManager inputManager = (InputMethodManager) con.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputManager.isAcceptingText()) {
                if (inputManager.isActive())
                    inputManager.hideSoftInputFromWindow(((Activity) con).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static void showAlert(Context context, String msg) {
        new androidx.appcompat.app.AlertDialog.Builder(context).setTitle(context.getResources().getString(R.string.app_name))
                .setMessage(msg)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }


}













