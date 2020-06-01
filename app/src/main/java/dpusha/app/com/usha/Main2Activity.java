package dpusha.app.com.usha;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import dpusha.app.com.usha.Apiclint.Changepasswordclint;
import dpusha.app.com.usha.Interface.Anouncementinterface;
//import dpusha.app.com.usha.orders_home.orders.ClientOrdersActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends AppCompatActivity /*implements View.OnClickListener*/ {
    private ImageView cartimage, menuRight;
    private TextView mTextMessage;
    private Dialog dialog;
    private RelativeLayout accountsttument, carwash,order_list;
    ImageView edit_profile;
    Context context;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.offer:
                    Intent faqs = new Intent(Main2Activity.this, Home.class);
                    startActivity(faqs);

                    return true;
                case R.id.faq:
                    Intent faq = new Intent(Main2Activity.this, Search.class);
                    startActivity(faq);
                    return true;
                case R.id.navigation_history:
                    Intent fg = new Intent(Main2Activity.this, Profile.class);
                    startActivity(fg);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=Main2Activity.this;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main2);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
       /* accountsttument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popup();
            }*/
        initView();

    }

    private void initView() {

        order_list= findViewById(R.id.order_list);
        carwash = findViewById(R.id.carwash);
        cartimage = findViewById(R.id.cartimage);
        menuRight = findViewById(R.id.menuRight);
       /* menuRight.setOnClickListener(this);
        cartimage.setOnClickListener(this);*/
        accountsttument = findViewById(R.id.accountsttument);
        /*car5.setOnClickListener(this);*/
        mTextMessage = (TextView) findViewById(R.id.message);
        menuRight=findViewById(R.id.menuRight);

        order_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Intent i = new Intent (context, ClientOrdersActivity.class);
             //   startActivity(i);

            }
        });


        menuRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dilog();
            }
        });
        accountsttument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup();
//
            }
        });
        carwash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(Main2Activity.this, Dashbord.class);
                startActivity(mainIntent);
            }
        });

    }

    private void Dilog() {
        Dialog dialog = new Dialog(Main2Activity.this);
        dialog.setContentView(R.layout.manu_dilog);
        dialog.setCanceledOnTouchOutside(false);
        LayoutInflater inflater = Main2Activity.this.getLayoutInflater();

        /*Reffer layoutInflater =Login.this.getLayoutInflater()*/

      /*  ImageView edit_profile = dialog.findViewById(R.id.edit_profile);
        Button sub_btn = dialog.findViewById(R.id.sub_btn);
        final EditText name_edit = dialog.findViewById(R.id.user_email);*/

        //  System.out.println("bearer "+getToken);

     /* sub_btn.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){
        hitForgotPasswordApi();
                *//*  getToken = name_edit.getText().toString();

                  progressDialog=new ProgressDialog(Login.this);
                  if(Configuration.isInternetConnection(getApplicationContext())) {

                      String jsonData = toJSon(user_email.getText().toString().trim());
                      new GET_DATA().execute(jsonData);*//*
    }
                 *//* else
                  {
                      Intent intent = new Intent(Login.this,ErrorScreen.class);
                      startActivity(intent);
                  }*//*


    });
*/
       /* edit_profile.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                *//*

                 *//*
            }
        });*/
        dialog.show();

    }


    //    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.menuRight:
