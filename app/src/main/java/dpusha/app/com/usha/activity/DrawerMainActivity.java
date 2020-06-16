package dpusha.app.com.usha.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dpusha.app.com.usha.Login;
//import dpusha.app.com.usha.fragment.book_order.by_cart.OrderByCart;
import dpusha.app.com.usha.fragment.AnnounceFragment;
import dpusha.app.com.usha.fragment.ContactUsFragment;
import dpusha.app.com.usha.fragment.DashboardFragment;
import dpusha.app.com.usha.fragment.ProductCatolugeFragment;
import dpusha.app.com.usha.fragment.SocialNetworkingFragment;
import dpusha.app.com.usha.fragment.UsefulLinkFragment;
import dpusha.app.com.usha.fragment.book_order.BookOrderHome;
import dpusha.app.com.usha.fragment.book_order.BookOrderHome;
import dpusha.app.com.usha.fragment.book_order.by_cart.OrderByCart;
import dpusha.app.com.usha.fragment.book_order.by_category.OrderByItemCategory;
import dpusha.app.com.usha.fragment.book_order.by_itemcode.OrderByItemCode;
import dpusha.app.com.usha.fragment.book_order.by_template.OrderByTemplate;
//import dpusha.app.com.usha.fragment.cart.CartFragment;
import dpusha.app.com.usha.fragment.cart.CartSummary;
import dpusha.app.com.usha.fragment.cart.PlaceOrder;
import dpusha.app.com.usha.fragment.download.FragmentAccountStatement;
import dpusha.app.com.usha.fragment.download.InvoiceFragment;
import dpusha.app.com.usha.fragment.download.SchemesFragment;
import dpusha.app.com.usha.fragment.orders.orderListFragment;
import dpusha.app.com.usha.model.AuthToken;
import dpusha.app.com.usha.model.DrawerItem;
import dpusha.app.com.usha.R;
import dpusha.app.com.usha.adapter.LeftMenuAdapter;
import dpusha.app.com.usha.adapter.recycler_decorator.RecyclerViewMargin;
import dpusha.app.com.usha.fragment.HomeFragment;
import dpusha.app.com.usha.listeners.AppResumeListener;
import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.network.APIError;
import dpusha.app.com.usha.network.RequestListener;
import dpusha.app.com.usha.network.RetrofitManager;
import dpusha.app.com.usha.orders_home.util.Constants;
import dpusha.app.com.usha.orders_home.util.utility;
import dpusha.app.com.usha.shared_preference.SharedPreferencesUtil;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class DrawerMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainListner, AppResumeListener,RequestListener {
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.recycler_left_menu)
    RecyclerView recycler_left_menu;

    @BindView(R.id.profile_image)
    com.github.abdularis.civ.CircleImageView profile_image;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    @BindView(R.id.txtvw_userName)
    TextView txtvw_userName;

    @BindView(R.id.txtvw_userid)
    TextView txtvw_userid;

    @BindView(R.id.txtvw_user_mail)
    TextView txtvw_user_mail;


    /*  @BindView(R.id.ll_logout)
      LinearLayout ll_logout;*/
    NavigationView navigationView;

   /* @BindView(R.id.imgvw_cart)
    ImageView imgvw_cart;*/


    @BindView(R.id.button_cartCount)
    Button button_cartCount;

  /*  @BindView(R.id.ll_logout)
    LinearLayout ll_logout;*/



    Dialog changePassDialog;
    @BindView(R.id.profile_username)
    TextView profile_username;

    @BindView(R.id.imgvw_notification)
    ImageView imgvw_notification;

    @BindView(R.id.profile_userimage)
    ImageView profile_userimage;
    EditText oldPassword;
    EditText newPassword;
    EditText confirmPassword;
    private String userPassword,newPass,confirmPass;
    private String uToken;
    ImageView icProfile;

    LeftMenuAdapter leftMenuAdapter;
    private RetrofitManager retrofitManager = RetrofitManager.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changePassDialog = new Dialog(this, R.style.Theme_AppCompat_Light_Dialog);
        changePassDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_drawer);
        final ActionBar actionBar = getSupportActionBar();
        navigationView = findViewById(R.id.nav_view_live_stream);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //actionBar.setDisplayHomeAsUpEnabled(true);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        navigationView.setNavigationItemSelectedListener(this); // You missed this line, add this to your code

        ButterKnife.bind(this);

        hitAPIAccessToken();
        drawerActivityListener();
        initBottomViewAndLoadFragments();
        initLeftDrawerMenu();

        //icProfile=toolbar.findViewById(R.id.profile_username);
        profile_userimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(DrawerMainActivity.this,"jhvfgbdhvfd",Toast.LENGTH_LONG).show();
                showChangePassDialog();
            }
        });
        refreshCartCount(DrawerMainActivity.this);
    }

    @Override
    public void refreshCartCount(Context context) {
        int count=utility.getCartItemCount(context);
        button_cartCount.setText(String.valueOf(count));
    }

    @Override
    public void clearCart(Context context) {
        SharedPreferencesUtil.clearCartItems(context);
        refreshCartCount(context);
    }

    @Override
    public void addFragment(Fragment fragment, String tag, boolean isReplace) {
        boolean isAddBackstack = true;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Log.e("size", "A :" + manager.getBackStackEntryCount() + "");
        //  fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        if (!isReplace)
            transaction.add(R.id.fragment_container, fragment, fragment.getClass().getSimpleName());
        else
            transaction.replace(R.id.fragment_container, fragment, fragment.getClass().getSimpleName());

        if (isAddBackstack) {
            transaction.addToBackStack(tag);
        }

        transaction.commit();

        Log.e("size", "C :" + manager.getBackStackEntryCount() + "");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
    private void initBottomViewAndLoadFragments() {
        addFragment(new HomeFragment(), "HomeFragment", true);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            addFragment(new HomeFragment(), "HomeFragment", true);
                            break;

                        case R.id.nav_search:
                            // addFragment(new searchFragment(), "searchFragment", true);
                            Toast.makeText(DrawerMainActivity.this,"Add your search fragment HERE:",Toast.LENGTH_SHORT).show();
                            break;

                    }


                    return true;
                });
    }

    public void initLeftDrawerMenu() {
        List<DrawerItem> drawerItemList=new ArrayList<>();
        drawerItemList.add(new DrawerItem("Dashboard",R.drawable.drawer_dash));
        drawerItemList.add(new DrawerItem("Announcements",R.drawable.drawer_announcement));
        drawerItemList.add(new DrawerItem("Book an Order",R.drawable.drawer_book_order));
        drawerItemList.add(new DrawerItem("Orders",R.drawable.drawer_orders));
        drawerItemList.add(new DrawerItem("Product Catalogue",R.drawable.drawer_product_catalogue));
        drawerItemList.add(new DrawerItem("Downloads",R.drawable.drawer_downloads));
        drawerItemList.add(new DrawerItem("Feedbacks",R.drawable.drawer_feedback));
        drawerItemList.add(new DrawerItem("Social Networking",R.drawable.drawer_social));
        drawerItemList.add(new DrawerItem("Others",R.drawable.drawer_others));
        drawerItemList.add(new DrawerItem("Service Request",R.drawable.drawer_service_request));
        drawerItemList.add(new DrawerItem("Retailer Registration",R.drawable.drawer_retailer_regsiteration));
        drawerItemList.add(new DrawerItem("Contact us",R.drawable.drawer_contact_us));

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_left_menu.setLayoutManager(mLayoutManager);
        recycler_left_menu.setItemAnimator(new DefaultItemAnimator());
        //recycler_live_stream_menu.setHasFixedSize(true);
        RecyclerViewMargin decoration = new RecyclerViewMargin(35, 1);
        while (recycler_left_menu.getItemDecorationCount() > 0) {
            recycler_left_menu.removeItemDecorationAt(0);
        }
        recycler_left_menu.addItemDecoration(decoration);
        //recycler_left_menu.addItemDecoration(new SimpleDividerItemDecoration(this));
        leftMenuAdapter=new LeftMenuAdapter(this, drawerItemList);
        recycler_left_menu.setAdapter(leftMenuAdapter);

    }

    @OnClick({R.id.menuLeft,R.id.ll_logout,R.id.imgvw_cart})
    public void onClick(@NonNull View view) {
        switch (view.getId()) {
            case R.id.menuLeft:
                openLeftDrawer();
                break;
            case R.id.ll_logout:
                closeLeftDrawer();
                logout();

                break;

            case R.id.imgvw_cart:
                addFragment(new CartSummary(), "CartSummary", true);
                break;

        }


    }
    public void onLeftDrawerItemClick(int position) {
        // Toast.makeText(this,"Drawer Clicked ,Postion: "+position,Toast.LENGTH_SHORT).show();
        closeLeftDrawer();
        switch (position) {
            case 0:
                 addFragment(new DashboardFragment(), "DashboardFragment", true);
                break;

            case 1:
                // addFragment(new searchFragment(), "searchFragment", true);
                addFragment(new AnnounceFragment(),"announceFragment",true);

                break;
            case 2:
                addFragment(new BookOrderHome(), "BookOrderHome", true);

                break;
            case 3:
                // addFragment(new searchFragment(), "searchFragment", true);
                addFragment(new orderListFragment(), "orderListFragment", true);
                break;
            case 4:
                // addFragment(new searchFragment(), "searchFragment", true);
                addFragment(new ProductCatolugeFragment(),"productCatalouge",true);

                break;
            case 6:
                // addFragment(new searchFragment(), "searchFragment", true);

                break;
            case 7:
                // addFragment(new searchFragment(), "searchFragment", true);
                addFragment(new SocialNetworkingFragment(),"socialFragment",true);
                break;

            case 8:
                // addFragment(new searchFragment(), "searchFragment", true);
                addFragment(new UsefulLinkFragment(), "usefulLinkFragment", true);
                break;
            case 9:
                // addFragment(new searchFragment(), "searchFragment", true);
                String serviceUrl="http://care.usha.com";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(serviceUrl));
                startActivity(intent);
                break;
            case 10:
                //addFragment(new searchFragment(), "searchFragment", true);
                /*Uri uri = Uri.parse("//Url for Retailer Registration\n" +
                        "//http://148.72.22.64:92/EmployeeUser/CreateEditUserRegistration?&amp;UserCode=&amp;usertype=UTC0002&amp;Source=WEB"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);*/
                String url = "http://148.72.22.64:92/EmployeeUser/CreateEditUserRegistration?&amp;UserCode=&amp;usertype=UTC0002&amp;Source=WEB";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;
            case 11:
                addFragment(new ContactUsFragment(), "contactUsFragment", true);
                break;
        }
    }

    public void onLeftDrawerBookOrderItemClick(int position) {
        // Toast.makeText(this,"Drawer Clicked ,Postion: "+position,Toast.LENGTH_SHORT).show();
        closeLeftDrawer();
        switch (position) {
            case 0:
                addFragment(new OrderByItemCode(), "OrderByItemCodeFragment", true);
                break;

            case 1:
                addFragment(new OrderByItemCategory(), "OrderByItemCategory", true);

                break;
            case 2:
                addFragment(new OrderByCart(), "OrderByCart", true);
                break;
            case 3:
                addFragment(new OrderByTemplate(), "OrderByTemplate", true);
                break;
        }


    }
    public void onLeftDrawerDownloadsItemClick(int position) {
        // Toast.makeText(this,"Drawer Clicked ,Postion: "+position,Toast.LENGTH_SHORT).show();
        closeLeftDrawer();
        switch (position) {
            case 0:
                addFragment(new SchemesFragment(), "schemeFragment", true);
                break;

            case 1:
                addFragment(new FragmentAccountStatement(), "accountFragment", true);
                break;

            case 2:
                addFragment(new InvoiceFragment(), "invoiceFragment", true);
                break;
        }
    }
    private void closeLeftDrawer() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
        }
    }
    private void openLeftDrawer() {
        if (!drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.openDrawer(GravityCompat.START);
        }
    }


    @Override
    public void onAppResume() {
        //  hitAPIAccessTokenNews();
    }
    @Override
    public void onBackPressed() {


        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }  else {
            int count = getSupportFragmentManager().getBackStackEntryCount();
            if (count > 1) {
                getSupportFragmentManager().popBackStackImmediate();
            } else {
                showExitDialog();
                // finish();

            }
        }
    }
    public  void showExitDialog() {
        //  final long[] mLastClickTime = {0};
        final AlertDialog.Builder builder = new AlertDialog.Builder(DrawerMainActivity.this);
        final AlertDialog dialog = builder.create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.setMessage("Do you want to exit?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // mis-clicking prevention, using threshold of 1000 ms
              /*  if (SystemClock.elapsedRealtime() - mLastClickTime[0] < 1000) {
                    return;
                }*/
                // mLastClickTime[0] = SystemClock.elapsedRealtime();
                dialog.dismiss();
                finish();


            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });

        builder.setCancelable(true);
        builder.show();
    }
    public void drawerActivityListener() {
        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View view, float v) {

            }

            @Override
            public void onDrawerOpened(View view) {
               /* if (drawer.isDrawerOpen(GravityCompat.END)) {
                    showBottomNavigation(false);
                }*/


            }

            @Override
            public void onDrawerClosed(View view) {
               /* if (!drawer.isDrawerOpen(GravityCompat.END)) {
                    showBottomNavigation(true);
                }*/
                //  leftMenuAdapter.onDrawerClosed();
                initLeftDrawerMenu();
            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });
    }


    protected void logout() {
        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Do you want to logout?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        SharedPreferencesUtil.clearPrefernce(DrawerMainActivity.this);
                        startActivity(new Intent(DrawerMainActivity.this,Login.class));
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();


    }


    //test

    public void showChangePassDialog(){
        changePassDialog=new Dialog(DrawerMainActivity.this);
        changePassDialog.setContentView(R.layout.alert_change_password);
        changePassDialog.setCanceledOnTouchOutside(false);
        int width = (int) (DrawerMainActivity.this.getResources().getDisplayMetrics().widthPixels * 0.90);
        int height = (int) (DrawerMainActivity.this.getResources().getDisplayMetrics().heightPixels * 0.90);
        LayoutInflater inflater = DrawerMainActivity.this.getLayoutInflater();
        Button btn_save = changePassDialog.findViewById(R.id.btn_save);
        Button btn_reset = changePassDialog.findViewById(R.id.btn_reset);
        oldPassword=changePassDialog.findViewById(R.id.edit_pass_old);
        newPassword=changePassDialog.findViewById(R.id.edit_new_pass);
        confirmPassword=changePassDialog.findViewById(R.id.edit_confirm_pass1);
        changePassDialog.getWindow().setLayout(width, height);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //changePassDialog.dismiss();

                hitChangePasswordApi();

                changePassDialog.dismiss();
            }
        });
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldPassword.setText("");
                newPassword.setText("");
                confirmPassword.setText("");
            }
        });
        changePassDialog.show();
    }

    private void hitChangePasswordApi() {
        //user_id = user_email.getText().toString();
        userPassword = oldPassword.getText().toString();
        newPass=newPassword.getText().toString();
        confirmPass=confirmPassword.getText().toString();
        if (isConnectingToInternet()) {
            // new ForgotPassword().execute();slider
            //ForgotPassword_REST_api();
            uToken = SharedPreferencesUtil.getAuthToken(this);
           /* if(newPassword.getText().toString().length()<8 &&!isValidPassword(newPassword.getText().toString())){
                Toast.makeText(DrawerMainActivity.this," Invalid Password",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(DrawerMainActivity.this, "Valid Password",Toast.LENGTH_LONG).show();
                hitAPIChangePassword(newPass,confirmPass,userPassword);
            }
            if(isValidPassword(newPass)){
                hitAPIChangePassword(newPass,confirmPass,userPassword);

            }*/
           /* if(newPassword.getText().toString().length()<8 &&!isValidPassword(newPassword.getText().toString())){
                Toast.makeText(DrawerMainActivity.this," Invalid Password",Toast.LENGTH_LONG).show();

            }
            else{
                Toast.makeText(DrawerMainActivity.this, "Valid Password",Toast.LENGTH_LONG).show();
                hitAPIChangePassword(newPass,confirmPass,userPassword);
            }*/
            if (newPassword.getText().toString().isEmpty() || confirmPassword.getText().toString().isEmpty()) {
                Toast.makeText(DrawerMainActivity.this, " empty Password", Toast.LENGTH_LONG).show();

            }
            if ((newPassword.getText().toString().length() < 8) || (newPassword.getText().toString().length() < 8)) {
                Toast.makeText(DrawerMainActivity.this, " less than 8 character Password", Toast.LENGTH_LONG).show();
            }
            //hitAPIChangePassword(userPassword,newPass,confirmPass);
            if (!newPassword.getText().toString().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*+=?-]).{8,15}$") || !newPassword.getText().toString().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*+=?-]).{8,15}$")) {
                Toast.makeText(DrawerMainActivity.this, " provide special char", Toast.LENGTH_LONG).show();
            } else {
                hitAPIChangePassword(userPassword,newPass,confirmPass);
                //hitAPIChangePassword(newPass, confirmPass, userPassword);
                Toast.makeText(DrawerMainActivity.this, " SUCCESS", Toast.LENGTH_LONG).show();
            }
        }
        if (isConnectingToInternet()) {
            //new ForgotPassword_CRG().execute();
            //test
            //ChangPassword();

        } else {
            Toast.makeText(getApplicationContext(),
                    "No Internet Connection....", Toast.LENGTH_SHORT).show();
        }
    }

    /* public static boolean isValidPassword(final String password) {
         Pattern pattern;
         Matcher matcher;
         final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
         pattern = Pattern.compile(PASSWORD_PATTERN);
         matcher = pattern.matcher(password);
         return matcher.matches();

     }
 */
    public static boolean isValidPassword(String s) {
        Pattern PASSWORD_PATTERN
                = Pattern.compile(
                "[a-zA-Z0-9\\!\\@\\#\\$]{8,24}");

        return !TextUtils.isEmpty(s) && PASSWORD_PATTERN.matcher(s).matches();
    }

    private void hitAPIChangePassword(String userPass,String userNewPass,String userConfirmPass){
        retrofitManager.changePassword(this,this, Constants.API_TYPE.CHANGEPASSWORD,userPass,userNewPass,userConfirmPass,uToken,true);

        /*if(userNewPass.equals(userConfirmPass)){
       retrofitManager.changePassword(this,this, Constants.API_TYPE.CHANGEPASSWORD,userPass,userNewPass,userConfirmPass,uToken,true);
   }
   else{
       Toast.makeText(DrawerMainActivity.this,"password not matched",Toast.LENGTH_LONG).show();
   }*/
    }
    private boolean isConnectingToInternet() {
        ConnectivityManager connectivity = (ConnectivityManager) DrawerMainActivity.this
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


    //
    private void hitAPIAccessToken() {

        retrofitManager.getAuthToken(this, this, Constants.API_TYPE.TOKEN,SharedPreferencesUtil.getUserId(this),SharedPreferencesUtil.getPassword(this) ,true);
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
                SharedPreferencesUtil.setAuthToken(DrawerMainActivity.this,token_type+" "+token);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void onFailure(Response<ResponseBody> response, Constants.API_TYPE apiType) {
        Toast.makeText(this, apiType+" error "+response.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onApiException(APIError error, Response<ResponseBody> response, Constants.API_TYPE apiType) {
        Toast.makeText(this, apiType+" onApiException "+response.toString(),Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case android.R.id.home:
                //onBackPressed();
                break;
            case R.id.profile_userimage:
                //showChangePassDialog();
                //Log.d(TAG, "SHARE BUTTON");
                Toast.makeText(this, "hiiiiiiii",Toast.LENGTH_SHORT).show();

                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;

    }

}
