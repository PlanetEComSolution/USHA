package dpusha.app.com.usha.fragment.cart;


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
import android.widget.Spinner;
import android.widget.TextView;
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
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dpusha.app.com.usha.R;
import dpusha.app.com.usha.adapter.CartItemsAdapter;
import dpusha.app.com.usha.adapter.recycler_decorator.MyDividerItemDecoration;
import dpusha.app.com.usha.listeners.CartItemChangedListener;
import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.model.CartItem;
import dpusha.app.com.usha.model.ProductDescription;
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
public class CartSummary extends Fragment implements RequestListener, CartItemChangedListener, DatePickerDialog.OnDateSetListener {

    @BindView(R.id.recycler_items)
    RecyclerView recycler_items;

    @BindView(R.id.et_deliveryDate)
    EditText et_deliveryDate;
    @BindView(R.id.spinnerShipToParty)
    Spinner spinnerShipToParty;

    @BindView(R.id.et_referenceNumber)
    EditText et_referenceNumber;
    @BindView(R.id.et_remarks)
    EditText et_remarks;


    @BindView(R.id.txtvw_noItems)
    TextView txtvw_noItems;


    private RetrofitManager retrofitManager = RetrofitManager.getInstance();
    List<ShipToParty> shipToPartyList = new ArrayList<>();
    private MainListner listenerMainActivity;
    private FragmentActivity activity;
    AlertDialog alertDialog;


    public CartSummary() {
        // Required empty public constructor
    }

    public static CartSummary newInstance() {
        return new CartSummary();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.cart_summary, container, false);
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

