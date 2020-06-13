package dpusha.app.com.usha.orders_home.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Window;



import java.util.Objects;

public class ViewDialog {

    Context activity;
    Dialog dialog;

    public ViewDialog(Context activity) {
        this.activity = activity;
    }

    public void showDialog() {

        try {
            if (dialog == null) {
                dialog  = new Dialog(activity);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                dialog.setCancelable(false);

                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
               // dialog.setContentView(R.layout.custom_loading_layout);
              //  AVLoadingIndicatorView gifImageView = dialog.findViewById(R.id.avi);
              //  gifImageView.smoothToShow();
            }

            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //..also create a method which will hide the dialog when some work is done
    public void hideDialog(){
        try {
            if (!((Activity)activity).isFinishing()  && dialog != null && dialog.isShowing()) {
                dialog.dismiss();
                dialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
