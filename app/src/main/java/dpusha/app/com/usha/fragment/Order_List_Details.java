package dpusha.app.com.usha.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import dpusha.app.com.usha.R;
import dpusha.app.com.usha.model.AuthToken;
import dpusha.app.com.usha.network.APIError;
import dpusha.app.com.usha.network.RequestListener;
import dpusha.app.com.usha.network.RetrofitManager;
import dpusha.app.com.usha.orders_home.adapter.OrderListAdapter_detail;
import dpusha.app.com.usha.orders_home.model.Array_data_Model;
import dpusha.app.com.usha.orders_home.model.OrderListDetails_Model;
import dpusha.app.com.usha.orders_home.util.Constants;
import dpusha.app.com.usha.shared_preference.SharedPreferencesUtil;
import okhttp3.ResponseBody;
import retrofit2.Response;


public class Order_List_Details extends Fragment implements RequestListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String id;
    private RecyclerView recycler_orderlist_details;

    OrderListAdapter_detail orderListAdapterDetail;

    final ArrayList<Array_data_Model> filterList= new ArrayList<>();

    private RetrofitManager retrofitManager = RetrofitManager.getInstance();
    View rootView;

    public Order_List_Details() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
  /*  public static Order_List_Details newInstance(String param1, String param2) {

        Order_List_Details fragment = new Order_List_Details();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            /*mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);*/
            id = getArguments().getString("id");
            Toast.makeText(getContext(),id,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_order__list__details, container, false);


        recycler_orderlist_details= (RecyclerView) rootView.findViewById(R.id.recycler_orderlist_details);
        recycler_orderlist_details.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recycler_orderlist_details.setItemAnimator(new DefaultItemAnimator());

        getOrderListDetails();





        return rootView;
    }


    public void getOrderListDetails(){






        retrofitManager.GetOrderListDetails(this,getContext(), Constants.API_TYPE.ORDER_LIST_DETAILS,id,true);




    }

    private void hitAPIAccessToken() {



        retrofitManager.getAuthToken(this, getActivity(), Constants.API_TYPE.TOKEN,SharedPreferencesUtil.getUserId(getActivity()),SharedPreferencesUtil.getPassword(getActivity()) ,true);
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
                SharedPreferencesUtil.setAuthToken(getContext(),token_type+" "+token);
            }else if(apiType == Constants.API_TYPE.ORDER_LIST_DETAILS){

               /* Gson gson = new Gson();
                Type listType = new TypeToken<List<Order_List_Details>>() {
                }.getType();
                ArrayList<Order_List_Details> OrderListDetailsResponse = gson.fromJson(strResponse, listType);*/

          //      filterList.addAll(OrderListResponse);

                // getting data
                OrderListDetails_Model orderListDetails = new Gson().fromJson(strResponse, OrderListDetails_Model.class);
                String order_id = orderListDetails.getOrderId();
                Log.d("SKU_id", order_id);
               // String token_type = tokenBean.getTokenType();
                    for(int i=0; i<orderListDetails.getItems().size();i++) {
                        filterList.add(orderListDetails.getItems().get(i));
                        String SKU = filterList.get(i).getSKU();
                        Log.d("SKU", SKU);
                    }



                    orderListAdapterDetail= new OrderListAdapter_detail(getContext(),filterList);
                    recycler_orderlist_details.setAdapter(orderListAdapterDetail);

                //if(orderListDetails!=null && !orderListDetails.isEmpty()){
                   /* filterList.addAll(OrderListResponse);

                    orderListAdapter = new OrderListAdapter_new(getContext(),OrderListResponse);
                    recycler_orderlist.setAdapter(orderListAdapter);*/


                    Toast.makeText(getContext(),"Order List Details",Toast.LENGTH_SHORT).show();
                //}else
                  //  Toast.makeText(getContext(),"Invalid user",Toast.LENGTH_SHORT).show();
            }

        } catch (IOException e) {
           // Log.d("exceptis",e.toString());
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
