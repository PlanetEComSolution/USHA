package dpusha.app.com.usha.fragment.book_order.by_template;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.whiteelephant.monthpicker.MonthPickerDialog;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dpusha.app.com.usha.R;
import dpusha.app.com.usha.adapter.CategoryAdapter;
import dpusha.app.com.usha.adapter.TemplateAdapter;
import dpusha.app.com.usha.adapter.recycler_decorator.ListSpacingDecoration;
import dpusha.app.com.usha.adapter.recycler_decorator.MyDividerItemDecoration;
import dpusha.app.com.usha.adapter.recycler_decorator.RecyclerViewMargin;
import dpusha.app.com.usha.adapter.recycler_decorator.SpacesItemDecoration;
import dpusha.app.com.usha.fragment.cart.CartFragment;
import dpusha.app.com.usha.listeners.BookOrderByTemplateListener;
import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.model.AuthToken;
import dpusha.app.com.usha.model.CartItem;
import dpusha.app.com.usha.model.ProductCategory;
import dpusha.app.com.usha.model.ProductDivision;
import dpusha.app.com.usha.model.Template;
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
public class OrderByTemplate extends Fragment implements RequestListener, BookOrderByTemplateListener {

    @BindView(R.id.recycler_template)
    RecyclerView recycler_template;

    @BindView(R.id.spinnerTemplate)
    Spinner spinnerTemplate;

    @BindView(R.id.et_month)
    EditText et_month;

    @BindView(R.id.et_year)
    EditText et_year;

    @BindView(R.id.txtvw_noItems)
    TextView txtvw_noItems;



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


