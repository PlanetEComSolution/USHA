package dpusha.app.com.usha.activity;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;



import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;

import dpusha.app.com.usha.listeners.AppResumeListener;
import dpusha.app.com.usha.orders_home.util.AppVisibilityDetector;
import io.fabric.sdk.android.Fabric;

//import com.danikula.videocache.HttpProxyCacheServer;

/**
 * Created by Jitendra on 22,March,2019
 */
public class USHAApplication extends Application {

    private static USHAApplication mInstance = null;
    private static AppResumeListener resumeEventReceiverListener;
  //  private HttpProxyCacheServer proxy;
 // private SharedPreferencesUtil mPreferencesRotana;
    @Override
    public void onCreate() {

   //     mPreferencesRotana = new SharedPreferencesUtil(this, null);

//        fabricInitialization();

        mInstance = this;



        checkForApplicationLevelEvents();
        super.onCreate();
    }

    private void fabricInitialization() {
        try {

            boolean isDebuggable =  ( 0 != ( getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE ) );
            // Set up Crashlytics, disabled for debug builds
            Crashlytics crashlyticsKit = new Crashlytics.Builder()
                    .core(new CrashlyticsCore.Builder().disabled(isDebuggable).build())
                    .build();
            // Initialize Fabric with the debug-disabled crashlytics.
//            Fabric.with(this, crashlyticsKit, new Crashlytics());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static USHAApplication get() {
        return mInstance;
    }

    public static Context getContext() {
        return mInstance;
    }
    public void setAppResumeListener(AppResumeListener listener) {
        resumeEventReceiverListener = listener;
    }

    private void checkForApplicationLevelEvents() {
        AppVisibilityDetector.init(mInstance, new AppVisibilityDetector.AppVisibilityCallback() {
            @Override
            public void onAppGotoForeground() {
                //app is from background to foreground
           //     mPreferencesRotana.putBooleanValue(Constants.APP_IN_FOREGROUND, true);
                Log.e("CHECK", "background to foreground: ");
                if (resumeEventReceiverListener != null) {
                    resumeEventReceiverListener.onAppResume();
                }
            }

            @Override
            public void onAppGotoBackground() {
                //app is from foreground to background
            //    mPreferencesRotana.putBooleanValue(Constants.APP_IN_FOREGROUND, false);
                Log.e("CHECK", "foreground to background :");
            }
        });
    }



}
