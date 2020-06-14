package dpusha.app.com.usha.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import dpusha.app.com.usha.adapter.ProductCatalougeAdapter;
import dpusha.app.com.usha.adapter.SocialNetworkingAdapter;
import dpusha.app.com.usha.adapter.recycler_decorator.GridSpacingItemDecoration;
import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.model.ProductCatalougeList;
import dpusha.app.com.usha.model.SocialNetworkingList;
import dpusha.app.com.usha.network.APIError;
import dpusha.app.com.usha.network.RequestListener;
import dpusha.app.com.usha.network.RetrofitManager;
import dpusha.app.com.usha.orders_home.util.Constants;
import okhttp3.ResponseBody;
import retrofit2.Response;


public class SocialNetworkingFragment extends Fragment implements RequestListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    View view;
    private FragmentActivity activity;
    private MainListner listenerMainActivity;
    private List<SocialNetworkingList> socialNetworkingLists = new ArrayList<>();
    private RetrofitManager retrofitManager = RetrofitManager.getInstance();


    public SocialNetworkingFragment() {
        // Required empty public constructor
    }


    public static SocialNetworkingFragment newInstance() {
       return new SocialNetworkingFragment();
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
        view= inflater.inflate(R.layout.fragment_social_networking, container, false);
        ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //   recycler_category.addItemDecoration(new ListSpacingDecoration(20));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2,14,true));
        //hitApiUsefulLinks();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (activity == null) {
            activity = getActivity();
            listenerMainActivity = (MainListner) activity;
        }
        hitApiSocialNetworking();
    }

    private void hitApiSocialNetworking() {

        retrofitManager.getSocialLinks(this, getActivity(), Constants.API_TYPE.USEFULLINKS, true);
        //Toast.makeText(getActivity(),"this is links",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess(Response<ResponseBody> response, Constants.API_TYPE apiType) {
        try {
            String strResponse = response.body().string();
            Log.e("menu", response.body().toString());
            // Toast.makeText(this, apiType+"Response  "+strResponse,Toast.LENGTH_SHORT).show();
            if (apiType == Constants.API_TYPE.USEFULLINKS) {
                socialNetworkingLists.clear();
                Gson gson = new Gson();
                Type listType = new TypeToken<List<SocialNetworkingList>>() {
                }.getType();
                List<SocialNetworkingList> mList = gson.fromJson(strResponse, listType);
                socialNetworkingLists.addAll(mList);
                if (mList != null && !mList.isEmpty()) {
                    SocialNetworkingAdapter socialNetworkingAdapter = new SocialNetworkingAdapter(getActivity(), socialNetworkingLists,listenerMainActivity);
                    recyclerView.setAdapter(socialNetworkingAdapter);
                } else {
                    recyclerView.setAdapter(null);
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
}