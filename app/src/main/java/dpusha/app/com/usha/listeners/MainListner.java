package dpusha.app.com.usha.listeners;

import android.content.Context;

import androidx.fragment.app.Fragment;

/**
 * Created by Jitendra on 01,April,2019
 */
public interface MainListner {
    void addFragment(Fragment fragment, String tag, boolean flag);
     void refreshCartCount(Context context);
    void clearCart(Context context);
}
