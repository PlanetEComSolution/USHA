package dpusha.app.com.usha.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dpusha.app.com.usha.R;
import dpusha.app.com.usha.activity.DrawerMainActivity;
import dpusha.app.com.usha.adapter.LinksAdapter;
import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.model.ContactResponse;
import dpusha.app.com.usha.model.LinkData;
import dpusha.app.com.usha.network.APIError;
import dpusha.app.com.usha.network.RequestListener;
import dpusha.app.com.usha.network.RetrofitManager;
import dpusha.app.com.usha.orders_home.util.Constants;
import dpusha.app.com.usha.shared_preference.SharedPreferencesUtil;
import helper.ApiClient;
import helper.ApiService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;


public class ContactUsFragment extends Fragment implements RequestListener {
    View view;

    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.ro_address)
    TextView ro_address;
    @BindView(R.id.cin_details)
    TextView cin;
    @BindView(R.id.contact_num)
    TextView contactNum1;
    @BindView(R.id.contact_num2)

    TextView contactNum2;
    @BindView(R.id.email_details)
    TextView email;
    @BindView(R.id.grev_office_details)
    TextView grevOffice;
    String token;
    private FragmentActivity activity;
    private MainListner listenerMainActivity;

    private RetrofitManager retrofitManager = RetrofitManager.getInstance();

    public ContactUsFragment() {
        // Required empty public constructor
    }

    public static ContactUsFragment newInstance() {

        return new ContactUsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        ButterKnife.bind(this, view);
        //hitApiContactUs();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (activity == null) {
            activity = getActivity();
            listenerMainActivity = (MainListner) activity;
        }
        hitApiContactUs();
        //setViewListener();
    }



    private void hitApiContactUs() {
        token = SharedPreferencesUtil.getAuthToken(getActivity());
        retrofitManager.getContactUsDetails(this, getActivity(), Constants.API_TYPE.CONTACTUS, token, true);
    }
       /* private void getDetailsContact () {
            if (isConnectingToInternet()) {
                token = SharedPreferencesUtil.getAuthToken(getActivity());


            } else {
                Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_LONG).show();
            }

        }*/



    @Override
    public void onSuccess(Response<ResponseBody> response, Constants.API_TYPE apiType) {

        try {
            String strResponse = response.body().string();
            Log.e("menu", response.body().toString());
            // Toast.makeText(this, apiType+"Response  "+strResponse,Toast.LENGTH_SHORT).show();
            if (apiType == Constants.API_TYPE.CONTACTUS) {
                //usefulLinkList.clear();
                Gson gson = new Gson();
                Type listType = new TypeToken<ContactResponse>() {
                }.getType();
                ContactResponse mResponse = gson.fromJson(strResponse, listType);
                if (mResponse != null ) {
                 address.setText(mResponse.getHeadOfficeAddress());
                 ro_address.setText(mResponse.getRegisteredOffice());
                 cin.setText(mResponse.getCIN());
                 contactNum1.setText(mResponse.getContact1());
                 contactNum2.setText(mResponse.getContact2());
                 email.setText(mResponse.getEmail());
                 grevOffice.setText(mResponse.getGrievanceEmail());

                } else {
                    //recyclerView.setAdapter(null);
                    Toast.makeText(getActivity(), "No data found!", Toast.LENGTH_SHORT).show();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onFailure(Response<ResponseBody> response, Constants.API_TYPE apiType) {

    }

    @Override
    public void onApiException(APIError error, Response<ResponseBody> response, Constants.API_TYPE apiType) {

    }

    private boolean isConnectingToInternet() {
        ConnectivityManager connectivity = (ConnectivityManager) getActivity()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }

}