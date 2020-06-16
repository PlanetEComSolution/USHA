package dpusha.app.com.usha.fragment.download;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dpusha.app.com.usha.R;
import dpusha.app.com.usha.adapter.IdValueSpinnerAdapter;
import dpusha.app.com.usha.adapter.ProductCatalougeAdapter;
import dpusha.app.com.usha.adapter.SchemeByUserAdapter;
import dpusha.app.com.usha.adapter.recycler_decorator.GridSpacingItemDecoration;
import dpusha.app.com.usha.listeners.CartItemChangedListener;
import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.model.ProductCatalougeList;
import dpusha.app.com.usha.model.ProductCategory;
import dpusha.app.com.usha.model.ProductDivision;
import dpusha.app.com.usha.model.SchemeByUser;
import dpusha.app.com.usha.model.SchemeSpinnerResponse;
import dpusha.app.com.usha.network.APIError;
import dpusha.app.com.usha.network.RequestListener;
import dpusha.app.com.usha.network.RetrofitManager;
import dpusha.app.com.usha.orders_home.util.Constants;
import dpusha.app.com.usha.shared_preference.SharedPreferencesUtil;
import okhttp3.ResponseBody;
import retrofit2.Response;


public class SchemesFragment extends Fragment implements RequestListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.spinnerCategory)
    Spinner divisionSpinner;

    private List<SchemeSpinnerResponse> mList = new ArrayList<>();
    private List<ProductCategory> productCategoryList = new ArrayList<>();

    View view;
    private FragmentActivity activity;
    private MainListner listenerMainActivity;
    private List<SchemeByUser> schemeList = new ArrayList<>();
    private IdValueSpinnerAdapter divisonAdapter;
    String label;
    String value;
    long spinnerId;
    private String CategoryCode = "", DivisionCode = "";
    String itemCode;
    private RetrofitManager retrofitManager = RetrofitManager.getInstance();

    public SchemesFragment() {
        // Required empty public constructor
    }


    public static SchemesFragment newInstance() {
        return new SchemesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_schemes, container, false);
        ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));
        //   recycler_category.addItemDecoration(new ListSpacingDecoration(20));
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2,10,true));
        //hitApiUsefulLinks();

        //test

        //mList.add(new SchemeSpinnerResponse("0", "Select"));


        //test en

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (activity == null) {
            activity = getActivity();
            listenerMainActivity = (MainListner) activity;
        }
        hitApiDownloadScheme();
        hitApiGetDivision();
        setViewListener();

    }

    private void hitApiDownloadScheme() {
        String token = SharedPreferencesUtil.getAuthToken(getActivity());
        retrofitManager.getSchemeList(this, getActivity(), Constants.API_TYPE.DOWNLOADSCHEME, token, true);
        //Toast.makeText(getActivity(),"this is links",Toast.LENGTH_LONG).show();
    }

    void setTemporaryAdapterForDivsionSpinner() {
        mList.clear();
        mList.add(new SchemeSpinnerResponse("0", "Select Division"));
        ArrayAdapter<SchemeSpinnerResponse> dataAdapter1 = new ArrayAdapter<SchemeSpinnerResponse>(getActivity(), android.R.layout.simple_spinner_item, mList);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        divisionSpinner.setAdapter(dataAdapter1);

    }


    void setViewListener() {
        setTemporaryAdapterForDivsionSpinner();

        divisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //oast.makeText(getActivity(),"clicked",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Toast.makeText(getActivity(),"Failed clicked...",Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onSuccess(Response<ResponseBody> response, Constants.API_TYPE apiType) {
        try {
            String strResponse = response.body().string();
            Log.e("menu", response.body().toString());
            // Toast.makeText(this, apiType+"Response  "+strResponse,Toast.LENGTH_SHORT).show();
            if (apiType == Constants.API_TYPE.DOWNLOADSCHEME) {
                schemeList.clear();
                Gson gson = new Gson();
                Type listType = new TypeToken<List<SchemeByUser>>() {
                }.getType();
                List<SchemeByUser> mList = gson.fromJson(strResponse, listType);
                schemeList.addAll(mList);
                if (mList != null && !mList.isEmpty()) {
                    SchemeByUserAdapter schemeByUserAdapter = new SchemeByUserAdapter(getActivity(), schemeList, listenerMainActivity);
                    recyclerView.setAdapter(schemeByUserAdapter);
                } else {
                    recyclerView.setAdapter(null);
                    Toast.makeText(getActivity(), "No data found!", Toast.LENGTH_SHORT).show();
                }
            } else if (apiType == Constants.API_TYPE.DOWNLOADDIVISION) {
                mList.clear();
                Gson gson = new Gson();
                Type listType = new TypeToken<List<SchemeSpinnerResponse>>() {

                }.getType();

                List<SchemeSpinnerResponse> divisionList = gson.fromJson(strResponse, listType);
                mList.add(new SchemeSpinnerResponse("0", "Select Division"));
                mList.addAll(divisionList);
                if (divisionList != null && !divisionList.isEmpty()) {
                    ArrayAdapter<SchemeSpinnerResponse> dataAdapter = new ArrayAdapter<SchemeSpinnerResponse>(getActivity(), android.R.layout.simple_spinner_item, mList);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    divisionSpinner.setAdapter(dataAdapter);
                } else {
                    setTemporaryAdapterForDivsionSpinner();
                    Toast.makeText(getActivity(), "No data found!", Toast.LENGTH_SHORT).show();
                }
            }
            else if(apiType  == Constants.API_TYPE.DIVISIONSCHEME){

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

   /* void setTemporaryAdapterForCategorySpinner() {
        productCategoryList.clear();
        productCategoryList.add(new ProductCategory("0", "Select Category"));
        ArrayAdapter<ProductCategory> dataAdapter = new ArrayAdapter<ProductCategory>(getActivity(), android.R.layout.simple_spinner_item, productCategoryList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(dataAdapter);

    }*/



   /* void setTemporaryAdapterForCategorySpinner() {
        productDivisionList.clear();
        productDivisionList.add(new ProductDivision("0", "Select Division"));
        ArrayAdapter<ProductDivision> dataAdapter1 = new ArrayAdapter<ProductDivision>(getActivity(), android.R.layout.simple_spinner_item, productDivisionList);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerDivision.setAdapter(dataAdapter1);

    }*/


    private void hitApiGetDivision() {
        retrofitManager.getDivisionDownload(this, getActivity(), Constants.API_TYPE.DOWNLOADDIVISION, true);

        //retrofitManager.getDivisionDownload(this, getActivity(), Constants.API_TYPE.DOWNLOADDIVISION, SharedPreferencesUtil.getUserId(getActivity()), true);
    }
}