package dpusha.app.com.usha.orders_home.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import java.util.Objects;

public class ViewDialogProgress {

    Context activity;
    ProgressHUD dialog;

    public ViewDialogProgress(Context activity) {
        this.activity = activity;
    }

    public void showDialog() {

        try {
            if (dialog == null) {
                try {
                    dialog = ProgressHUD.show(activity, "", false);
                } catch (Exception e) {
                    e.getMessage();
                }
            }

            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //..also create a method which will hide the dialog when some work is done
    public void hideDialog(){
        try {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

}