        hitAPIGetShipToParty();

    }

    @Override
    public void onResume() {
        super.onResume();
        refreshCartRecyclerFromPreference();
    }

    private void hitAPIGetShipToParty() {
        retrofitManager.getShipToParty(this, getActivity(), Constants.API_TYPE.SHIP_TO_PARTY, true);
    }

    private void hitAPISaveDraft(CartItem cartItems) {
        retrofitManager.saveDraft(this, getActivity(), Constants.API_TYPE.SAVE_DRAFT, cartItems, true);
    }

    private void hitAPISaveTemplate(CartItem cartItems) {
        retrofitManager.saveTemplate(this, getActivity(), Constants.API_TYPE.SAVE_TEMPLATE, cartItems, true);
    }


    @OnClick({

            R.id.button_proceed,
            R.id.button_emptyCart,
            R.id.button_saveAsDraft,
            R.id.button_saveAsTemplate,
            R.id.et_deliveryDate,
    })
    public void onClick(@NonNull View view) {

        switch (view.getId()) {

            case R.id.button_proceed:
                CartItem cartObject3 = utility.getCartFromPreference(getActivity());
                if (cartObject3 != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("ReferenceNum", et_referenceNumber.getText().toString().trim());
                    bundle.putString("Remarks", et_remarks.getText().toString().trim());
                    bundle.putString("Party", ((ShipToParty) spinnerShipToParty.getSelectedItem()).getColumnValue());
                    bundle.putString("DeliveryDate", et_deliveryDate.getText().toString().trim());
                    Fragment fragment = new PlaceOrder();
                    fragment.setArguments(bundle);
                    listenerMainActivity.addFragment(fragment, "PlaceOrder", true);
                } else {
                    utility.showToast(getActivity(), "Cart is empty!");
                }


                break;
            case R.id.button_emptyCart:
                listenerMainActivity.clearCart(getActivity());
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
            case R.id.et_deliveryDate:

                Click_getDate();

                break;
        }
    }

    @Override
    public void onSuccess(Response<ResponseBody> response, Constants.API_TYPE apiType) {
        try {
            String strResponse = response.body().string();
            Log.e("menu", response.body().toString());
            // Toast.makeText(this, apiType+"Response  "+strResponse,Toast.LENGTH_SHORT).show();
            if (apiType == Constants.API_TYPE.SHIP_TO_PARTY) {
                shipToPartyList.clear();
                Gson gson = new Gson();
                Type listType = new TypeToken<List<ShipToParty>>() {
                }.getType();
                List<ShipToParty> _shipToPartyList = gson.fromJson(strResponse, listType);

                shipToPartyList.add(new ShipToParty("Select Party", "0"));
                shipToPartyList.addAll(_shipToPartyList);

                if (_shipToPartyList != null && !_shipToPartyList.isEmpty()) {
                    ArrayAdapter<ShipToParty> dataAdapter = new ArrayAdapter<ShipToParty>(getActivity(), android.R.layout.simple_spinner_item, shipToPartyList);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerShipToParty.setAdapter(dataAdapter);
                } else {
                    setTemporaryAdapterForPartySpinner();
                    Toast.makeText(getActivity(), "No data found!", Toast.LENGTH_SHORT).show();
                }
            }
            if (apiType == Constants.API_TYPE.SAVE_DRAFT) {

                Toast.makeText(getActivity(), strResponse, Toast.LENGTH_SHORT).show();
            } else if (apiType == Constants.API_TYPE.SAVE_TEMPLATE) {

                Toast.makeText(getActivity(), strResponse, Toast.LENGTH_SHORT).show();
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
        String strCartItem = SharedPreferencesUtil.getCartItems(getActivity());
        if (!strCartItem.equals("")) {
            CartItem cartObject = utility.convertJSONStringToCartObject(strCartItem);
            if (cartObject != null && cartObject.getItems() != null && cartObject.getItems().size() > 0) {
                CartItemsAdapter cartItemsAdapter = new CartItemsAdapter(getActivity(), cartObject, this, listenerMainActivity, true);
                recycler_items.setAdapter(cartItemsAdapter);
            } else {
                recycler_items.setAdapter(null);
            }
        }

        return validate;
    }


    void refreshCartRecyclerFromPreference() {
        String strCartItem = SharedPreferencesUtil.getCartItems(getActivity());
        if (!strCartItem.equals("")) {
            CartItem cartObject = utility.convertJSONStringToCartObject(strCartItem);
            if (cartObject != null && cartObject.getItems() != null && cartObject.getItems().size() > 0) {
                CartItemsAdapter cartItemsAdapter = new CartItemsAdapter(getActivity(), cartObject, this, listenerMainActivity, true);
                recycler_items.setAdapter(cartItemsAdapter);
                txtvw_noItems.setVisibility(View.GONE);
            } else {
                recycler_items.setAdapter(null);
                txtvw_noItems.setVisibility(View.VISIBLE);
            }
        } else {
            recycler_items.setAdapter(null);
            txtvw_noItems.setVisibility(View.VISIBLE);
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


    @Override
    public void onCartRefresh(int CartSize) {
        if (CartSize == 0) {
         txtvw_noItems.setVisibility(View.VISIBLE);
        }else {
            txtvw_noItems.setVisibility(View.GONE);
        }
    }


    void setTemporaryAdapterForPartySpinner() {
        shipToPartyList.clear();
        shipToPartyList.add(new ShipToParty("Select Party", "0"));
        ArrayAdapter<ShipToParty> dataAdapter = new ArrayAdapter<ShipToParty>(getActivity(), android.R.layout.simple_spinner_item, shipToPartyList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerShipToParty.setAdapter(dataAdapter);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = +dayOfMonth + "/" + (++monthOfYear) + "/" + year;

        String dayOfMonthString = dayOfMonth < 10 ? "0" + dayOfMonth : "" + dayOfMonth;
        String monthOfYearString = monthOfYear < 10 ? "0" + monthOfYear : "" + monthOfYear;

        // String  date_1 = year + "-" + monthOfYearString + "-" + dayOfMonthString;
        String date_1 = dayOfMonthString + "-" + monthOfYearString + "-" + year;

        et_deliveryDate.setText(date_1);

    }

    private void Click_getDate() {

        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                CartSummary.this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dpd.setThemeDark(false);
        dpd.vibrate(false);
        dpd.dismissOnPause(false);
        dpd.showYearPickerFirst(false);
        dpd.setVersion(DatePickerDialog.Version.VERSION_1);
        dpd.setAccentColor(getResources().getColor(R.color.colorAccent));


        dpd.setTitle("Delivery Date");
        dpd.setYearRange(1985, 2028);
        dpd.setMinDate(calendar);
        dpd.show(getActivity().getFragmentManager(), "dialog");


    }
}