        recycler_template.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler_template.setItemAnimator(new DefaultItemAnimator());
        RecyclerViewMargin decoration = new RecyclerViewMargin(20, 1);
        recycler_template.addItemDecoration(decoration);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (activity == null) {
            activity = getActivity();
            listenerMainActivity = (MainListner) activity;
        }
        hitAPIGetTemplate();
        setViewListener();
    }


    @OnClick({
            R.id.et_month,
            R.id.et_year,

    })
    public void onClick(@NonNull View view) {

        switch (view.getId()) {
            case R.id.et_month:
                chooseMonthOnly();

                break;
            case R.id.et_year:

                chooseYearOnly();

                break;

        }
    }

    void setViewListener() {
        spinnerTemplate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                et_month.setText("");
                et_year.setText("");
                Template item = (Template) parent.getItemAtPosition(position);
                int TemplateId = item.getId();
                filterByTemplateId(TemplateId);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void hitAPIGetTemplate() {

        retrofitManager.getTemplate(this, getActivity(), Constants.API_TYPE.TEMPLATE, true);
    }

    @Override
    public void onBookOrderClick(String id) {
        hitAPIGetTemplateDetails(id);
    }

    @Override
    public void onDeleteTemplateClick(String id) {
        hitAPIDeleteTemplate(id);
    }

    private void hitAPIGetTemplateDetails(String TemplateId) {

        retrofitManager.getTemplateDetailsByTemplateId(this, getActivity(), Constants.API_TYPE.TEMPLATE_DETAILS, TemplateId, true);
    }
    private void hitAPIDeleteTemplate(String TemplateId) {

        retrofitManager.deleteTemplate(this, getActivity(), Constants.API_TYPE.DELETE_TEMPLATE, TemplateId, true);
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
                templateList = gson.fromJson(strResponse, listType);
                List<Template> _templateList = new ArrayList<>();
                _templateList.add(new Template("All", 0));
                _templateList.addAll(templateList);
                if (templateList != null && !templateList.isEmpty()) {
                    TemplateAdapter templateAdapter = new TemplateAdapter(getActivity(), templateList,this);
                    recycler_template.setAdapter(templateAdapter);
                    ArrayAdapter<Template> dataAdapter = new ArrayAdapter<Template>(getActivity(), android.R.layout.simple_spinner_item, _templateList);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerTemplate.setAdapter(dataAdapter);
                    txtvw_noItems.setVisibility(View.GONE);
                } else {
                    setTemporaryAdapterForTemplateSpinner();
                    recycler_template.setAdapter(null);
                    txtvw_noItems.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), "No data found!", Toast.LENGTH_SHORT).show();
                }
            } else if (apiType == Constants.API_TYPE.TEMPLATE_DETAILS) {
                Template template = new Gson().fromJson(strResponse, Template.class);
                //SharedPreferencesUtil.clearCartItems(getActivity(),listenerMainActivity);
                listenerMainActivity.clearCart(getActivity());
                utility.saveTemplateToCartPreference(template, getActivity(),listenerMainActivity);
                listenerMainActivity.addFragment(new CartFragment(), "CartFragment", true);

            }
            else if (apiType == Constants.API_TYPE.DELETE_TEMPLATE) {
                Toast.makeText(getActivity(), strResponse,Toast.LENGTH_SHORT).show();
                hitAPIGetTemplate();
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


    void setTemporaryAdapterForTemplateSpinner() {
        List<Template> _templateList = new ArrayList<>();
        _templateList.add(new Template("All", 0));
        ArrayAdapter<Template> dataAdapter1 = new ArrayAdapter<Template>(getActivity(), android.R.layout.simple_spinner_item, _templateList);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTemplate.setAdapter(dataAdapter1);

    }


    private void chooseMonthOnly() {
        Calendar calendar = Calendar.getInstance();

        MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(getActivity(), new MonthPickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(int selectedMonth, int selectedYear) {
                et_month.setText(utility.formatMonth(String.valueOf(selectedMonth + 1)));
                filterByMonth(selectedMonth + 1);
            }
        }, /* activated number in year */ calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));

        builder.showMonthOnly()
                //  .setMinMonth(Calendar.FEBRUARY)
                .setTitle("Select Month")
                .build()
                .show();

    }

    private void chooseYearOnly() {
        Calendar calendar = Calendar.getInstance();
        MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(getActivity(), new MonthPickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(int selectedMonth, int selectedYear) {
                et_year.setText(Integer.toString(selectedYear));
                filterByYear(selectedYear);
            }
        }, calendar.get(Calendar.YEAR), 0);

        builder.showYearOnly()
                .setYearRange(1990, 2030)
                // .setMinYear(1990)
                .setTitle("Select Year")
                .build()
                .show();

    }


    void filterByTemplateId(int TemplateId) {


        if (TemplateId == 0) {
            TemplateAdapter categoryAdapter = new TemplateAdapter(getActivity(), templateList,this);
            recycler_template.setAdapter(categoryAdapter);
        } else {
            List<Template> templateList1 = new ArrayList<>();
            for (Template template : templateList) {
                if (template.getId() == TemplateId) {
                    templateList1.add(template);
                    break;
                }
            }
            TemplateAdapter categoryAdapter = new TemplateAdapter(getActivity(), templateList1,this);
            recycler_template.setAdapter(categoryAdapter);

        }
    }

    void filterByYear(int selectedYear) {
        List<Template> templateList1 = new ArrayList<>();
        for (int i = 0; i < templateList.size(); i++) {
            Template template = templateList.get(i);
            String date = template.getCreatedDate();
            String year = "";
            if (date.contains("T")) {
                date = date.substring(0, date.indexOf("T"));
                year = utility.chageDateFormat("yyyy-MM-dd", date, "yyyy");
            }
            if (String.valueOf(selectedYear).equals(year)) {
                templateList1.add(template);
            }
        }
        TemplateAdapter categoryAdapter = new TemplateAdapter(getActivity(), templateList1,this);
        recycler_template.setAdapter(categoryAdapter);


    }

    void filterByMonth(int selectedMonth) {
        List<Template> templateList1 = new ArrayList<>();
        for (int i = 0; i < templateList.size(); i++) {
            Template template = templateList.get(i);
            String date = template.getCreatedDate();
            int month = 0;
            if (date.contains("T")) {
                date = date.substring(0, date.indexOf("T"));
                String str_month = utility.chageDateFormat("yyyy-MM-dd", date, "MM");
                month = Integer.parseInt(str_month);
            }
            if (selectedMonth == month) {
                templateList1.add(template);
            }
        }
        TemplateAdapter categoryAdapter = new TemplateAdapter(getActivity(), templateList1,this);
        recycler_template.setAdapter(categoryAdapter);

    }
}
