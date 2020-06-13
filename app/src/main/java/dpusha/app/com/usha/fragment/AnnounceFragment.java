package dpusha.app.com.usha.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import dpusha.app.com.usha.adapter.AnnouncementAdapter;
import dpusha.app.com.usha.adapter.ProductCatalougeAdapter;
import dpusha.app.com.usha.adapter.recycler_decorator.GridSpacingItemDecoration;
import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.model.AnnouncementList;
import dpusha.app.com.usha.model.ProductCatalougeList;
import dpusha.app.com.usha.network.APIError;
import dpusha.app.com.usha.network.RequestListener;
import dpusha.app.com.usha.network.RetrofitManager;
import dpusha.app.com.usha.orders_home.util.Constants;
import dpusha.app.com.usha.shared_preference.SharedPreferencesUtil;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class AnnounceFragment extends Fragment implements RequestListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    View view;
    private FragmentActivity activity;
    private MainListner listenerMainActivity;
    private List<AnnouncementList> announcementLists = new ArrayList<>();

    private RetrofitManager retrofitManager = RetrofitManager.getInstance();

    public AnnounceFragment() {
        // Required empty public constructor
    }


    public static AnnounceFragment newInstance() {
      return new AnnounceFragment();
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
        view= inflater.inflate(R.layout.fragment_announce, container, false);
        ButterKnife.bind(this, view);
        //recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //   recycler_category.addItemDecoration(new ListSpacingDecoration(20));
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2,10,true));
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
        hitApiAnnouncement();
    }

    private void hitApiAnnouncement() {
       String token = SharedPreferencesUtil.getAuthToken(getActivity());

        retrofitManager.getAnnouncementDetails(this, getActivity(), Constants.API_TYPE.ANNOUNCEMENT, token,true);
        //Toast.makeText(getActivity(),"this is links",Toast.LENGTH_LONG).show();
    }




    @Override
    public void onSuccess(Response<ResponseBody> response, Constants.API_TYPE apiType) {
        try {
            String strResponse = response.body().string();
            Log.e("menu", response.body().toString());
            // Toast.makeText(this, apiType+"Response  "+strResponse,Toast.LENGTH_SHORT).show();
            if (apiType == Constants.API_TYPE.ANNOUNCEMENT) {
                announcementLists.clear();
                Gson gson = new Gson();
                Type listType = new TypeToken<List<AnnouncementList>>() {
                }.getType();
                List<AnnouncementList> mList = gson.fromJson(strResponse, listType);
                announcementLists.addAll(mList);
                if (mList != null && !mList.isEmpty()) {
                    AnnouncementAdapter announcementAdapter = new AnnouncementAdapter(getActivity(), announcementLists,listenerMainActivity);
                    recyclerView.setAdapter(announcementAdapter);
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