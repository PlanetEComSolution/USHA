package dpusha.app.com.usha;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import dpusha.app.com.usha.Interface.ApiInterface;
import dpusha.app.com.usha.Interface.ChangpasswordInterface;
import dpusha.app.com.usha.activity.DrawerMainActivity;
import dpusha.app.com.usha.model.Changpassword;
import dpusha.app.com.usha.model.AuthToken;
import dpusha.app.com.usha.model.LoginResponse;
import dpusha.app.com.usha.network.APIError;
import dpusha.app.com.usha.network.RequestListener;
import dpusha.app.com.usha.network.RetrofitManager;
import dpusha.app.com.usha.orders_home.util.Constants;
import dpusha.app.com.usha.shared_preference.SharedPreferencesUtil;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity implements View.OnClickListener ,BaseSliderView.OnSliderClickListener,ViewPagerEx.OnPageChangeListener, RequestListener {
    public static final String FROM = "EDIT";
    Button btn;
    private Dialog dialog3;
    private Button back;
    private TextView forgot_pass;
    private String user_id, password, status, NewPassword, OldPassword;
    private EditText user_name, pass, user_email;
    private JSONArray jArray;
    private String inValid_username = "", inValid_otp = "", inValid_Email = "";


    private ProgressDialog progressDialog;

    private SliderLayout mDemoSlider;


    private Button login_button;

    private RetrofitManager retrofitManager = RetrofitManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        hitAPIAccessToken();
        login_button = findViewById(R.id.login_button);
        user_name = (EditText) findViewById(R.id.user_id);
        pass = (EditText) findViewById(R.id.user_pass);
        forgot_pass = findViewById(R.id.forg_pass);


        login_button.setOnClickListener(this);
        forgot_pass.setOnClickListener(this);
        progressDialog = new ProgressDialog(Login.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);

        HashMap<String, String> url_maps = new HashMap<String, String>();
        url_maps.put("", "http://beta.usha.businesstowork.com/assets/images/banner.jpg");
        url_maps.put("", "http://beta.usha.businesstowork.com/assets/images/CategoryImages/c6f832b6-e0d2-4b30-892d-9c8afb717571.jpg");
        url_maps.put("", "http://beta.usha.businesstowork.com/assets/images/CategoryImages/3a909b7c-cc9c-4da4-9358-986dd1eebf65.jpg");
        url_maps.put("", "http://beta.usha.businesstowork.com/assets/images/CategoryImages/bc67cfd7-b4ff-482f-8e3d-d58a65c9c7b5.jpg");
        url_maps.put("", "http://beta.usha.businesstowork.com/assets/images/CategoryImages/ac07768b-1e4e-44b8-a341-6534e69d26fe.jpg");
        url_maps.put("", "http://beta.usha.businesstowork.com/assets/images/CategoryImages/be3f4be1-7c6f-4ef2-8644-eef368a8d999.jpg");


        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Hannibal", R.drawable.puma_offer);
        file_maps.put("Big Bang Theory", R.drawable.home);
        file_maps.put("House of Cards", R.drawable.accountstatement);
        file_maps.put("Game of Thrones", R.drawable.indicator_corner_bg);

        for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Background2Foreground);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(6000);
        mDemoSlider.addOnPageChangeListener(this);
        ListView l = (ListView) findViewById(R.id.transformers);
        l.setAdapter(new TransformerAdapter(this));
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDemoSlider.setPresetTransformer(((TextView) view).getText().toString());
                Toast.makeText(Login.this, ((TextView) view).getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_navigation_drawer, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_custom_indicator:
                mDemoSlider.setCustomIndicator((PagerIndicator) findViewById(R.id.custom_indicator));
                break;
            case R.id.action_custom_child_animation:
                mDemoSlider.setCustomAnimation(new ChildAnimationExample());
                break;
            case R.id.action_restore_default:
                mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                mDemoSlider.setCustomAnimation(new DescriptionAnimation());
                break;
            case R.id.action_github:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/daimajia/AndroidImageSlider"));
                startActivity(browserIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }


    public void showMyAlertDialog() {

        Dialog dialog = new Dialog(Login.this);
        dialog.setContentView(R.layout.forgot_password);
        dialog.setCanceledOnTouchOutside(false);
        LayoutInflater inflater = Login.this.getLayoutInflater();

        /*Reffer layoutInflater =Login.this.getLayoutInflater()*/

        Button back = dialog.findViewById(R.id.back);
        Button sub_btn = dialog.findViewById(R.id.sub_btn);
        final EditText name_edit = dialog.findViewById(R.id.user_email);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                /*

                 */
            }
        });
        dialog.show();

    }


    private void hitForgotPasswordApi() {
        user_id = user_email.getText().toString();

        if (isConnectingToInternet()) {
            // new ForgotPassword().execute();slider
            ForgotPassword_REST_api();
        } else
            Toast.makeText(getApplicationContext(),
                    "No Internet Connection....", Toast.LENGTH_SHORT).show();

        if (isConnectingToInternet()) {
            //new ForgotPassword_CRG().execute();
            ChangPassword();

        } else {
            Toast.makeText(getApplicationContext(),
                    "No Internet Connection....", Toast.LENGTH_SHORT).show();
        }
    }

    private void ForgotPassword_REST_api() {

        /* showDialog();*/
        Dp_Usha_Interface apiInterface = ApiClient.getClient().create(Dp_Usha_Interface.class);
        Call<List<ForgotPassword>> call1 = apiInterface.ForgetPasswordApi("bearer", this.user_id);
        Dp_Usha_Interface apiService = (Dp_Usha_Interface) ApiClient.getClient().create(CRG_API_Interface.class);


        call1.enqueue(new Callback<List<ForgotPassword>>() {
            @Override
            public void onResponse(Call<List<ForgotPassword>> call, Response<List<ForgotPassword>> response) {
                /*hideDialog();*/
                /*String status = forgot_pass.getStatus();*/


                user_email.setText("Mail Sent Successfully");
                if (status != null && status.equals("1")) {
                    util.showAlert(Login.this, "Password sent successfully to branch email ID");
                } else {
                    util.showAlert(Login.this, "Please enter correct branch email ID!");
                }
            }
               /* if ( errormessage!= null && status.equals("1")) {
                    util.showAlert(Login_Activity.this, "Password sent successfully to branch email ID");
                } else {
                    util.showAlert(Login_Activity.this, "Please enter correct branch email ID!");
                }*/


            @Override
            public void onFailure(Call<List<ForgotPassword>> call, Throwable t) {
                /*Toast.makeText(Login.this, "failure", Toast.LENGTH_SHORT).show();*/
                String error = t.getMessage();
                Toast.makeText(getApplicationContext(),
                        "Network Error....", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void CRG_ForgotPassword_REST_api() {

    }


    private void ChangPassword() {

        ChangpasswordInterface apiInterface = ApiClient.getClient().create(ChangpasswordInterface.class);
        Call<List<Changpassword>> call1 = apiInterface.ChangePassword("bearer token", this.user_id, OldPassword, NewPassword);
        ChangpasswordInterface apiService = (ChangpasswordInterface) ApiClient.getClient().create(ChangpasswordInterface.class);


        call1.enqueue(new Callback<List<Changpassword>>() {
            @Override
            public void onResponse(Call<List<Changpassword>> call, Response<List<Changpassword>> response) {
                hideDialog();

                hideDialog();
                /*  String status = login_model.getStatus();*/
                user_email.setText("");
                if (status != null && status.equals("1")) {
                    util.showAlert(Login.this, "Password sent successfully to branch email ID");
                } else {
                    util.showAlert(Login.this, "Please enter correct branch email ID!");
                }

            }

            @Override
            public void onFailure(Call<List<Changpassword>> call, Throwable t) {
                String error = t.getMessage();
                Toast.makeText(getApplicationContext(),
                        "Network Error....", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDialog() {
        if (progressDialog != null && !progressDialog.isShowing()) {

        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                Login_click();
                break;
            case R.id.forg_pass:
                showMyAlertDialog();
                break;
            case R.id.sub_btn:
                hitForgotPasswordApi();
                break;
            /*case R.id.sliderView:
                sliderView.slideToPreviousPosition();
                break;*/
        }

    }


    private void Login_click() {
        user_id = user_name.getText().toString();
        password = pass.getText().toString();

        if (validateLogin(user_id, password)) {
            if (isConnectingToInternet()) {
               /* if (user_id.startsWith("UserId")) {

                } else {
                    hitLogin_REST_api(user_id, password);
                }*/
                hitAPILogin(user_id,password);


            }
        }
    }

    private boolean validateLogin(String user_id, String password) {
        user_id = user_name.getText().toString();
        if (user_id.trim().equalsIgnoreCase("")) {
            Toast.makeText(getApplicationContext(),
                    "Please Enter User ID!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.trim().equalsIgnoreCase("")) {
            Toast.makeText(getApplicationContext(),
                    "Please Enter password!", Toast.LENGTH_SHORT).show();
            return false;

        }
        return true;
    }

    private boolean isConnectingToInternet() {
        ConnectivityManager connectivity = (ConnectivityManager) Login.this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();

            //moveTaskToBack(false);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {

        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Do you want to exit application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                        //close();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();


    }

    private void hitLogin_REST_api(String user_id, String password) {
        /* valid_=validate();*/
        showDialog();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<GetLoginResponse>> call1 = apiInterface.GetUser("bearer", this.user_id, this.password);


        call1.enqueue(new Callback<List<GetLoginResponse>>() {
            @Override
            public void onResponse(Call<List<GetLoginResponse>> call, Response<List<GetLoginResponse>> response) {
                /*  Toast.makeText(Login.this, response.code(), Toast.LENGTH_SHORT).show();*/
                List<GetLoginResponse> getLoginResponses = response.body();
/*
                Login.this.user_id = getLoginResponses.get(0).getUserId();
*/

                Login.this.password = getLoginResponses.get(0).getPassword();

                String user_id = String.valueOf(getLoginResponses.get(0).getUserId());

                String password = getLoginResponses.get(0).getPassword();
                String u = getLoginResponses.get(0).getUserType();
                /*util.setPassword_CRG(Login.this, password);
                util.setUserId(Login.this, user_id);
                util.setEmail_id(Login.this, email);
                util.setusername(Login.this, name);*/

                SharedPreferences pref = getSharedPreferences("new1", MODE_PRIVATE);
                SharedPreferences.Editor ed = pref.edit();
                ed.putBoolean("Deactivate1", true);
                ed.apply();
                Intent mainIntent = new Intent(Login.this, Main2Activity.class);
                mainIntent.putExtra("from", FROM);
                startActivity(mainIntent);
                finish();

            }

            @Override
            public void onFailure(Call<List<GetLoginResponse>> call, Throwable t) {
                Toast.makeText(Login.this, "failure", Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void hideDialog() {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();

            }
        } catch (Exception e) {
            e.getMessage();
        }


    }


    private void hitAPIAccessToken() {

        retrofitManager.getAuthToken(this, this, Constants.API_TYPE.TOKEN, true);
    }
    @Override
    public void onSuccess(Response<ResponseBody> response, Constants.API_TYPE apiType) {
        try {
            String strResponse = response.body().string();
            Log.e("menu", response.body().toString());
            // Toast.makeText(this, apiType+"Response  "+strResponse,Toast.LENGTH_SHORT).show();
            if (apiType == Constants.API_TYPE.TOKEN) {
                AuthToken tokenBean = new Gson().fromJson(strResponse, AuthToken.class);

                String token = tokenBean.getAccessToken();
                String token_type = tokenBean.getTokenType();
                SharedPreferencesUtil.setAuthToken(Login.this,token_type+" "+token);
            }else if(apiType == Constants.API_TYPE.LOGIN){

                Gson gson = new Gson();
                Type listType = new TypeToken<List<LoginResponse>>() {
                }.getType();
               List<LoginResponse> loginResponse = gson.fromJson(strResponse, listType);

               if(loginResponse!=null && !loginResponse.isEmpty()){
                   String userid=loginResponse.get(0).getUserId();
                  if(userid!=null && !userid.equals("")){
                      SharedPreferencesUtil.setUserId(Login.this,userid);
                      SharedPreferencesUtil.setPassword(Login.this,password);

                      startActivity(new Intent(Login.this, DrawerMainActivity.class));
                       finish();
                  }else {
                      Toast.makeText(getApplicationContext(),"Invalid user",Toast.LENGTH_SHORT).show();
                  }
               }else
                   Toast.makeText(getApplicationContext(),"Invalid user",Toast.LENGTH_SHORT).show();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void hitAPILogin(String user_id,String password) {
        retrofitManager.Login(this, this, Constants.API_TYPE.LOGIN, user_id,password,true);
    }
    @Override
    public void onFailure(Response<ResponseBody> response, Constants.API_TYPE apiType) {
          Toast.makeText(this, apiType+" error "+response.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onApiException(APIError error, Response<ResponseBody> response, Constants.API_TYPE apiType) {
           Toast.makeText(this, apiType+" onApiException "+response.toString(),Toast.LENGTH_SHORT).show();
    }
}