package dpusha.app.com.usha.fragment.book_order;


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
import dpusha.app.com.usha.fragment.book_order.by_cart.OrderByCart;
import dpusha.app.com.usha.fragment.book_order.by_category.OrderByItemCategory;
import dpusha.app.com.usha.fragment.book_order.by_itemcode.OrderByItemCode;
import dpusha.app.com.usha.fragment.book_order.by_template.OrderByTemplate;
//import dpusha.app.com.usha.fragment.cart.CartFragment;
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
public class BookOrderHome extends Fragment {


    private MainListner listenerMainActivity;
    private FragmentActivity activity;

    public BookOrderHome() {
        // Required empty public constructor
    }

    public static BookOrderHome newInstance() {
        return new BookOrderHome();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_book_order_home, container, false);
        ButterKnife.bind(this, view);

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



    @OnClick({
            R.id.rl_book_by_itemCode,
            R.id.rl_book_by_category,
            R.id.rl_book_by_cart,
            R.id.rl_book_by_template,

    })
    public void onClick(@NonNull View view) {

        switch (view.getId()) {
            case R.id.rl_book_by_itemCode:

                listenerMainActivity.addFragment(new OrderByItemCode(), "OrderByItemCodeFragment", true);
                break;
            case R.id.rl_book_by_category:
                listenerMainActivity. addFragment(new OrderByItemCategory(), "OrderByItemCategory", true);

                break;
            case R.id.rl_book_by_cart:
                listenerMainActivity.  addFragment(new OrderByCart(), "OrderByCart", true);
                break;
            case R.id.rl_book_by_template:

                listenerMainActivity.addFragment(new OrderByTemplate(), "OrderByTemplate", true);
                break;

        }
    }



}
