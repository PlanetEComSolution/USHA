package dpusha.app.com.usha;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class LoginScreen extends AppCompatActivity {
   /* public static final String FROM = "EDIT";
    Button btn;
    private Dialog dialog3;
    TextView forgot_pass;
    private String user_name, password;
    private EditText user_id, pass;
    private JSONArray jArray;
    private String inValid_username = "", inValid_otp = "", inValid_Email = "";

    private String FName, CMO, Cluster;


    private ProgressDialog progressDialog;
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
       /* btn = findViewById(R.id.login_button);
        user_id = (EditText) findViewById(R.id.user_id);
        pass = (EditText) findViewById(R.id.user_pass);
        forgot_pass = findViewById(R.id.forg_pass);
        btn.setOnClickListener(this);
        forgot_pass.setOnClickListener(this);
        progressDialog = new ProgressDialog(LoginScreen.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);

       *//* btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginScreen.this,Dashbord.class);
                startActivity(intent);
            }
        });*//*

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                Login_click();
                break;
            case R.id.forg_pass:
                *//*Forgot_passpard();*//*
                break;
        }


    }

    *//*private void Forgot_passpard() {
        showDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Email", email);
        CRG_API_Interface apiService = CRG_API_Client.getClient().create(CRG_API_Interface.class);

        apiService.CRG_ForgetPasswordApi(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CRG_ForgetPasswordApi_Response>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideDialog();
                        String error = e.getMessage();
                        Toast.makeText(getApplicationContext(),
                                "Network Error....", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(CRG_ForgetPasswordApi_Response login_model) {
                        hideDialog();
                        String status = login_model.getStatus();
                        user_email.setText("");
                        if (status != null && status.equals("1")) {
                            util.showAlert(Login_Activity.this, "Password sent successfully to branch email ID");
                        } else {
                            util.showAlert(Login_Activity.this, "Please enter correct branch email ID!");
                        }
                    }
                });

    }*//*
    private void Login_click() {

        user_name = user_id.getText().toString();
        password = pass.getText().toString();

        if (validateAllFields()) {
            if (isConnectingToInternet()) {
                if (user_name.startsWith("bm") || user_name.startsWith("crg")) {
                    hitLogin_REST_api();
                } else {

                }

            } else {
                Toast.makeText(getApplicationContext(),
                        "An error has occurred....", Toast.LENGTH_SHORT).show();
            }


        }
    }

    private void hitLogin_REST_api() {
        showDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("UserName", user_name);
        hashMap.put("Password", password);
        Dp_Usha_Interface apiService = USH_API_Client.getClient().create(Dp_Usha_Interface.class);

        apiService.GetUser(hashMap)
                .subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Login_Model>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideDialog();
                        String error = e.getMessage();
                        Toast.makeText(getApplicationContext(),
                                "Network Error....", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNext(Login_Model login_model) {

                        hideDialog();
                        String status = login_model.getStatus();
                        if (status != null && status.equals("1")) {

                            List<Login_Model_data> login_model_data = login_model.getData();
                            if (!login_model_data.isEmpty()) {
                                String login_id = String.valueOf(login_model_data.get(0).getNuLoginid());
                                String email_id = login_model_data.get(0).getVrEmail();
                                String return_username = login_model_data.get(0).getvRUSERNAME();

                                String BranchName = login_model_data.get(0).getBranchName();
                                String Branchsol = login_model_data.get(0).getBranchsol();
                               *//* util.setBranchName(LoginScreen.this, BranchName);
                                util.setBranchsol(LoginScreen.this, Branchsol);
                                util.setUserId(LoginScreen.this, login_id);
                                util.setEmail_id(LoginScreen.this, email_id);
                                util.setReturnUser_name(LoginScreen.this, return_username);*//*
                                user_id.setText("");
                                pass.setText("");
                                open_sol_dilog();

                            }


                        } else {
                            util.showAlert(LoginScreen.this, "Please Enter valid userID and password");


                        }
                    }
                });


    }

   


        private void open_sol_dilog() {
            dialog3 = new Dialog(LoginScreen.this);
            dialog3.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog3.setContentView(R.layout.content_drawer);
           *//* sol_edt = (EditText) dialog3.findViewById(R.id.sol_id);
            sol_edt.setFocusableInTouchMode(false);
            sol_edt.setFocusable(false);
            sol_edt.clearFocus();*//*
            *//*String SOL_ID_String = user_name.substring(user_name.length() - 4);
            util.setSOLID_value(this, SOL_ID_String);
            sol_edt.setText(SOL_ID_String);
*//*
            *//*dialog3.setCanceledOnTouchOutside(false);
            TextView submit = (TextView) dialog3.findViewById(R.id.submit);
            submit.setOnClickListener(this);
            dialog3.show()*//*;
        }
    
    private void showAlert(LoginScreen loginScreen, String s) {

    }

    private boolean validateAllFields() {
        user_name = user_id.getText().toString();
        if (user_name.trim().equalsIgnoreCase("")) {
            Toast.makeText(getApplicationContext(),
                    "Please Enter User ID!", Toast.LENGTH_SHORT).show();
            return false;
        }
        password = pass.getText().toString();
        if (password.trim().equalsIgnoreCase("")) {
            Toast.makeText(getApplicationContext(),
                    "Please Enter password!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;

    }
    private boolean isConnectingToInternet() {
        ConnectivityManager connectivity = (ConnectivityManager) LoginScreen.this
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
    private void showDialog() {
        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    private void hideDialog() {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        } catch (Exception e) {
            e.getMessage();
        }*/
    }

}

