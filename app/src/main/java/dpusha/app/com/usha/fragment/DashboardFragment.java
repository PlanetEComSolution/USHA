package dpusha.app.com.usha.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dpusha.app.com.usha.R;
import dpusha.app.com.usha.adapter.DashboardAdapter;
import dpusha.app.com.usha.adapter.DivisionAdapter;
import dpusha.app.com.usha.adapter.recycler_decorator.GridSpacingItemDecoration;
import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.model.AuthToken;
import dpusha.app.com.usha.model.ContactResponse;
//import dpusha.app.com.usha.model.Dashboard;
import dpusha.app.com.usha.model.DashboardData;
import dpusha.app.com.usha.model.DashboardModel;
import dpusha.app.com.usha.network.APIError;
import dpusha.app.com.usha.network.RequestListener;
import dpusha.app.com.usha.network.RetrofitManager;
import dpusha.app.com.usha.orders_home.util.Constants;
import okhttp3.ResponseBody;
import retrofit2.Response;


public class DashboardFragment extends Fragment implements RequestListener {
    View view;
    private FragmentActivity activity;
    private MainListner listenerMainActivity;
    private RetrofitManager retrofitManager = RetrofitManager.getInstance();
/*
    @BindView(R.id.txtvw_YTD)
    TextView txtvw_YTD;


    @BindView(R.id.txtvw_MTD)
    TextView txtvw_MTD;


    @BindView(R.id.txtvw_totalCreditLimit)
    TextView txtvw_totalCreditLimit;



    @BindView(R.id.txtvw_BalanceCreditLimit)
    TextView txtvw_BalanceCreditLimit;


    @BindView(R.id.txtvw_Total_outstanding)
    TextView txtvw_Total_outstanding;


    @BindView(R.id.txtvw_Pending_Orders)
    TextView txtvw_Pending_Orders;


    @BindView(R.id.txtvw_Name)
    TextView txtvw_Name;

    @BindView(R.id.txtvw_Div)
    TextView txtvw_Div;*/


    @BindView(R.id.recycler_dashboard)
    RecyclerView recycler_dashboard;




    @BindView(R.id.txtvw_sales_ttd)
    TextView txtvw_sales_ttd;


    @BindView(R.id.txtvw_sales_mtd)
    TextView txtvw_sales_mtd;


    @BindView(R.id.txtvw_sales_ytd)
    TextView txtvw_sales_ytd;




    public DashboardFragment() {
        // Required empty public constructor
    }

    public static DashboardFragment newInstance() {

        return new DashboardFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this, view);


        recycler_dashboard.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recycler_dashboard.setItemAnimator(new DefaultItemAnimator());
        //   recycler_category.addItemDecoration(new ListSpacingDecoration(20));
        recycler_dashboard.addItemDecoration(new GridSpacingItemDecoration(2,10,false));

        //hitApiContactUs();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (activity == null) {
            activity = getActivity();
            listenerMainActivity = (MainListner) activity;
        }
        hitApiGetDashboard();
        //setViewListener();
    }



    private void hitApiGetDashboard() {

        retrofitManager.getDashboard(this, getActivity(), Constants.API_TYPE.DASHBOARD, true);
    }



    @Override
    public void onSuccess(Response<ResponseBody> response, Constants.API_TYPE apiType) {

        try {
            String strResponse = response.body().string();
            Log.e("Res", response.body().toString());
            // Toast.makeText(this, apiType+"Response  "+strResponse,Toast.LENGTH_SHORT).show();
            if (apiType == Constants.API_TYPE.DASHBOARD) {
                DashboardData mResponse = new Gson().fromJson(strResponse, DashboardData.class);
                if (mResponse != null ) {
                    setData(mResponse);
                } else {
                    //recyclerView.setAdapter(null);
                    Toast.makeText(getActivity(), "No data found!", Toast.LENGTH_SHORT).show();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    void setData(DashboardData dashboardData){

        /* txtvw_YTD.setText("YTD Rs. 45 Lac");
         txtvw_MTD.setText("MTD Rs. 45 Lac");
         txtvw_totalCreditLimit.setText("Total Credit Limit: Rs. 100 Lac");
         txtvw_BalanceCreditLimit.setText("Balance Credit Limit: Rs. 25 Lac");
        // txtvw_Total_outstanding.setText(dashboardData.getTotalOutstanding());
       //  txtvw_Pending_Orders.setText(dashboardData.getTotalOrdersPending());

        txtvw_Total_outstanding.setText("Total Outstanding: Rs. 65 Lac");
        txtvw_Pending_Orders.setText("Rs 5 Lac");

         txtvw_Name.setText("Rahul");
         txtvw_Div.setText("FAN Division");*/


        List<DashboardModel> dashboardList=new ArrayList<>();
        dashboardList.add(new DashboardModel("Sales Performance","YTD Rs. 45 Lac","MTD Rs. 45 Lac",R.drawable.sales_performance));
        dashboardList.add(new DashboardModel("Credit Limit","Total Credit Limit: Rs. 100 Lac","Balance Credit Limit: Rs. 25 Lac",R.drawable.credit_limit));
        dashboardList.add(new DashboardModel("Outstanding Status","Total Outstanding: Rs. 65 Lac","",R.drawable.total));
        dashboardList.add(new DashboardModel("Pending Orders","Rs 5 Lac","",R.drawable.cart_red));
        dashboardList.add(new DashboardModel("KAM Details","Rahul","FAN Division",R.drawable.account_red));



        DashboardAdapter categoryAdapter = new DashboardAdapter(getActivity(), dashboardList,listenerMainActivity);
        recycler_dashboard.setAdapter(categoryAdapter);



         txtvw_sales_ttd.setText("+86");
         txtvw_sales_mtd.setText("+76");
         txtvw_sales_ytd.setText("+96");

    }

    @Override
    public void onFailure(Response<ResponseBody> response, Constants.API_TYPE apiType) {

    }

    @Override
    public void onApiException(APIError error, Response<ResponseBody> response, Constants.API_TYPE apiType) {

    }



}