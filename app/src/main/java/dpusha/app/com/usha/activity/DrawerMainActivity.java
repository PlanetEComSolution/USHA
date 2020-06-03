package dpusha.app.com.usha.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dpusha.app.com.usha.Login;
import dpusha.app.com.usha.fragment.OrderByItemCodeFragment;
import dpusha.app.com.usha.fragment.orderListFragment;
import dpusha.app.com.usha.model.AuthToken;
import dpusha.app.com.usha.model.DrawerItem;
import dpusha.app.com.usha.R;
import dpusha.app.com.usha.adapter.LeftMenuAdapter;
import dpusha.app.com.usha.adapter.RecyclerViewMargin;
import dpusha.app.com.usha.fragment.HomeFragment;
import dpusha.app.com.usha.listeners.AppResumeListener;
import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.model.LoginResponse;
import dpusha.app.com.usha.network.APIError;
import dpusha.app.com.usha.network.RequestListener;
import dpusha.app.com.usha.network.RetrofitManager;
import dpusha.app.com.usha.orders_home.util.Constants;
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


    @BindView(R.id.profile_username)
    TextView profile_username;

    @BindView(R.id.imgvw_notification)
    ImageView imgvw_notification;

    @BindView(R.id.profile_userimage)
    ImageView profile_userimage;

    LeftMenuAdapter leftMenuAdapter;
    private RetrofitManager retrofitManager = RetrofitManager.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);
        ButterKnife.bind(this);
        hitAPIAccessToken();
        drawerActivityListener();
        initBottomViewAndLoadFragments();
        initLeftDrawerMenu();
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

    @OnClick({R.id.menuLeft,R.id.ll_logout})
    public void onClick(@NonNull View view) {
        switch (view.getId()) {
            case R.id.menuLeft:
                openLeftDrawer();
                break;
            case R.id.ll_logout:
                logout();
                break;
        }
    }
    public void onLeftDrawerItemClick(int position) {
       // Toast.makeText(this,"Drawer Clicked ,Postion: "+position,Toast.LENGTH_SHORT).show();
        closeLeftDrawer();
        switch (position) {
            case 0:
                // addFragment(new searchFragment(), "searchFragment", true);
                break;

            case 1:
                // addFragment(new searchFragment(), "searchFragment", true);

                break;
            case 3:
                // addFragment(new searchFragment(), "searchFragment", true);
                addFragment(new orderListFragment(), "orderListFragment", true);
                break;
            case 4:
                // addFragment(new searchFragment(), "searchFragment", true);

                break;
            case 6:
                // addFragment(new searchFragment(), "searchFragment", true);

                break;
            case 7:
                // addFragment(new searchFragment(), "searchFragment", true);

                break;

            case 8:
                // addFragment(new searchFragment(), "searchFragment", true);

                break;
            case 9:
                // addFragment(new searchFragment(), "searchFragment", true);

                break;
            case 10:
                // addFragment(new searchFragment(), "searchFragment", true);

                break;
            case 11:
                // addFragment(new searchFragment(), "searchFragment", true);

                break;
        }
    }

    public void onLeftDrawerBookOrderItemClick(int position) {
       // Toast.makeText(this,"Drawer Clicked ,Postion: "+position,Toast.LENGTH_SHORT).show();
        closeLeftDrawer();
        switch (position) {
            case 0:
                addFragment(new OrderByItemCodeFragment(), "OrderByItemCodeFragment", true);
                break;

            case 1:
                // addFragment(new searchFragment(), "searchFragment", true);
                break;
            case 2:
                // addFragment(new searchFragment(), "searchFragment", true);
                break;
            case 3:
                // addFragment(new searchFragment(), "searchFragment", true);
                break;
        }


    }
    public void onLeftDrawerDownloadsItemClick(int position) {
       // Toast.makeText(this,"Drawer Clicked ,Postion: "+position,Toast.LENGTH_SHORT).show();
        closeLeftDrawer();

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
}