//                showWalletDialog();
//                break;
//            case R.id.car5:
//                popup();
//                break;
//        }
//
//    }
    private void popup() {
        Dialog dialog = new Dialog(Main2Activity.this);
        dialog.setContentView(R.layout.anouncentpopup);
        dialog.setCanceledOnTouchOutside(false);
        LayoutInflater inflater = Main2Activity.this.getLayoutInflater();

        /*Reffer layoutInflater =Login.this.getLayoutInflater()*/

        ImageView edit_profile = dialog.findViewById(R.id.edit_profile);
        Button sub_btn = dialog.findViewById(R.id.sub_btn);
        final EditText name_edit = dialog.findViewById(R.id.user_email);

        //  System.out.println("bearer "+getToken);

     /* sub_btn.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){
        hitForgotPasswordApi();
                *//*  getToken = name_edit.getText().toString();

                  progressDialog=new ProgressDialog(Login.this);
                  if(Configuration.isInternetConnection(getApplicationContext())) {

                      String jsonData = toJSon(user_email.getText().toString().trim());
                      new GET_DATA().execute(jsonData);*//*
    }
                 *//* else
                  {
                      Intent intent = new Intent(Login.this,ErrorScreen.class);
                      startActivity(intent);
                  }*//*


    });
*/
        edit_profile.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                /*

                 */
            }
        });
        dialog.show();


        Anouncementinterface apiInterface = CRG_API_Client.getClient().create(Anouncementinterface.class);


        Call<Changepasswordclint> call1 = apiInterface.GetAll("bearer token");


        call1.enqueue(new Callback<Changepasswordclint>() {


            @Override
            public void onResponse(Call<Changepasswordclint> call, Response<Changepasswordclint> response) {



                {
                    if (response.isSuccessful())
                    {
                       Changepasswordclint token  = response.body();
                    }
                }


               /* Changepasswordclint userList = response.body();
                Integer text = userList.getParentId();
                Integer total = userList.getSequence();
                String totalPages = userList.getCategoryCode();
             *//*   List<Changepasswordclint> datumList = userLis;*//*
                Toast.makeText(getApplicationContext(), text + " page\n" + total + " total\n" + totalPages + " totalPages\n", Toast.LENGTH_SHORT).show();
*/
               /* for (Changepasswordclint.Datum datum : datumList) {
                    Toast.makeText(getApplicationContext(), "id : " + datum.id + " name: " + datum.first_name + " " + datum.last_name + " avatar: " + datum.avatar, Toast.LENGTH_SHORT).show();
                }*/

            }




            @Override
            public void onFailure(Call<Changepasswordclint> call, Throwable t) {

            }

          /*  @Override
            public void onFailure(Call<Changepasswordclint> call, Throwable t) {
                List<GetLoginResponse> getLoginResponses = response.body();
                Login.this.user_id = getLoginResponses.get(0).getUserId();

                Login.this.password = getLoginResponses.get(0).getPassword();

                String user_id = String.valueOf(getLoginResponses.get(0).getUserId());

                String password = getLoginResponses.get(0).getPassword();
                String u = getLoginResponses.get(0).getUserType();
                util.setPassword_CRG(Login.this, password);
                util.setUserId(Login.this, user_id);
                util.setEmail_id(Login.this, email);
                util.setusername(Login.this, name);

                SharedPreferences pref = getSharedPreferences("new1", MODE_PRIVATE);
                SharedPreferences.Editor ed = pref.edit();
                ed.putBoolean("Deactivate1", true);
                ed.apply();
                Intent mainIntent = new Intent(Login.this, Main2Activity.class);
                mainIntent.putExtra("from", FROM);
                startActivity(mainIntent);
                finish();
*/


        });
    }
}

  /*  InterfaceApi api = ApiClient.getClient().create(InterfaceApi.class);
    Call<LoginResponse> call = api.getLoginResponse(sendToken, loginDatum);

    call.enqueue(new Callback<LoginResponse>() {
        @Override
        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
            LoginResponse loginResponse = response.body();
            String token = response.headers().get("Token");
            if (token != null) {
                Log.e("tokenTAG", "Token : " + token);
                sendToken = token;
            }
            Toast.makeText(context, loginResponse.getStatusMessage() + "", Toast.LENGTH_SHORT).show();

            loadProgress.setVisibility(View.GONE);
            loginBtn.setVisibility(View.VISIBLE);
        }

        @Override
        public void onFailure(Call<LoginResponse> call, Throwable t) {
            loadProgress.setVisibility(View.GONE);
            loginBtn.setVisibility(View.VISIBLE);
        }
    });*/


        /*Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.planets_array,
//        android.R.layout.simple_spinner_item);
                R.layout.my_spinner_textview);
//    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.my_spinner_textview);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
    }


    search=dialog.findViewById<TextView>(R.id.search)
                val setting=dialog.findViewById<TextView>(R.id.setting)
                val profile=dialog.findViewById<TextView>(R.id.profile)
                val near_by=dialog.findViewById<TextView>(R.id.near_by)
                val record=dialog.findViewById<TextView>(R.id.record)
                val logout=dialog.findViewById<TextView>(R.id.logout)
                record.setOnClickListener(this)
        profile.setOnClickListener(this)
        setting.setOnClickListener(this)
        logout.setOnClickListener(this)
        search.setOnClickListener(this)
        val menuRightdilog=dialog.findViewById<ImageView>(R.id.menuRightdilog)
                val dilog=dialog.findViewById<android.support.constraint.ConstraintLayout>(R.id.dilog)
                menuRightdilog.setOnClickListener(View.OnClickListener {
            dialog.cancel()*/
       /* uploadkycdocCall = apiInterface.uploadkycdoc("Bearer " + token, filePart, requestBodyMap);

        uploadkycdocCall.enqueue(new Callback<UploadKycpojo>() {
            @Override
            public void onResponse(Call<UploadKycpojo> call, Response<UploadKycpojo> response) {
                cancelProgressDialog();
                try {
                    if (response.isSuccessful()) {

                    } else {

                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<UploadKycpojo> call, Throwable t) {

            }
        });
    }
    shareimprove this answer
        private void getTokenResponse() {
            Call<Token> call2 = apiInterface.getLoginResponse(authenticationRequest);
            call2.enqueue(new Callback<Token>() {
                @Override
                public void onResponse(Call<Token> call, Response<Token> response) {
                    Token token = response.body();
                }
                @Override
                public void onFailure(Call<Token> call, Throwable t) {
                    call.cancel();
                }
            });
        }
    }*/
 /*   uploadkycdocCall = apiInterface.uploadkycdoc("Bearer " + token);

    uploadkycdocCall.enqueue(new Callback<UploadKycpojo>() {
        @Override
        public void onResponse(Call<UploadKycpojo> call, Response<UploadKycpojo> response) {
            cancelProgressDialog();
            try {
                if (response.isSuccessful()) {

                } else {

                }
            } catch (Exception e) {

            }
        }

        @Override
        public void onFailure(Call<UploadKycpojo> call, Throwable t) {

        }
    });

}*/