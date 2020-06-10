package dpusha.app.com.usha.fragment.book_order.by_category;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import dpusha.app.com.usha.adapter.CategoryAdapter;
import dpusha.app.com.usha.adapter.recycler_decorator.GridSpacingItemDecoration;
import dpusha.app.com.usha.adapter.recycler_decorator.ListSpacingDecoration;
import dpusha.app.com.usha.adapter.recycler_decorator.RecyclerViewMargin;
import dpusha.app.com.usha.adapter.recycler_decorator.SpacesItemDecoration;
import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.model.ProductCategory;
import dpusha.app.com.usha.network.APIError;
import dpusha.app.com.usha.network.RequestListener;
import dpusha.app.com.usha.network.RetrofitManager;
import dpusha.app.com.usha.orders_home.util.Constants;
import dpusha.app.com.usha.shared_preference.SharedPreferencesUtil;
import okhttp3.ResponseBody;
import retrofit2.Response;

//import dpusha.app.com.usha.model.ProductSKU;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderByItemCategory extends Fragment implements RequestListener {

    @BindView(R.id.recycler_category)
    RecyclerView recycler_category;


    private RetrofitManager retrofitManager = RetrofitManager.getInstance();
    private List<ProductCategory> productCategoryList = new ArrayList<>();
    private FragmentActivity activity;
    private MainListner listenerMainActivity;
    public OrderByItemCategory() {
        // Required empty public constructor
    }

    public static OrderByItemCategory newInstance() {
        return new OrderByItemCategory();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.order_by_category, container, false);
        ButterKnife.bind(this, view);

        recycler_category.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recycler_category.setItemAnimator(new DefaultItemAnimator());
     //   recycler_category.addItemDecoration(new ListSpacingDecoration(20));
        recycler_category.addItemDecoration(new GridSpacingItemDecoration(2,10,true));



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (activity == null) {
            activity = getActivity();
            listenerMainActivity = (MainListner) activity;
        }
        hitAPIGetProductCategory();
    }




    private void hitAPIGetProductCategory() {

        retrofitManager.getProductCategory(this, getActivity(), Constants.API_TYPE.PRODUCT_CATEGORY, SharedPreferencesUtil.getUserId(getActivity()), true);
    }


    @Override
    public void onSuccess(Response<ResponseBody> response, Constants.API_TYPE apiType) {
        try {
            String strResponse = response.body().string();
            Log.e("menu", response.body().toString());
            // Toast.makeText(this, apiType+"Response  "+strResponse,Toast.LENGTH_SHORT).show();
            if (apiType == Constants.API_TYPE.PRODUCT_CATEGORY) {
                productCategoryList.clear();
                Gson gson = new Gson();
                Type listType = new TypeToken<List<ProductCategory>>() {
                }.getType();
                List<ProductCategory> _productCategoryList = gson.fromJson(strResponse, listType);
                productCategoryList.addAll(_productCategoryList);
                if (_productCategoryList != null && !_productCategoryList.isEmpty()) {
                      CategoryAdapter categoryAdapter = new CategoryAdapter(getActivity(), productCategoryList,listenerMainActivity);
                      recycler_category.setAdapter(categoryAdapter);
                } else {
                    recycler_category.setAdapter(null);
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
