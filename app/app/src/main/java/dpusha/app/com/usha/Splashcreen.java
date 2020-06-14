package dpusha.app.com.usha;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import dpusha.app.com.usha.activity.DrawerMainActivity;
import dpusha.app.com.usha.shared_preference.SharedPreferencesUtil;

public class Splashcreen extends AppCompatActivity {
    private static int SPLASH_SCREEN_TIME_OUT = 2000;
    private String TAG = "Splashcreen";
    private String from = "NA";
    public static final String EXTRA_REMINDER = "Reminder";
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        splash();

    }

    private void splash() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               String user_id= SharedPreferencesUtil.getUserId(Splashcreen.this);
               if(user_id.equals("")){
                   Intent mainIntent = new Intent(Splashcreen.this, Login.class);
                   startActivity(mainIntent);
                   finish();
               } else {
                   Intent mainIntent = new Intent(Splashcreen.this, DrawerMainActivity.class);
                   startActivity(mainIntent);
                   finish();
               }



            }


        }, 0);

    }
}

             /*           GetLoginResponse=SharedPreferences.getSignin(context)
                if (logindatamodel != null) {
                    startActivity(Intent(this@SplashActivity, Feed::class.java))
                    finish()
                } else {
                    startActivity(Intent(this@SplashActivity, Dashboard::class.java))
                    finish()*/



                /*SharedPreferences pref = getSharedPreferences("new", MODE_PRIVATE);
                boolean isDeactivated = pref.getBoolean("Deactivate", false);

                SharedPreferences pref1 = getSharedPreferences("new1", MODE_PRIVATE);
                boolean isDeactivated1 = pref1.getBoolean("Deactivate1", false);
                if (isDeactivated1 && from != null && from.equalsIgnoreCase(EXTRA_REMINDER)) {
                    Intent mainIntent = new Intent(Splashcreen.this, Main2Activity.class);
                    startActivity(mainIntent);
                    finish();*/




                    /*
                    String Login_Type = util.Get_Pref(Splash_activity.this, Utility.KEY_LOGIN_TYPE);
                    if ( Login_Type.equals("")) {
                        Intent mainIntent = new Intent(Splash_activity.this, SelectServiceActivity.class);
                        startActivity(mainIntent);
                    }
                    else if (Login_Type.equals(Utility.CPV) ) {
                        Intent mainIntent = new Intent(Splash_activity.this, Sol_ID_Activity.class);
                        startActivity(mainIntent);
                    } else if (Login_Type.equals(Utility.DSBS)) {
                        Intent mainIntent = new Intent(Splash_activity.this, ServiceListActivity.class);
                        startActivity(mainIntent);
                    } else if (Login_Type.equals(Utility.CUSTOMER_MEET)) {
                    }*/


                   /* finish();

                } else {
                    Intent mainIntent = new Intent(Splashcreen.this, Login.class);
                    startActivity(mainIntent);
                    finish();
                }
            }

        }, 2000);
    }
    }
*/