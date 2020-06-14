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
import dpusha.app.com.usha.adapter.CategoryAdapter;
import dpusha.app.com.usha.adapter.LinksAdapter;
import dpusha.app.com.usha.adapter.recycler_decorator.GridSpacingItemDecoration;
import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.model.LinkData;
import dpusha.app.com.usha.model.ProductCategory;
import dpusha.app.com.usha.network.APIError;
import dpusha.app.com.usha.network.RequestListener;
import dpusha.app.com.usha.network.RetrofitManager;
import dpusha.app.com.usha.orders_home.util.Constants;
import dpusha.app.com.usha.shared_preference.SharedPreferencesUtil;
import okhttp3.ResponseBody;
import retrofit2.Response;


public class UsefulLinkFragment extends Fragment implements RequestListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    View view;
    private FragmentActivity activity;
    private MainListner listenerMainActivity;
    private List<LinkData> usefulLinkList = new ArrayList<>();

    private RetrofitManager retrofitManager = RetrofitManager.getInstance();

    public UsefulLinkFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static UsefulLinkFragment newInstance() {
        /*UsefulLinkFragment fragment = new UsefulLinkFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;*/
        return new UsefulLinkFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* if (getArguments() != null) {

        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_useful_link, container, false);
        ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //   recycler_category.addItemDecoration(new ListSpacingDecoration(20));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2,10,true));
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
        hitApiUsefulLinks();
    }

    private void hitApiUsefulLinks() {

        retrofitManager.getUsefulLink(this, getActivity(), Constants.API_TYPE.USEFULLINKS, true);
        //Toast.makeText(getActivity(),"this is links",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess(Response<ResponseBody> response, Constants.API_TYPE apiType) {
        try {
            String strResponse = response.body().string();
            Log.e("menu", response.body().toString());
            // Toast.makeText(this, apiType+"Response  "+strResponse,Toast.LENGTH_SHORT).show();
            if (apiType == Constants.API_TYPE.USEFULLINKS) {
                usefulLinkList.clear();
                Gson gson = new Gson();
                Type listType = new TypeToken<List<LinkData>>() {
                }.getType();
                List<LinkData> linkData = gson.fromJson(strResponse, listType);
                usefulLinkList.addAll(linkData);
                if (linkData != null && !linkData.isEmpty()) {
                    LinksAdapter linksAdapter = new LinksAdapter(getActivity(), usefulLinkList,listenerMainActivity);
                    recyclerView.setAdapter(linksAdapter);
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