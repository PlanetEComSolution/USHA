package dpusha.app.com.usha.fragment.cart;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import dpusha.app.com.usha.fragment.book_order.BookOrderHome;
import dpusha.app.com.usha.listeners.CartItemChangedListener;
import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.model.CartItem;
import dpusha.app.com.usha.model.GetPriceResponse;
import dpusha.app.com.usha.model.Item;
import dpusha.app.com.usha.model.Material;
import dpusha.app.com.usha.model.Result;
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

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaceOrder extends Fragment implements RequestListener, DatePickerDialog.OnDateSetListener, CartItemChangedListener {


    @BindView(R.id.recycler_items)
    RecyclerView recycler_items;

    @BindView(R.id.txtvw_totalNetPrice)
    TextView txtvw_totalNetPrice;
    @BindView(R.id.txtvw_totalDiscount)
    TextView txtvw_totalDiscount;
    @BindView(R.id.txtvw_taxableValue)
    TextView txtvw_taxableValue;
    @BindView(R.id.txtvw_totalTax)
    TextView txtvw_totalTax;
    @BindView(R.id.txtvw_totalGrossPrice)
    TextView txtvw_totalGrossPrice;

    @BindView(R.id.et_deliveryDate)
    EditText et_deliveryDate;
    @BindView(R.id.spinnerShipToParty)
    Spinner spinnerShipToParty;


    @BindView(R.id.button_updateOrder)
    Button button_updateOrder;

    @BindView(R.id.button_proceed)
    LinearLayout button_proceed;


    @BindView(R.id.et_referenceNumber)
    EditText et_referenceNumber;
    @BindView(R.id.et_remarks)
    EditText et_remarks;
    private MainListner listenerMainActivity;
    private FragmentActivity activity;
    private RetrofitManager retrofitManager = RetrofitManager.getInstance();
    CartItem cartItems;
    List<ShipToParty> shipToPartyList = new ArrayList<>();
    AlertDialog alertDialog1;
    boolean isOrderEditable = true;
    String ShipToPartyId = "";

    public PlaceOrder() {
        // Required empty public constructor
    }

    public static PlaceOrder newInstance() {
        return new PlaceOrder();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (activity == null) {
            activity = getActivity();
            listenerMainActivity = (MainListner) activity;
        }

        setViewListener();
        setTemporaryAdapterForPartySpinner();
        hitAPIGetShipToParty();
        checkArguments();
        // refreshCartRecyclerFromPreference();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.place_order, container, false);
        ButterKnife.bind(this, view);
        recycler_items.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler_items.setItemAnimator(new DefaultItemAnimator());
        // RecyclerViewMargin decoration = new RecyclerViewMargin(20, 1);
        MyDividerItemDecoration decoration1 = new MyDividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL, 0);
        recycler_items.addItemDecoration(decoration1);

        return view;
    }

    void checkArguments() {

        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.containsKey("ReferenceNum")) {
                String ReferenceNum = bundle.getString("ReferenceNum");
                et_referenceNumber.setText(ReferenceNum);
            }
            if (bundle.containsKey("Remarks")) {
                String Remarks = bundle.getString("Remarks");
                et_remarks.setText(Remarks);
            }
            if (bundle.containsKey("Party")) {
                ShipToPartyId = bundle.getString("Party");
            }
            if (bundle.containsKey("DeliveryDate")) {
                String DeliveryDate = bundle.getString("DeliveryDate");
                et_deliveryDate.setText(DeliveryDate);
            }


            if (bundle.containsKey(Constants.FROM_PLACED_ORDER)) {
                //listenerMainActivity.clearCart(getActivity());

                isOrderEditable = bundle.getBoolean("isOrderEditable", true);
                if (isOrderEditable) {
                    et_referenceNumber.setEnabled(false);
                    et_remarks.setEnabled(true);
                    spinnerShipToParty.setEnabled(true);
                    et_deliveryDate.setEnabled(true);
                    button_updateOrder.setVisibility(View.VISIBLE);
                    button_proceed.setVisibility(View.GONE);

                } else {
                    et_referenceNumber.setEnabled(false);
                    et_remarks.setEnabled(false);
                    spinnerShipToParty.setEnabled(false);
                    et_deliveryDate.setEnabled(false);
                    button_updateOrder.setVisibility(View.GONE);
                    button_proceed.setVisibility(View.GONE );
                }
                String id = getArguments().getString("id");
                getOrderListDetails(id);
            } else {
                refreshCartRecyclerFromPreference();
            }


        } else {
            refreshCartRecyclerFromPreference();

        }

    }

    private void setSpinnerShipToPartyFromPartyId() {
        for (int i = 0; i < shipToPartyList.size(); i++) {
            ShipToParty shipToParty = shipToPartyList.get(i);
            if (shipToParty.getColumnValue().equals(ShipToPartyId)) {
                spinnerShipToParty.setSelection(i);
                break;
            }
        }
    }

    private void setSpinnerShipToPartyFromPartyName(String PartyName) {
        for (int i = 0; i < shipToPartyList.size(); i++) {
            ShipToParty shipToParty = shipToPartyList.get(i);
            if (shipToParty.getColumnName().equals(PartyName)) {
                spinnerShipToParty.setSelection(i);
                break;
            }
        }
    }

    private void setOrderDetailFields(String PartyName, String OrderId, String DeliveryDate) {
        setSpinnerShipToPartyFromPartyName(PartyName);
        et_referenceNumber.setText(OrderId);
        if (DeliveryDate.contains("T")) {
            DeliveryDate = DeliveryDate.substring(0, DeliveryDate.indexOf("T"));
            DeliveryDate = utility.chageDateFormat("yyyy-MM-dd", DeliveryDate, "dd-MM-yyyy");
        }

        et_deliveryDate.setText(DeliveryDate);

    }


    void getPrice(CartItem cartItems) {
        List<String> sku = new ArrayList<>();
        List<Item> items = cartItems.getItems();
        for (int i = 0; i < items.size(); i++) {
            sku.add(items.get(i).getsKU());
        }
        Material material = new Material(SharedPreferencesUtil.getUserId(getActivity()), sku);
        hitAPIGetPrice(material);
    }

    void refreshCartRecyclerFromPreference() {
        String strCartItem = SharedPreferencesUtil.getCartItems(getActivity());
        if (!strCartItem.equals("")) {
            cartItems = utility.convertJSONStringToCartObject(strCartItem);
            if (cartItems != null && cartItems.getItems() != null && cartItems.getItems().size() > 0) {
                getPriceSample();
                // getPrice(cartItems);
                CartItemsAdapter cartItemsAdapter = new CartItemsAdapter(getActivity(), cartItems, this, listenerMainActivity, isOrderEditable);
                recycler_items.setAdapter(cartItemsAdapter);

            } else {
                recycler_items.setAdapter(null);
            }
        } else {
            recycler_items.setAdapter(null);
        }
        // refreshTotalAmount();
    }

    void setViewListener() {

    }

    private void hitAPIGetPrice(Material material) {
        retrofitManager.getPrice(this, getActivity(), Constants.API_TYPE.GET_PRICE, material, true);
    }

    private void hitAPIGetShipToParty() {
        retrofitManager.getShipToParty(this, getActivity(), Constants.API_TYPE.SHIP_TO_PARTY, true);
    }

    private void hitAPIPlaceOrder() {

        Log.e("Log_cartPlaceOrder", utility.convertCartToJSONString(cartItems));
        retrofitManager.placeOrder(this, getActivity(), Constants.API_TYPE.PLACE_ORDER, cartItems, true);
    }
    private void hitAPIUpdateOrder() {

        Log.e("Log_cartUpdateOrder", utility.convertCartToJSONString(cartItems));
        retrofitManager.updateOrder(this, getActivity(), Constants.API_TYPE.PLACE_ORDER, cartItems, true);
    }




    public void getOrderListDetails(String id) {
        retrofitManager.GetOrderListDetails(this, getContext(), Constants.API_TYPE.ORDER_LIST_DETAILS, id, true);
    }

    @OnClick({
            R.id.et_deliveryDate,
            R.id.button_proceed,
            R.id.button_updateOrder,

    })
    public void onClick(@NonNull View view) {

        switch (view.getId()) {
            case R.id.et_deliveryDate:
                Click_getDate();
                break;
            case R.id.button_proceed :
                if (validation()) {
                    hitAPIPlaceOrder();
                }
                break;
            case R.id.button_updateOrder:
                cartItems.setOrderId(et_referenceNumber.getText().toString().trim());
                if (validation()) {
                    hitAPIUpdateOrder();
                }
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

                shipToPartyList.add(new ShipToParty("Shipping Address", "0"));
                shipToPartyList.addAll(_shipToPartyList);

                if (_shipToPartyList != null && !_shipToPartyList.isEmpty()) {
                    ArrayAdapter<ShipToParty> dataAdapter = new ArrayAdapter<ShipToParty>(getActivity(), android.R.layout.simple_spinner_item, shipToPartyList);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerShipToParty.setAdapter(dataAdapter);
                } else {
                    setTemporaryAdapterForPartySpinner();
                    Toast.makeText(getActivity(), "No data found!", Toast.LENGTH_SHORT).show();
                }

                setSpinnerShipToPartyFromPartyId();
            } else if (apiType == Constants.API_TYPE.GET_PRICE) {
                GetPriceResponse priceResponse = new Gson().fromJson(strResponse, GetPriceResponse.class);
                if (priceResponse != null && priceResponse.getIsSuccess() && priceResponse.getResult() != null && priceResponse.getResult().size() > 0) {
                    List<Result> priceList = priceResponse.getResult();
                    for (int i = 0; i < priceList.size(); i++) {
                        if (!priceList.get(i).getMaterialCode().equals("")) continue;
                        for (int j = 0; j < cartItems.getItems().size(); j++) {
                            if (priceList.get(i).getMaterialCode().equals(cartItems.getItems().get(i).getsKU())) {
                                cartItems.getItems().get(i).setUnitPrice(priceList.get(i).getPriceBeforeDiscount());
                                cartItems.getItems().get(i).setDiscount(priceList.get(i).getBillDiscount());
                                cartItems.getItems().get(i).setTaxPercent(priceList.get(i).gettAX());
                            }
                        }
                    }
                    refreshTotalAmount();

                }


            } else if (apiType == Constants.API_TYPE.PLACE_ORDER) {

                Toast.makeText(getActivity(), strResponse, Toast.LENGTH_SHORT).show();
                listenerMainActivity.clearCart(getActivity());
                try {
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    FragmentTransaction trans = manager.beginTransaction();
                    trans.remove(this);
                    trans.commit();
                    manager.popBackStack();
                } catch (Exception e) {
                    e.getMessage();
                }
                listenerMainActivity.addFragment(new BookOrderHome(), "BookOrderHome", true);
            } else if (apiType == Constants.API_TYPE.ORDER_LIST_DETAILS) {
                GetDraft draft = new Gson().fromJson(strResponse, GetDraft.class);
                utility.saveDraftToCartPreference(draft, getActivity(), listenerMainActivity);
                refreshCartRecyclerFromPreference();
                setOrderDetailFields(String.valueOf(draft.getShipToParty()), draft.getOrderId(), draft.getRequestDeliveryDate());
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

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    private void getPriceSample() {
        for (int j = 0; j < cartItems.getItems().size(); j++) {
            cartItems.getItems().get(j).setUnitPrice(100.0);
            cartItems.getItems().get(j).setDiscount(20.0);
            cartItems.getItems().get(j).setTaxPercent(18.0);
        }
        refreshTotalAmount();
    }

    private void refreshTotalAmount() {
        double totalNetPrice = 0.0;
        double totalDiscount = 0.0;
        double totalTax = 0.0;
        double totalTaxableValue = 0.0;
        double totalGrossPrice = 0.0;
        for (int j = 0; j < cartItems.getItems().size(); j++) {
            try {
                double unitPrice = cartItems.getItems().get(j).getUnitPrice();
                // double unitPrice = cartItems.getItems().get(j).getUnitPrice() * cartItems.getItems().get(j).getQuantity();
                double _discount = cartItems.getItems().get(j).getDiscount();
                double taxPercent = cartItems.getItems().get(j).getTaxPercent();
                double quantity = cartItems.getItems().get(j).getQuantity();
                // double discount = (totalNetPrice * _discount) / 100;
                double discount = _discount;

                totalDiscount = totalDiscount + (discount * quantity);
                totalNetPrice = totalNetPrice + (unitPrice * quantity);

                double _taxableValue = (unitPrice - discount) * quantity;
                totalTaxableValue = totalTaxableValue + _taxableValue;

                double tax = (_taxableValue * taxPercent) / 100;
                totalTax = totalTax + tax;

                double priceAfterTax = _taxableValue + tax;
                totalGrossPrice = totalGrossPrice + priceAfterTax;


            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
            }
        }

        txtvw_totalNetPrice.setText(String.format("%.2f", totalNetPrice));
        txtvw_totalDiscount.setText(String.format("%.2f", totalDiscount));
        txtvw_taxableValue.setText(String.format("%.2f", totalTaxableValue));
        txtvw_totalTax.setText(String.format("%.2f", totalTax));
        txtvw_totalGrossPrice.setText(String.format("%.2f", totalGrossPrice));//String.valueOf(totalGrossPrice));


        cartItems.setTotalNetPrice(totalNetPrice);
        cartItems.setTotalDiscounts(totalDiscount);
        cartItems.setTaxableValue(totalTaxableValue);
        cartItems.setTotalTax(totalTax);
        cartItems.setTotalGrossPrice(totalGrossPrice);
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
                PlaceOrder.this,
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

    public boolean validation() {
        boolean validate = true;
        ShipToParty _shipToParty = (ShipToParty) spinnerShipToParty.getSelectedItem();
        String shipToParty = _shipToParty.getColumnValue();
        // String shipToParty = etShipToParty.getText().toString().trim();
        String date = et_deliveryDate.getText().toString().trim();
        date=utility.chageDateFormat("dd-MM-yyyy",date,"yyyy-MM-dd");
      //  cartItems.setReferenceNo(et_referenceNumber.getText().toString().trim());
        cartItems.setShipToPartyId(shipToParty);
        cartItems.setRequestDeliveryDate(date);

        cartItems.setReferenceNo(cartItems.getOrderId());
        cartItems.setOrderStatus("Created");



        //cartItems.setremarks

        if (cartItems == null || cartItems.getItems() == null || cartItems.getItems().size() == 0) {
            Toast.makeText(getActivity(), "Cart is empty!", Toast.LENGTH_SHORT).show();
            validate = false;
        }
        // else if (shipToParty.isEmpty() ) {
        else if (shipToParty.equals("0")) {
            Toast.makeText(getActivity(), "Please select shipping address!", Toast.LENGTH_SHORT).show();
            validate = false;
        } else if (date.isEmpty()) {
            Toast.makeText(getActivity(), "Please select delivery date!", Toast.LENGTH_SHORT).show();
            validate = false;
        }


        return validate;
    }

    void setTemporaryAdapterForPartySpinner() {
        shipToPartyList.clear();
        shipToPartyList.add(new ShipToParty("Shipping Address", "0"));
        ArrayAdapter<ShipToParty> dataAdapter = new ArrayAdapter<ShipToParty>(getActivity(), android.R.layout.simple_spinner_item, shipToPartyList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerShipToParty.setAdapter(dataAdapter);
    }

    @Override
    public void onCartRefresh(int CartSize) {
        refreshTotalAmount();
    }


}
