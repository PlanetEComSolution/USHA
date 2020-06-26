package dpusha.app.com.usha.fragment.book_order.by_cart;


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
import androidx.core.widget.NestedScrollView;
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
import dpusha.app.com.usha.adapter.CartAdapter;

import dpusha.app.com.usha.adapter.recycler_decorator.EndlessScrollListener;
import dpusha.app.com.usha.adapter.recycler_decorator.GridSpacingItemDecoration;
import dpusha.app.com.usha.adapter.recycler_decorator.MyDividerItemDecoration;
import dpusha.app.com.usha.adapter.recycler_decorator.WrapContentGridLayoutManager;

import dpusha.app.com.usha.listeners.CartItemChangedListener;
import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.model.Cart;
import dpusha.app.com.usha.model.CartItem;
import dpusha.app.com.usha.model.CategoryType;
import dpusha.app.com.usha.model.Item;
import dpusha.app.com.usha.model.Material;
import dpusha.app.com.usha.model.ProductCategory;
import dpusha.app.com.usha.model.ProductDescription;
import dpusha.app.com.usha.model.ProductDivision;
import dpusha.app.com.usha.model.ProductSKU;
import dpusha.app.com.usha.model.ShipToParty;
import dpusha.app.com.usha.model.SubCategory;

import dpusha.app.com.usha.network.APIError;
import dpusha.app.com.usha.network.RequestListener;
import dpusha.app.com.usha.network.RetrofitManager;
import dpusha.app.com.usha.orders_home.util.Constants;
import dpusha.app.com.usha.orders_home.util.utility;
import dpusha.app.com.usha.shared_preference.SharedPreferencesUtil;
import okhttp3.ResponseBody;
import retrofit2.Response;


//import static dpusha.app.com.usha.R.attr.layoutManager;
import static dpusha.app.com.usha.R2.attr.layoutManager;

