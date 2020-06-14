package dpusha.app.com.usha.fragment.orders;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dpusha.app.com.usha.R;
import dpusha.app.com.usha.adapter.recycler_decorator.MyDividerItemDecoration;
import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.model.AuthToken;
import dpusha.app.com.usha.network.APIError;
import dpusha.app.com.usha.network.RequestListener;
import dpusha.app.com.usha.network.RetrofitManager;
import dpusha.app.com.usha.orders_home.adapter.OrderListAdapter_new;
import dpusha.app.com.usha.orders_home.model.OrderList;
import dpusha.app.com.usha.orders_home.util.Constants;
import dpusha.app.com.usha.orders_home.util.utility;
import dpusha.app.com.usha.shared_preference.SharedPreferencesUtil;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link orderListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class orderListFragment extends Fragment implements RequestListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View rootView;
    private ProgressDialog progressDialog;

    private RetrofitManager retrofitManager = RetrofitManager.getInstance();
    private RecyclerView recycler_orderlist;
    OrderListAdapter_new orderListAdapter;
    SwipeRefreshLayout pullToRefresh;
    Button go,cancel;

    EditText s_date;
    EditText e_date;
    DatePickerDialog picker;

    SearchView getEditsearch;
    TextView msg;

    final ArrayList<OrderList> filterList= new ArrayList<>();
    private MainListner listenerMainActivity;
    private FragmentActivity activity;

    public orderListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment orderListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static orderListFragment newInstance(String param1, String param2) {
        orderListFragment fragment = new orderListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (activity == null) {
            activity = getActivity();
            listenerMainActivity = (MainListner) activity;
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_order_list, container, false);

     //   hitAPIAccessToken();
        go = rootView.findViewById(R.id.go_button);

        msg=rootView.findViewById(R.id.msg_result);


        s_date=rootView.findViewById(R.id.start_date);
        e_date=rootView.findViewById(R.id.end_date);

        s_date.setInputType(InputType.TYPE_NULL);
        e_date.setInputType(InputType.TYPE_NULL);

        recycler_orderlist= (RecyclerView) rootView.findViewById(R.id.recycler_orderlist_1);
        recycler_orderlist.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recycler_orderlist.setItemAnimator(new DefaultItemAnimator());

        MyDividerItemDecoration decoration1 = new MyDividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL, 0);
        recycler_orderlist.addItemDecoration(decoration1);
       // hitAPIAccessToken();
        getOrderList();

        pullToRefresh = rootView.findViewById(R.id.pullToRefresh);
        pullToRefresh.setEnabled(true);

        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               // l_date_filter.setVisibility(View.GONE);

                e_date.getText().clear();
                s_date.getText().clear();
                msg.setVisibility(View.GONE);

                getOrderList();

                pullToRefresh.setRefreshing(false);
            }
        });




        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                msg.setVisibility(View.GONE);

                String start_date01 = s_date.getText().toString();
                String end_date01 = e_date.getText().toString();

                if (start_date01.length() != 0 && end_date01.length() != 0) {

                    String date[] = start_date01.split("-");
                    Log.d("Date_1", date[0]);
                    Log.d("Date_1", date[1]);


                    String dayString = String.valueOf(date[0]);
                    if (dayString.length() == 1) {
                        dayString = "0" + dayString;
                    }


                    //start date convert format
                    String monthString = String.valueOf(date[1]);
                    if (monthString.length() == 1) {
                        monthString = "0" + monthString;
                    }


                    StringBuilder sub_s_date = new StringBuilder();

                    sub_s_date.append(dayString).append("-").append(monthString).append("-").append(date[2]);


                    //end date convert

                    String date_end[] = end_date01.split("-");


                    String e_dayString = String.valueOf(date_end[0]);
                    if (e_dayString.length() == 1) {
                        e_dayString = "0" + e_dayString;
                    }


                    //start date convert format
                    String e_monthString = String.valueOf(date_end[1]);
                    if (e_monthString.length() == 1) {
                        e_monthString = "0" + e_monthString;
                    }


                    StringBuilder sub_e_date = new StringBuilder();


                    sub_e_date.append(e_dayString).append("-").append(e_monthString).append("-").append(date_end[2]);

                    String Valid_e_date= sub_e_date.toString();
                    String Valid_s_date=sub_s_date.toString();

                    if(utility.Check_validity(Valid_s_date,Valid_e_date,"dd-MM-yyyy")){


                        filterList(Valid_s_date, Valid_e_date);
                       // Toast.makeText(getContext(),"Valid date method called",Toast.LENGTH_LONG).show();

                    }else{

                        e_date.getText().clear();
                        Toast.makeText(getContext(),"To Date Should be greater than From date",Toast.LENGTH_LONG).show();

                    }




                }else{


                    Toast.makeText(getContext(),"Date is empty",Toast.LENGTH_LONG).show();
                }
            }
        });


        e_date.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP){

                    final Calendar cldr = Calendar.getInstance();
                    int day = cldr.get(Calendar.DAY_OF_MONTH);
                    int month = cldr.get(Calendar.MONTH);
                    int year = cldr.get(Calendar.YEAR);
                    // date picker dialog
                    picker = new DatePickerDialog(getContext(),
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {


                                    e_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                }
                            }, year, month, day);
                    picker.show();

                    // Do what you want
                    return true;
                }
                return false;
            }
        });


        s_date.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP){

                    final Calendar cldr = Calendar.getInstance();
                    int day = cldr.get(Calendar.DAY_OF_MONTH);
                    int month = cldr.get(Calendar.MONTH);
                    int year = cldr.get(Calendar.YEAR);
                    // date picker dialog
                    picker = new DatePickerDialog(getContext(),
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {





                                    s_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                }
                            }, year, month, day);
                    picker.show();

                    // Do what you want
                    return true;
                }
                return false;
            }
        });


        // search
        getEditsearch= rootView.findViewById(R.id.search);
        getEditsearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String newText)
            {
                //orderListAdapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                orderListAdapter.getFilter().filter(newText);
                return false;
            }
        });




        return rootView;
    }


    //Filter method

    private void filterList(String StartDate, String EndDate){

        ArrayList<OrderList> filterByDate=new ArrayList<>();
        // String StartDate="30-04-2020";
        // String EndDate="30-04-2020";

        for(int i=0; i<filterList.size(); i++)
        {
            String Created_date =String.valueOf(filterList.get(i).getCreatedDate());
            String created_date1[]=Created_date.split("T");
            String Created_date2=created_date1[0];
            String create_date2= utility.covertDateAndTimeFormat( created_date1[0]);
            Log.d("created_date",create_date2);

            /*boolean NotExpired = utility.isToDateafterFromDate(StartDate, create_date2, "dd-MM-yyyy");
            boolean NotExpired = utility.isToDateafterFromDate(StartDate,EndDate,create_date2, "dd-MM-yyyy");*/
            boolean NotExpired = utility.getFronDate_toDate_data(StartDate,EndDate,create_date2, "dd-MM-yyyy");

            if (NotExpired || create_date2.equals(StartDate)||create_date2.equals(EndDate)){


                filterByDate.add(filterList.get(i));
            }




        }

        if(filterByDate.size()==0)
        {
            msg.setVisibility(View.VISIBLE);
        }



        orderListAdapter = new OrderListAdapter_new(getContext(),filterByDate,listenerMainActivity);
        recycler_orderlist.setAdapter(orderListAdapter);







    }


    //get order List
    private void getOrderList(){

        String user_id= SharedPreferencesUtil.getUserId(getContext());




        retrofitManager.GetOrderList(this,getContext(), Constants.API_TYPE.ORDER_LIST,user_id,true);
    }



    @Override
    public void onSuccess(Response<ResponseBody> response, Constants.API_TYPE apiType) {
        try {
            String strResponse = response.body().string();
            Log.e("menu", response.body().toString());
            // Toast.makeText(this, apiType+"Response  "+strResponse,Toast.LENGTH_SHORT).show();
           if(apiType == Constants.API_TYPE.ORDER_LIST){

                Gson gson = new Gson();
                Type listType = new TypeToken<List<OrderList>>() {
                }.getType();
                List<OrderList> OrderListResponse = gson.fromJson(strResponse, listType);

                if(OrderListResponse!=null && !OrderListResponse.isEmpty()){
                    filterList.addAll(OrderListResponse);
                    orderListAdapter = new OrderListAdapter_new(getContext(),OrderListResponse,listenerMainActivity);
                    recycler_orderlist.setAdapter(orderListAdapter);


                    //Toast.makeText(getContext(),"Order List",Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(getContext(),"Failed!",Toast.LENGTH_SHORT).show();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onFailure(Response<ResponseBody> response, Constants.API_TYPE apiType) {
        //  Toast.makeText(this, apiType+" error "+response.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onApiException(APIError error, Response<ResponseBody> response, Constants.API_TYPE apiType) {
        //   Toast.makeText(this, apiType+" onApiException "+response.toString(),Toast.LENGTH_SHORT).show();
    }






}
