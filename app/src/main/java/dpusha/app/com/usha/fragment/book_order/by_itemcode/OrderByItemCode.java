package dpusha.app.com.usha.fragment.book_order.by_itemcode;


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
import dpusha.app.com.usha.adapter.recycler_decorator.MyDividerItemDecoration;
import dpusha.app.com.usha.fragment.cart.CartFragment;
import dpusha.app.com.usha.listeners.CartItemChangedListener;
import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.model.CartItem;
import dpusha.app.com.usha.model.Item;
import dpusha.app.com.usha.model.Material;
import dpusha.app.com.usha.model.ProductCategory;
import dpusha.app.com.usha.model.ProductDescription;
import dpusha.app.com.usha.model.ProductDivision;
//import dpusha.app.com.usha.model.ProductSKU;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderByItemCode extends Fragment implements RequestListener, CartItemChangedListener {

    @BindView(R.id.spinnerCategory)
    Spinner spinnerCategory;

    @BindView(R.id.spinnerDivision)
    Spinner spinnerDivision;
    @BindView(R.id.autoCompleteSKU)
    AutoCompleteTextView autoCompleteSKU;
    @BindView(R.id.autoCompleteDescription)
    AutoCompleteTextView autoCompleteDescription;

    @BindView(R.id.recycler_items)
    RecyclerView recycler_items;


    @BindView(R.id.et_SKU)
    EditText et_SKU;

    @BindView(R.id.etCompleteDescription)
    EditText etCompleteDescription;


    private RetrofitManager retrofitManager = RetrofitManager.getInstance();


    private String CategoryCode = "", DivisionCode = "";
    private List<ProductDescription> productDescriptionList = new ArrayList<>();
    private List<ProductSKU> productSKUList = new ArrayList<>();
    private List<ProductCategory> productCategoryList = new ArrayList<>();
    private List<ProductDivision> productDivisionList = new ArrayList<>();



    private ProductDescription item;
    boolean autoCompleteSKU_ItemClicked = false, autoCompleteDescription_ItemClicked = false;

    private MainListner listenerMainActivity;
    private FragmentActivity activity;
    AlertDialog alertDialog;

    boolean APICallInProgress = false;

    public OrderByItemCode() {
        // Required empty public constructor
    }

    public static OrderByItemCode newInstance() {
        return new OrderByItemCode();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.order_by_item_code, container, false);
        ButterKnife.bind(this, view);

        recycler_items.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler_items.setItemAnimator(new DefaultItemAnimator());
        MyDividerItemDecoration decoration1 = new MyDividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL, 0);
        recycler_items.addItemDecoration(decoration1);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (activity == null) {
            activity = getActivity();
            listenerMainActivity = (MainListner) activity;
        }

        hitAPIGetDraft();
        hitAPIGetProductCategory();
        setViewListener();
    }



    void setViewListener() {
        setTemporaryAdapterForCategorySpinner();
        setTemporaryAdapterForDivsionSpinner();
        // initRecyclerAdapter();
        // refreshCartRecyclerFromPreference();

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // On selecting a spinner item
                setTemporaryAdapterForDivsionSpinner();
                autoCompleteSKU.setText("");
                autoCompleteDescription.setText("");


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
                // On selecting a spinner item
                autoCompleteSKU.setText("");
                autoCompleteDescription.setText("");
                ProductDivision item = (ProductDivision) parent.getItemAtPosition(position);
                DivisionCode = item.getDivCode();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        autoCompleteSKU.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (autoCompleteSKU.isPerformingCompletion()) {
                    // An item has been selected from the list. Ignore.
                    autoCompleteSKU_ItemClicked = true;
                    Log.e("Log_autoCompleteSKU", "isPerformingCompletion");
                } else if (!autoCompleteDescription_ItemClicked) { //
                    //  autoCompleteSKU_ItemClicked = false;
                    // Perform your task here... Like calling web service, Reading data from SQLite database, etc..
                    Log.e("Log_autoCompleteSKU", "notPerformingCompletion");
                    autoCompleteDescription.setAdapter(null);
                   /* String Prefix = s.toString().trim();
                    if (!Prefix.isEmpty()) {*/
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (APICallInProgress) return;
                            String Prefix = autoCompleteSKU.getText().toString().trim();
                            if (!Prefix.isEmpty()) {
                                setAPICallInProgress();
                                hitAPIGetSKU(Prefix, DivisionCode);
                            } /*else {
                                    autoCompleteDescription.setText("");
                                }*/
                        }
                    }, 1500);

                   /* } else {
                        autoCompleteDescription.setText("");
                    }*/
                }
                setAutoCompleteSKUClickedFalse();

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        autoCompleteDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (autoCompleteDescription.isPerformingCompletion()) {
                    // An item has been selected from the list. Ignore.
                    Log.e("Log_autoCompleteDesc", "isPerformingCompletion");
                    autoCompleteDescription_ItemClicked = true;

                } else if (!autoCompleteSKU_ItemClicked) {//
                    //autoCompleteDescription_ItemClicked = false;
                    // Perform your task here... Like calling web service, Reading data from SQLite database, etc..
                    Log.e("Log_autoCompleteDesc", "notPerformingCompletion");
                    autoCompleteSKU.setAdapter(null);
                   /* String desc = s.toString().trim();
                    if (!desc.isEmpty()) {*/
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (APICallInProgress) return;
                            String desc = autoCompleteDescription.getText().toString().trim();
                            if (!desc.isEmpty()) {
                                setAPICallInProgress();
                                hitAPIGetDescription(desc, DivisionCode);
                            } /*else {
                                    autoCompleteSKU.setText("");
                                }*/
                        }
                    }, 1500);

                    /*} else {
                        autoCompleteSKU.setText("");
                }*/
                }
                setAutoCompleteDescriptionClickedFalse();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        autoCompleteSKU.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {

                ProductSKU productSKU = (ProductSKU) parent.getItemAtPosition(pos);
                item = utility.convertSKU_To_Description(productSKU);
                autoCompleteDescription.setText(item.getDescription());
            }
        });
        autoCompleteDescription.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                item = (ProductDescription) parent.getItemAtPosition(pos);
                autoCompleteSKU.setText(item.getsKU());

            }
        });


    }

    void setTemporaryAdapterForCategorySpinner() {
        productCategoryList.clear();
        productCategoryList.add(new ProductCategory("0", "Select Category"));
        ArrayAdapter<ProductCategory> dataAdapter = new ArrayAdapter<ProductCategory>(getActivity(), android.R.layout.simple_spinner_item, productCategoryList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(dataAdapter);

    }

    void setTemporaryAdapterForDivsionSpinner() {
        productDivisionList.clear();
        productDivisionList.add(new ProductDivision("0", "Select Division"));
        ArrayAdapter<ProductDivision> dataAdapter1 = new ArrayAdapter<ProductDivision>(getActivity(), android.R.layout.simple_spinner_item, productDivisionList);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerDivision.setAdapter(dataAdapter1);

    }

    private void hitAPIGetProductCategory() {

        retrofitManager.getProductCategory(this, getActivity(), Constants.API_TYPE.PRODUCT_CATEGORY, SharedPreferencesUtil.getUserId(getActivity()), true);
    }

    private void hitAPIGetDivisionByProductCategory() {
        retrofitManager.getDivisionByProductCategory(this, getActivity(), Constants.API_TYPE.PRODUCT_DIVISION, SharedPreferencesUtil.getUserId(getActivity()), CategoryCode, "GetDivisionsByProductCategory", true);
    }

    private void hitAPIGetSKU(String Prefix, String DivisionCode) {
        retrofitManager.getSKU(this, getActivity(), Constants.API_TYPE.PRODUCT_SKU, SharedPreferencesUtil.getUserId(getActivity()), Prefix, DivisionCode, "GetProductBySku", true);
    }

    private void hitAPIGetDescription(String Prefix, String DivisionCode) {
        retrofitManager.getDescription(this, getActivity(), Constants.API_TYPE.PRODUCT_DESCRIPTION, SharedPreferencesUtil.getUserId(getActivity()), Prefix, DivisionCode, "GetProductByDesc", true);
    }

    private void hitAPIGetDraft() {
        retrofitManager.getDraft(this, getActivity(), Constants.API_TYPE.GET_DRAFT, true);
    }

    private void hitAPISaveDraft(CartItem cartItems) {
        retrofitManager.saveDraft(this, getActivity(), Constants.API_TYPE.SAVE_DRAFT, cartItems, true);
    }

    private void hitAPISaveTemplate(CartItem cartItems) {
        retrofitManager.saveTemplate(this, getActivity(), Constants.API_TYPE.SAVE_TEMPLATE, cartItems, true);
    }

    private void hitAPIGetPrice(Material material) {
        retrofitManager.getPrice(this, getActivity(), Constants.API_TYPE.GET_PRICE, material, true);
    }
    private void hitAPIGetProductDetails(String SKU) {
        retrofitManager.getProductDetails(this, getActivity(), Constants.API_TYPE.GET_PRODUCT_DETAILS, SKU,true);
    }
    @OnClick({
            R.id.button_addItem,
            R.id.button_proceed,
            R.id.button_emptyCart,
            R.id.button_saveAsDraft,
            R.id.button_saveAsTemplate,
            R.id.et_SKU,
    })
    public void onClick(@NonNull View view) {

        switch (view.getId()) {
            case R.id.button_addItem:
                if (validation()) {
                    utility.addItemToCartPreference(item,getActivity(),listenerMainActivity);
                    refreshCartRecyclerFromPreference();
                    autoCompleteSKU.setText("");
                    autoCompleteDescription.setText("");
                   // hitAPIGetProductDetails(item.getsKU());//to get product image and id
                }

                break;
            case R.id.button_proceed:
                CartItem cartObject3 = utility.getCartFromPreference(getActivity());
                if (cartObject3 != null) {
                    listenerMainActivity.addFragment(new CartFragment(), "CartFragment", true);
                } else {
                    utility.showToast(getActivity(), "Cart is empty!");
                }


                break;
            case R.id.button_emptyCart:

                SharedPreferencesUtil.clearCartItems(getActivity(),listenerMainActivity);
                refreshCartRecyclerFromPreference();

                break;
            case R.id.button_saveAsDraft:

                CartItem cartObject = utility.getCartFromPreference(getActivity());
                if (cartObject != null) {
                    hitAPISaveDraft(cartObject);
                } else {
                    utility.showToast(getActivity(), "Cart is empty!");
                }
                break;
            case R.id.button_saveAsTemplate:
                CartItem cartObject1 = utility.getCartFromPreference(getActivity());
                if (cartObject1 != null) {
                    dialogSaveTemplate(cartObject1);
                } else {
                    utility.showToast(getActivity(), "Cart is empty!");
                }

                break;
            case R.id.et_SKU:
                showDialogSKU("SKU", getActivity(), et_SKU);

                break;
        }
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
                } else {
                    setTemporaryAdapterForDivsionSpinner();
                    Toast.makeText(getActivity(), "No data found!", Toast.LENGTH_SHORT).show();
                }
            } else if (apiType == Constants.API_TYPE.PRODUCT_SKU) {
                productSKUList.clear();
                Gson gson = new Gson();
                Type listType = new TypeToken<List<ProductSKU>>() {
                }.getType();
                productSKUList = gson.fromJson(strResponse, listType);

                if (productSKUList != null && !productSKUList.isEmpty()) {
                    ArrayAdapter<ProductSKU> adapter = new ArrayAdapter<ProductSKU>
                            (getActivity(), android.R.layout.simple_spinner_dropdown_item, productSKUList);
                    // android.R.layout.select_dialog_item
                    // autoCompleteSKU.setThreshold(1);//will start working from first character
                    autoCompleteSKU.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                    autoCompleteSKU.showDropDown();
                } else
                    Toast.makeText(getActivity(), "No data found!", Toast.LENGTH_SHORT).show();
            } else if (apiType == Constants.API_TYPE.PRODUCT_DESCRIPTION) {
                productDescriptionList.clear();
                Gson gson = new Gson();
                Type listType = new TypeToken<List<ProductDescription>>() {
                }.getType();
                productDescriptionList = gson.fromJson(strResponse, listType);
                if (productDescriptionList != null && !productDescriptionList.isEmpty()) {
                    ArrayAdapter<ProductDescription> adapter = new ArrayAdapter<ProductDescription>
                            (getActivity(), android.R.layout.simple_spinner_dropdown_item, productDescriptionList);
                    // autoCompleteSKU.setThreshold(1);//will start working from first character
                    autoCompleteDescription.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                    autoCompleteDescription.showDropDown();
                } else
                    Toast.makeText(getActivity(), "No data found!", Toast.LENGTH_SHORT).show();
            } else if (apiType == Constants.API_TYPE.SAVE_DRAFT) {

                Toast.makeText(getActivity(), strResponse, Toast.LENGTH_SHORT).show();
            } else if (apiType == Constants.API_TYPE.SAVE_TEMPLATE) {

                Toast.makeText(getActivity(), strResponse, Toast.LENGTH_SHORT).show();
            } else if (apiType == Constants.API_TYPE.GET_DRAFT) {
                GetDraft draft = new Gson().fromJson(strResponse, GetDraft.class);
                utility.saveDraftToCartPreference(draft, getActivity(),listenerMainActivity);
                refreshCartRecyclerFromPreference();
                // Toast.makeText(getActivity(), strResponse, Toast.LENGTH_SHORT).show();
            }
            else if (apiType == Constants.API_TYPE.GET_PRODUCT_DETAILS) {
                ProductSKU sku = new Gson().fromJson(strResponse, ProductSKU.class);
                item.setImageName(sku.getImageName());
                item.setId(sku.getId());
                item.setQuantity(1);
                utility.addItemToCartPreference(item,getActivity(),listenerMainActivity);
                refreshCartRecyclerFromPreference();
                autoCompleteSKU.setText("");
                autoCompleteDescription.setText("");
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

    public boolean validation() {
        boolean validate = true;
        ProductCategory productCategory = (ProductCategory) spinnerCategory.getSelectedItem();
        ProductDivision productDivision = (ProductDivision) spinnerDivision.getSelectedItem();
        String sku = autoCompleteSKU.getText().toString().trim();
        String desc = autoCompleteDescription.getText().toString().trim();
        item.setQuantity(1);
        boolean isCorrectSKU = false;
        boolean isCorrectDescription = false;
        for (ProductSKU productSKU : productSKUList) {
            if (productSKU.getsKU().equals(sku)) {
                isCorrectSKU = true;
                break;
            }
        }

        for (ProductDescription productDescription : productDescriptionList) {
            if (productDescription.getDescription().equals(desc)) {
                isCorrectDescription = true;
                break;
            }
        }
        for (ProductSKU productSKU : productSKUList) {
            if (productSKU.getDescription().equals(desc)) {
                isCorrectDescription = true;
                break;
            }
        }

        for (ProductDescription productDescription : productDescriptionList) {
            if (productDescription.getsKU().equals(sku)) {
                isCorrectSKU = true;
                break;
            }
        }
        if (productCategory.getCatCode().equals("0")) {
            Toast.makeText(getActivity(), "Please select Product Category", Toast.LENGTH_SHORT).show();
            validate = false;
        } else if (productDivision.getDivCode().equals("0")) {
            Toast.makeText(getActivity(), "Please select Product Division", Toast.LENGTH_SHORT).show();
            validate = false;
        } else if (sku.isEmpty() || !isCorrectSKU) {
            Toast.makeText(getActivity(), "Please select correct Product SKU", Toast.LENGTH_SHORT).show();
            validate = false;
        } else if (desc.isEmpty() || !isCorrectDescription) {
            Toast.makeText(getActivity(), "Please select correct Product Description", Toast.LENGTH_SHORT).show();
            validate = false;
        }
        return validate;
    }





    void refreshCartRecyclerFromPreference() {
        String strCartItem = SharedPreferencesUtil.getCartItems(getActivity());
        if (!strCartItem.equals("")) {
            CartItem cartObject = utility.convertJSONStringToCartObject(strCartItem);
            if (cartObject != null && cartObject.getItems() != null && cartObject.getItems().size() > 0) {
                CartItemsAdapter cartItemsAdapter = new CartItemsAdapter(getActivity(), cartObject, this,listenerMainActivity);
                recycler_items.setAdapter(cartItemsAdapter);
            } else {
                recycler_items.setAdapter(null);
            }
        } else {
            recycler_items.setAdapter(null);
        }
    }


    void dialogSaveTemplate(CartItem cartItems) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_template_name, null);
        dialogBuilder.setView(dialogView);

        Button buttonSaveTemplate = (Button) dialogView.findViewById(R.id.buttonSaveTemplate);
        EditText et_templateName = (EditText) dialogView.findViewById(R.id.et_templateName);


        buttonSaveTemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                if (et_templateName.getText().toString().trim().isEmpty()) {
                    utility.showToast(getActivity(), "Enter template name!");
                } else {
                    cartItems.setTemplateName(et_templateName.getText().toString().trim());
                    hitAPISaveTemplate(cartItems);
                }
            }
        });


        alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    public void showDialogSKU(String dialog_title, final Context context, final EditText editText) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_spinner_dialog, null);
        dialogBuilder.setView(dialogView);


        final EditText autoText = (EditText) dialogView.findViewById(R.id.autoText);
        final ImageView imgvwSearch = (ImageView) dialogView.findViewById(R.id.imgvwSearch);
        imgvwSearch.setVisibility(View.GONE);
        autoText.setHint(dialog_title);
        final ListView listvw = (ListView) dialogView.findViewById(R.id.listview);


        ArrayAdapter<ProductSKU> adapter = new ArrayAdapter<ProductSKU>(context,
                android.R.layout.simple_list_item_1, android.R.id.text1, productSKUList);
        listvw.setAdapter(adapter);
        autoText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                listvw.setAdapter(null);
                String Prefix = s.toString().trim();
                if (!Prefix.isEmpty()) {
                    hitAPIGetSKU(Prefix, DivisionCode);
                }

            }
        });
        listvw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                ShipToParty spinner_ = (ShipToParty) listvw.getItemAtPosition(position);
                String txt = spinner_.getColumnName();
                editText.setText(txt);


                try {
                    alertDialog.dismiss();
                } catch (Exception e) {
                    e.getMessage();
                }


            }
        });


        dialogBuilder.setTitle(dialog_title);

        alertDialog = dialogBuilder.create();
        if (!alertDialog.isShowing()) {
            alertDialog.show();
        }
    }

    @Override
    public void onCartRefresh() {

    }

    void setAutoCompleteSKUClickedFalse() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                autoCompleteSKU_ItemClicked = false;
            }
        }, 1000);

    }

    private void setAutoCompleteDescriptionClickedFalse() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                autoCompleteDescription_ItemClicked = false;
            }
        }, 1000);
    }

    private void setAPICallInProgress() {
        APICallInProgress = true;
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                APICallInProgress = false;
            }
        }, 2000);
    }
}