//import dpusha.app.com.usha.model.ProductSKU;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderByCart extends Fragment implements RequestListener, CartItemChangedListener {
   // @BindView(R.id.nestedScrollRotanaNewsHome)
   // NestedScrollView nestedScrollRotanaNewsHome;
    @BindView(R.id.spinnerCategory)
    Spinner spinnerCategory;

    @BindView(R.id.spinnerDivision)
    Spinner spinnerDivision;

    @BindView(R.id.spinnerCategoryType)
    Spinner spinnerCategoryType;

    @BindView(R.id.spinnerSubCategory)
    Spinner spinnerSubCategory;

    @BindView(R.id.recycler_items)
    RecyclerView recycler_items;


    private RetrofitManager retrofitManager = RetrofitManager.getInstance();

    private String CategoryCode = "", DivisionCode = "", CategoryType = "", SubCategory = "";

    private List<ProductCategory> productCategoryList = new ArrayList<>();
    private List<ProductDivision> productDivisionList = new ArrayList<>();
    private List<CategoryType> categoryTypeList = new ArrayList<>();
    private List<SubCategory> subCategoryList = new ArrayList<>();

    private List<Cart> cartList = new ArrayList<>();
    private ProductDescription item;
    CartAdapter cartAdapter;
    private MainListner listenerMainActivity;
    private FragmentActivity activity;
    GridLayoutManager gridLayoutManager;
   // int pageNumber=1;
    int pageSize = 30;//, pageLimit=5;
    String callType = "";
    private boolean isLoading;

    public OrderByCart() {
        // Required empty public constructor
    }

    public static OrderByCart newInstance() {
        return new OrderByCart();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.order_by_cart, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        cartAdapter=null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (activity == null) {
            activity = getActivity();
            listenerMainActivity = (MainListner) activity;
        }
        hitAPIGetProductCategory();
        setViewListener();
    }


    void setViewListener() {
        setTemporaryAdapterForCategorySpinner();
        setTemporaryAdapterForDivsionSpinner();
        setTemporaryAdapterForCategoryTypeSpinner();
        setTemporaryAdapterForSubCategorySpinner();
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // On selecting a spinner item
                setTemporaryAdapterForDivsionSpinner();
                setTemporaryAdapterForCategoryTypeSpinner();
                setTemporaryAdapterForSubCategorySpinner();

                ProductCategory item = (ProductCategory) parent.getItemAtPosition(position);
                CategoryCode = item.getCatCode();
                if (!CategoryCode.equals("0"))
                    hitAPIGetDivisionByProductCategory();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerDivision.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setTemporaryAdapterForCategoryTypeSpinner();
                setTemporaryAdapterForSubCategorySpinner();

                ProductDivision item = (ProductDivision) parent.getItemAtPosition(position);
                DivisionCode = item.getDivCode();
                if (!DivisionCode.equals("0")) {
                    hitAPIGetCategoryTypeByProductCategory();
                    //   pageNumber=1;
                    cartList.clear();
                    callType = "GetByDivisions";
                    hitAPIGetCartList(1, callType);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerCategoryType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setTemporaryAdapterForSubCategorySpinner();

                CategoryType item = (CategoryType) parent.getItemAtPosition(position);
                CategoryType = String.valueOf(item.getCatType());
                String CategoryCode = String.valueOf(item.getCatCode());
                if (!CategoryCode.equals("0")) {
                    hitAPIGetSubCategoryByProductCategory();
                    //pageNumber=1;
                    cartList.clear();
                    callType = "GetByCategories";
                    hitAPIGetCartList(1, callType);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerSubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                SubCategory item = (SubCategory) parent.getItemAtPosition(position);
                SubCategory = String.valueOf(item.getSubCategory());
                String CategoryCode = String.valueOf(item.getCatCode());
                if (!CategoryCode.equals("0")) {
                    //pageNumber=1;
                    cartList.clear();
                    callType = "GetBySubCategories";
                    hitAPIGetCartList(1, callType);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
      /*
        recycler_items.addOnScrollListener(new EndlessScrollListener(gridLayoutManager) {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                Log.e("LoadMore", "page:" + page + "  totalItemsCount:" + String.valueOf(totalItemsCount));
                if (++page > 1) {
                    hitAPIGetCartList(page, callType);
                }
                 isLoading = true;

                return true;
            }
        });*/

    }

    void setTemporaryAdapterForCategorySpinner() {
        CategoryCode = "";
        productCategoryList.clear();
        productCategoryList.add(new ProductCategory("0", "Select Category"));
        ArrayAdapter<ProductCategory> dataAdapter = new ArrayAdapter<ProductCategory>(getActivity(), android.R.layout.simple_spinner_item, productCategoryList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(dataAdapter);
    }

    void setTemporaryAdapterForDivsionSpinner() {
        DivisionCode = "";

        productDivisionList.clear();
        productDivisionList.add(new ProductDivision("0", "Select Division"));
        ArrayAdapter<ProductDivision> dataAdapter1 = new ArrayAdapter<ProductDivision>(getActivity(), android.R.layout.simple_spinner_item, productDivisionList);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDivision.setAdapter(dataAdapter1);
    }

    void setTemporaryAdapterForCategoryTypeSpinner() {
        CategoryType = "";

        categoryTypeList.clear();
        categoryTypeList.add(new CategoryType("0", "Select Category Type"));
        ArrayAdapter<CategoryType> dataAdapter1 = new ArrayAdapter<CategoryType>(getActivity(), android.R.layout.simple_spinner_item, categoryTypeList);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoryType.setAdapter(dataAdapter1);
    }

    void setTemporaryAdapterForSubCategorySpinner() {
        SubCategory = "";

        subCategoryList.clear();
        subCategoryList.add(new SubCategory("0", "Select Sub-Category"));
        ArrayAdapter<SubCategory> dataAdapter1 = new ArrayAdapter<SubCategory>(getActivity(), android.R.layout.simple_spinner_item, subCategoryList);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubCategory.setAdapter(dataAdapter1);
    }

    private void hitAPIGetProductCategory() {

        retrofitManager.getProductCategory(this, getActivity(), Constants.API_TYPE.PRODUCT_CATEGORY, SharedPreferencesUtil.getUserId(getActivity()), true);
    }

    private void hitAPIGetDivisionByProductCategory() {
        retrofitManager.getDivisionByProductCategory(this, getActivity(), Constants.API_TYPE.PRODUCT_DIVISION, SharedPreferencesUtil.getUserId(getActivity()), CategoryCode, "GetDivisionsByProductCategory", true);
    }

    private void hitAPIGetCategoryTypeByProductCategory() {
        retrofitManager.getCategoryTypeByProductCategory(this, getActivity(), Constants.API_TYPE.CATEGORY_TYPE, CategoryCode, DivisionCode, "GetCategoryByProductCategoryAndDivision", true);
    }

    private void hitAPIGetSubCategoryByProductCategory() {
        retrofitManager.getSubCategoryByProductCategory(this, getActivity(), Constants.API_TYPE.SUB_CATEGORY, CategoryCode, DivisionCode, CategoryType, "GetSubCategoryByProductCategoryAndDivision", true);
    }

    private void hitAPIGetCartList(int pageNumber, String callType) {
        retrofitManager.getProductsForList(this, getActivity(), Constants.API_TYPE.CART_LIST, DivisionCode, CategoryType, SubCategory, callType, pageNumber, pageSize, true);
    }


    @Override
    public void onSuccess(Response<ResponseBody> response, Constants.API_TYPE apiType) {
        try {
            String strResponse = response.body().string();
            Log.e("Response", response.body().toString());
            // Toast.makeText(this, apiType+"Response  "+strResponse,Toast.LENGTH_SHORT).show();
            if (apiType == Constants.API_TYPE.PRODUCT_CATEGORY) {
                productCategoryList.clear();
                Gson gson = new Gson();
                Type listType = new TypeToken<List<ProductCategory>>() {
                }.getType();
                List<ProductCategory> _productCategoryList = gson.fromJson(strResponse, listType);
                productCategoryList.add(new ProductCategory("0", "Select Category"));
                productCategoryList.addAll(_productCategoryList);
                if (_productCategoryList != null && !_productCategoryList.isEmpty()) {
                    ArrayAdapter<ProductCategory> dataAdapter = new ArrayAdapter<ProductCategory>(getActivity(), android.R.layout.simple_spinner_item, productCategoryList);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCategory.setAdapter(dataAdapter);

                } else {
                    setTemporaryAdapterForCategorySpinner();
                    Toast.makeText(getActivity(), "No data found!", Toast.LENGTH_SHORT).show();

                }
            } else if (apiType == Constants.API_TYPE.PRODUCT_DIVISION) {
                productDivisionList.clear();
                Gson gson = new Gson();
                Type listType = new TypeToken<List<ProductDivision>>() {
                }.getType();
                List<ProductDivision> _productDivisionList = gson.fromJson(strResponse, listType);
                productDivisionList.add(new ProductDivision("0", "Select Division"));
                productDivisionList.addAll(_productDivisionList);

                if (_productDivisionList != null && !_productDivisionList.isEmpty()) {
                    ArrayAdapter<ProductDivision> dataAdapter = new ArrayAdapter<ProductDivision>(getActivity(), android.R.layout.simple_spinner_item, productDivisionList);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerDivision.setAdapter(dataAdapter);
                   // spinnerDivision.setSelection(1);
                } else {
                    setTemporaryAdapterForDivsionSpinner();
                    Toast.makeText(getActivity(), "No data found!", Toast.LENGTH_SHORT).show();
                }
            } else if (apiType == Constants.API_TYPE.CATEGORY_TYPE) {
                categoryTypeList.clear();
                Gson gson = new Gson();
                Type listType = new TypeToken<List<CategoryType>>() {
                }.getType();
                List<CategoryType> _categoryTypeList = gson.fromJson(strResponse, listType);
                categoryTypeList.add(new CategoryType("0", "Select Category Type"));
                categoryTypeList.addAll(_categoryTypeList);

                if (_categoryTypeList != null && !_categoryTypeList.isEmpty()) {
                    ArrayAdapter<CategoryType> dataAdapter = new ArrayAdapter<CategoryType>(getActivity(), android.R.layout.simple_spinner_item, categoryTypeList);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCategoryType.setAdapter(dataAdapter);

                } else {
                    setTemporaryAdapterForCategoryTypeSpinner();
                    Toast.makeText(getActivity(), "No data found!", Toast.LENGTH_SHORT).show();
                }
            } else if (apiType == Constants.API_TYPE.CART_LIST) {
                //  cartList.clear();
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Cart>>() {
                }.getType();
                List<Cart> _cartList = gson.fromJson(strResponse, listType);
                cartList.addAll(_cartList);
                Log.e("Log_size", cartList.size() + "");
                if (_cartList != null && !_cartList.isEmpty()) {
                    //  cartAdapter = new CartAdapter(getActivity(), cartList, this, listenerMainActivity);
                    //recycler_items.setAdapter(cartAdapter);
                    checkForLoadMore();


                } else {
                  //  Toast.makeText(getActivity(), "No data found!", Toast.LENGTH_SHORT).show();
                }
            } else if (apiType == Constants.API_TYPE.SUB_CATEGORY) {
                subCategoryList.clear();
                Gson gson = new Gson();
                Type listType = new TypeToken<List<SubCategory>>() {
                }.getType();
                List<SubCategory> _subCategoryList = gson.fromJson(strResponse, listType);
                subCategoryList.add(new SubCategory("0", "Select Sub-Category"));
                subCategoryList.addAll(_subCategoryList);

                if (_subCategoryList != null && !_subCategoryList.isEmpty()) {
                    ArrayAdapter<SubCategory> dataAdapter = new ArrayAdapter<SubCategory>(getActivity(), android.R.layout.simple_spinner_item, subCategoryList);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerSubCategory.setAdapter(dataAdapter);

                } else {
                    setTemporaryAdapterForSubCategorySpinner();
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


    @Override
    public void onCartRefresh(int CartSize) {

    }

    private void checkForLoadMore() {

        if (isLoading) {
            isLoading = false;
            int curSize = cartAdapter.getItemCount();
            cartAdapter.notifyItemRangeInserted(curSize, cartList.size() - 2);
        } else {
            if (cartAdapter != null) {
                Log.e("checkLoadMore", "notify");
                cartAdapter.notifyDataSetChanged();
            } else {
                Log.e("checkLoadMore", "setAdapter");
                setAdapter();
            }
        }
        // itemProgressBar.setVisibility(View.GONE);
    }

    private void setAdapter() {

        if (cartList.size() > 0) {
            WrapContentGridLayoutManager layoutManager = new WrapContentGridLayoutManager(getActivity(), 2);
            recycler_items.setLayoutManager(layoutManager);
            cartAdapter = new CartAdapter(getActivity(), cartList, this, listenerMainActivity);
            recycler_items.setAdapter(cartAdapter);
            recycler_items.scrollTo(0, 0);

            recycler_items.addOnScrollListener(new EndlessScrollListener(layoutManager) {
                @Override
                public boolean onLoadMore(int page, int totalItemsCount) {
                    Log.e("LoadMore", "page:" + page + "  totalItemsCount:" + String.valueOf(totalItemsCount));
                    if (++page > 1) {
                        hitAPIGetCartList(page, callType);
                    }
                    isLoading = true;

                    return true;
                }
            });





        } else {

        }
    }
}
