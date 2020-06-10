package dpusha.app.com.usha.fragment.book_order.by_template;


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
import dpusha.app.com.usha.R;
import dpusha.app.com.usha.adapter.CategoryAdapter;
import dpusha.app.com.usha.adapter.TemplateAdapter;
import dpusha.app.com.usha.adapter.recycler_decorator.ListSpacingDecoration;
import dpusha.app.com.usha.adapter.recycler_decorator.RecyclerViewMargin;
import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.model.ProductCategory;
import dpusha.app.com.usha.model.Template;
import dpusha.app.com.usha.network.APIError;
import dpusha.app.com.usha.network.RequestListener;
import dpusha.app.com.usha.network.RetrofitManager;
import dpusha.app.com.usha.orders_home.util.Constants;
import okhttp3.ResponseBody;
import retrofit2.Response;

//import dpusha.app.com.usha.model.ProductSKU;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderByTemplate extends Fragment implements RequestListener {

    @BindView(R.id.recycler_template)
    RecyclerView recycler_template;


    private RetrofitManager retrofitManager = RetrofitManager.getInstance();
    private List<Template> templateList = new ArrayList<>();
    private FragmentActivity activity;
    private MainListner listenerMainActivity;
    public OrderByTemplate() {
        // Required empty public constructor
    }

    public static OrderByTemplate newInstance() {
        return new OrderByTemplate();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.order_by_template, container, false);
        ButterKnife.bind(this, view);

      //  recycler_template.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recycler_template.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler_template.setItemAnimator(new DefaultItemAnimator());

         RecyclerViewMargin decoration = new RecyclerViewMargin(0, 2);
       // MyDividerItemDecoration decoration1 = new MyDividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL, 50);
       // recycler_category.addItemDecoration(new SpacesItemDecoration(20));
       // recycler_category.addItemDecoration(decoration);

        int spanCount = 2; // 3 columns
        int spacing = 40; // 50px
        boolean includeEdge = false;
        //recycler_category.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        recycler_template.addItemDecoration(new ListSpacingDecoration(20));

          includeEdge = true;
      //  recycler_category.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));


        hitAPIGetTemplate();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (activity == null) {
            activity = getActivity();
            listenerMainActivity = (MainListner) activity;
        }
    }

   /* void initRecyclerAdapter() {

        productListAdapter = new ProductListAdapter(getActivity(), productItemList);
        recycler_items.setAdapter(productListAdapter);

    }*/


    private void hitAPIGetTemplate() {

        retrofitManager.getTemplate(this, getActivity(), Constants.API_TYPE.TEMPLATE, true);
    }


    @Override
    public void onSuccess(Response<ResponseBody> response, Constants.API_TYPE apiType) {
        try {
            String strResponse = response.body().string();
            Log.e("menu", response.body().toString());
            // Toast.makeText(this, apiType+"Response  "+strResponse,Toast.LENGTH_SHORT).show();
            if (apiType == Constants.API_TYPE.TEMPLATE) {
                templateList.clear();
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Template>>() {
                }.getType();
                List<Template> _templateList = gson.fromJson(strResponse, listType);
                templateList.addAll(_templateList);
                if (_templateList != null && !_templateList.isEmpty()) {
                    TemplateAdapter categoryAdapter = new TemplateAdapter(getActivity(), templateList);
                    recycler_template.setAdapter(categoryAdapter);
                } else {
                    recycler_template.setAdapter(null);
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


    void refreshCartRecyclerFromPreference() {
        //  if (cartObject != null && cartObject.getItems() != null && cartObject.getItems().size() > 0) {
        //     CartItemsAdapter cartItemsAdapter = new CartItemsAdapter(getActivity(), cartObject, this);
        //  recycler_items.setAdapter(cartItemsAdapter);

    }


}
