package dpusha.app.com.usha.fragment.feedback;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dpusha.app.com.usha.R;
import dpusha.app.com.usha.adapter.FeedbackListAdapter;
import dpusha.app.com.usha.adapter.recycler_decorator.MyDividerItemDecoration;
import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.model.DashboardData;
import dpusha.app.com.usha.model.Feedback;
import dpusha.app.com.usha.network.APIError;
import dpusha.app.com.usha.network.RequestListener;
import dpusha.app.com.usha.network.RetrofitManager;

import dpusha.app.com.usha.orders_home.model.OrderList;
import dpusha.app.com.usha.orders_home.util.Constants;
import okhttp3.ResponseBody;
import retrofit2.Response;

//import dpusha.app.com.usha.model.Dashboard;


public class FeedbackList extends Fragment implements RequestListener {
    View view;
    private FragmentActivity activity;
    private MainListner listenerMainActivity;
    private RetrofitManager retrofitManager = RetrofitManager.getInstance();


    @BindView(R.id.recycler_feedback)
    RecyclerView recycler_feedback;



    public FeedbackList() {
        // Required empty public constructor
    }

    public static FeedbackList newInstance() {

        return new FeedbackList();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.feedback_list, container, false);
        ButterKnife.bind(this, view);

        recycler_feedback.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recycler_feedback.setItemAnimator(new DefaultItemAnimator());
        MyDividerItemDecoration decoration1 = new MyDividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL, 0);
        recycler_feedback.addItemDecoration(decoration1);

        //FeedbackListAdapter

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (activity == null) {
            activity = getActivity();
            listenerMainActivity = (MainListner) activity;
        }




        hitApiGetFeedbackList();
        //setViewListener();


    }


    @OnClick({
            R.id.fab,


    })
    public void onClick(@NonNull View view) {

        switch (view.getId()) {
            case R.id.fab:

                listenerMainActivity.addFragment(new CreateFeedbackFragment(),"CreateFeedbackFragment",true);
                break;


        }
    }


    private void hitApiGetFeedbackList() {

        retrofitManager.getFeedback(this, getActivity(), Constants.API_TYPE.FEEDBACK, true);
    }



    @Override
    public void onSuccess(Response<ResponseBody> response, Constants.API_TYPE apiType) {

        try {
            String strResponse = response.body().string();
            Log.e("Res", response.body().toString());
            // Toast.makeText(this, apiType+"Response  "+strResponse,Toast.LENGTH_SHORT).show();
            if (apiType == Constants.API_TYPE.FEEDBACK) {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Feedback>>() {
                }.getType();
                List<Feedback> feedbackList = gson.fromJson(strResponse, listType);

                if(feedbackList!=null && !feedbackList.isEmpty()){
                    Collections.reverse(feedbackList);
                    FeedbackListAdapter feedbackListAdapter = new FeedbackListAdapter(getContext(),feedbackList,listenerMainActivity);
                    recycler_feedback.setAdapter(feedbackListAdapter);
                }else
                    Toast.makeText(getContext(),"Failed!",Toast.LENGTH_SHORT).show();
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



}