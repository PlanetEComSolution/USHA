package dpusha.app.com.usha.fragment.book_order.by_category;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dpusha.app.com.usha.R;
import dpusha.app.com.usha.adapter.CartItemsAdapter;
import dpusha.app.com.usha.adapter.CategoryAdapter;
import dpusha.app.com.usha.adapter.DivisionAdapter;
import dpusha.app.com.usha.adapter.ProductListAdapter;
import dpusha.app.com.usha.adapter.recycler_decorator.GridSpacingItemDecoration;

import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.model.CartItem;
import dpusha.app.com.usha.model.Item;
import dpusha.app.com.usha.model.Material;
import dpusha.app.com.usha.model.ProductCategory;
import dpusha.app.com.usha.model.ProductDescription;
import dpusha.app.com.usha.model.ProductDivision;
import dpusha.app.com.usha.model.ProductSKU;
import dpusha.app.com.usha.model.ShipToParty;
import dpusha.app.com.usha.model.draft.GetDraft;
import dpusha.app.com.usha.network.APIError;
import dpusha.app.com.usha.network.RequestListener;
import dpusha.app.com.usha.network.RetrofitManager;
import dpusha.app.com.usha.orders_home.util.Constants;
import dpusha.app.com.usha.orders_home.util.utility;
import dpusha.app.com.usha.shared_preference.SharedPreferencesUtil;
import okhttp3.ResponseBody;
import retrofit2.Response;

//import dpusha.app.com.usha.model.ProductSKU;


/**
 * A simple {@link Fragment} subclass.
 */
public class Division extends Fragment implements RequestListener {



    @BindView(R.id.recycler_division)
    RecyclerView recycler_division;



    private RetrofitManager retrofitManager = RetrofitManager.getInstance();

    private List<ProductDivision> productDivisionList = new ArrayList<>();

    private MainListner listenerMainActivity;
    private FragmentActivity activity;
    String CategoryCode="";

    public Division() {
        // Required empty public constructor
    }

    public static Division newInstance() {
        return new Division();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.divison, container, false);
        ButterKnife.bind(this, view);

        recycler_division.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recycler_division.setItemAnimator(new DefaultItemAnimator());
        //   recycler_category.addItemDecoration(new ListSpacingDecoration(20));
        recycler_division.addItemDecoration(new GridSpacingItemDecoration(2,10,true));

        Bundle bundle=getArguments();
        if(bundle!=null && bundle.containsKey("CategoryCode")){
          CategoryCode=   bundle.getString("CategoryCode");
           // hitAPIGetDivisionByProductCategory(CategoryCode);

        }


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (activity == null) {
            activity = getActivity();
            listenerMainActivity = (MainListner) activity;
        }
        hitAPIGetDivisionByCategoryCode(CategoryCode);
    }




    private void hitAPIGetDivisionByProductCategory(String CategoryCode) {
        retrofitManager.getDivisionByProductCategory(this, getActivity(), Constants.API_TYPE.PRODUCT_DIVISION, SharedPreferencesUtil.getUserId(getActivity()), CategoryCode, "GetDivisionsByProductCategory", true);
    }
    private void hitAPIGetDivisionByCategoryCode(String CategoryCode) {
        retrofitManager.getDivisionByCategoryCode(this, getActivity(), Constants.API_TYPE.DIVISION, CategoryCode, true);
    }


        @Override
    public void onSuccess(Response<ResponseBody> response, Constants.API_TYPE apiType) {
        try {
            String strResponse = response.body().string();
            Log.e("menu", response.body().toString());
            // Toast.makeText(this, apiType+"Response  "+strResponse,Toast.LENGTH_SHORT).show();
           if (apiType == Constants.API_TYPE.DIVISION) {
                productDivisionList.clear();
                Gson gson = new Gson();
                Type listType = new TypeToken<List<ProductDivision>>() {
                }.getType();
                List<ProductDivision> _productDivisionList = gson.fromJson(strResponse, listType);
                productDivisionList.addAll(_productDivisionList);

               if (_productDivisionList != null && !_productDivisionList.isEmpty()) {
                   DivisionAdapter categoryAdapter = new DivisionAdapter(getActivity(), productDivisionList,listenerMainActivity);
                   recycler_division.setAdapter(categoryAdapter);
               } else {
                   recycler_division.setAdapter(null);
                   Toast.makeText(getActivity(), "No data found!", Toast.LENGTH_SHORT).show();
               }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Response<ResponseBody> response, Constants.API_TYPE apiType) {
        Toast.makeText(getActivity(), apiType + " error " + response.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onApiException(APIError error, Response<ResponseBody> response, Constants.API_TYPE apiType) {
        Toast.makeText(getActivity(), apiType + " onApiException " + response.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }





}
