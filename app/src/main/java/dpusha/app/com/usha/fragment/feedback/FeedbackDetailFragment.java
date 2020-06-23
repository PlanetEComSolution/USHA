package dpusha.app.com.usha.fragment.feedback;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dpusha.app.com.usha.R;
import dpusha.app.com.usha.adapter.FeedbackImagesAdapter;
import dpusha.app.com.usha.adapter.FeedbackLogsAdapter;
import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.model.feedback_data.FeedbackDetail;
import dpusha.app.com.usha.model.feedback_data.FeedbackImage;
import dpusha.app.com.usha.model.feedback_data.FeedbackLog;
import dpusha.app.com.usha.network.APIError;
import dpusha.app.com.usha.network.RequestListener;
import dpusha.app.com.usha.network.RetrofitManager;
import dpusha.app.com.usha.orders_home.util.Constants;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

//import dpusha.app.com.usha.model.Dashboard;


public class FeedbackDetailFragment extends Fragment implements RequestListener {
    View view;
    private FragmentActivity activity;
    private MainListner listenerMainActivity;
    private RetrofitManager retrofitManager = RetrofitManager.getInstance();


    @BindView(R.id.recycler_feedback)
    RecyclerView recycler_feedback;

    @BindView(R.id.recycler_feedback_images)
    RecyclerView recycler_feedback_images;


    @BindView(R.id.txtvw_Location)
    TextView txtvw_Location;
    @BindView(R.id.txtvw_Department)
    TextView txtvw_Department;
    @BindView(R.id.txtvw_Category)
    TextView txtvw_Category;
    @BindView(R.id.txtvw_Class)
    TextView txtvw_Class;
    @BindView(R.id.txtvw_Title)
    TextView txtvw_Title;
    //@BindView(R.id.imgvw)
    // ImageView imgvw;
    @BindView(R.id.txtvw_Remarks)
    TextView txtvw_Remarks;


    @BindView(R.id.button_CloseFeedback)
    Button button_CloseFeedback;


    @BindView(R.id.button_ReOpenFeedback)
    Button button_ReOpenFeedback;


    String feedbackId = "",pendingWithUserCode="";

    public FeedbackDetailFragment() {
        // Required empty public constructor
    }

    public static FeedbackDetail newInstance() {

        return new FeedbackDetail();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @OnClick({
            R.id.button_ReOpenFeedback,
            R.id.button_CloseFeedback,
    })
    public void onClick(@NonNull View view) {
        switch (view.getId()) {
            case R.id.button_ReOpenFeedback:
               // hitApiChangeFeedbackStatus(feedbackId);
                hitApiChangeFeedbackStatusNew(feedbackId,"ReOpen",pendingWithUserCode);
                break;


            case R.id.button_CloseFeedback:
                //hitApiChangeFeedbackStatus(feedbackId);
                hitApiChangeFeedbackStatusNew(feedbackId,"Closed",pendingWithUserCode);
                break;

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.feedback_detail, container, false);
        ButterKnife.bind(this, view);

        recycler_feedback.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recycler_feedback.setItemAnimator(new DefaultItemAnimator());
        // MyDividerItemDecoration decoration1 = new MyDividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL, 0);
        //recycler_feedback.addItemDecoration(decoration1);


        recycler_feedback_images.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recycler_feedback_images.setItemAnimator(new DefaultItemAnimator());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (activity == null) {
            activity = getActivity();
            listenerMainActivity = (MainListner) activity;
        }

        Bundle bundle = getArguments();
        if (bundle != null) {
            feedbackId = bundle.getString("id");
            hitApiGetFeedbackDetail(feedbackId);
        }


        //setViewListener();


    }


    private void hitApiGetFeedbackDetail(String id) {
        retrofitManager.getFeedbackDetails(this, getActivity(), Constants.API_TYPE.FEEDBACK_DETAILS, id, true);
    }

    private void hitApiChangeFeedbackStatus(String id) {
        retrofitManager.changeFeedbackStatus(this, getActivity(), Constants.API_TYPE.CHANGE_FEEDBACK_STATUS, id, true);
    }
    private void hitApiChangeFeedbackStatusNew(String FeedbackId,String FeedbackStatus,String PendingWithUserCode) {
        retrofitManager.changeFeedbackStatusNew(this, getActivity(), Constants.API_TYPE.CHANGE_FEEDBACK_STATUS_NEW, FeedbackId,FeedbackStatus,PendingWithUserCode, true);
    }



    @Override
    public void onSuccess(Response<ResponseBody> response, Constants.API_TYPE apiType) {

        try {
            String strResponse = response.body().string();
            Log.e("Res", response.body().toString());
            // Toast.makeText(this, apiType+"Response  "+strResponse,Toast.LENGTH_SHORT).show();
            if (apiType == Constants.API_TYPE.FEEDBACK_DETAILS) {
                FeedbackDetail mResponse = new Gson().fromJson(strResponse, FeedbackDetail.class);
                if (mResponse != null) {
                    setFieldData(mResponse);
                } else {
                    //recyclerView.setAdapter(null);
                    Toast.makeText(getActivity(), "No data found!", Toast.LENGTH_SHORT).show();
                }
            } else if (apiType == Constants.API_TYPE.CHANGE_FEEDBACK_STATUS) {
                Toast.makeText(getActivity(), strResponse, Toast.LENGTH_SHORT).show();
            }
            else if (apiType == Constants.API_TYPE.CHANGE_FEEDBACK_STATUS_NEW) {
               // getActivity().getSupportFragmentManager().popBackStackImmediate();
                Toast.makeText(getActivity(), strResponse, Toast.LENGTH_SHORT).show();
                hitApiGetFeedbackDetail(feedbackId);
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

    private void setFieldData(FeedbackDetail fieldData) {

        if (fieldData.getCurrentFeedbackStatus().equals("New") || fieldData.getCurrentFeedbackStatus().equals("ReOpen")) {
            button_CloseFeedback.setVisibility(View.VISIBLE);
            button_ReOpenFeedback.setVisibility(View.GONE);
        } else if (fieldData.getCurrentFeedbackStatus().equals("Closed")) {
            button_CloseFeedback.setVisibility(View.GONE);
            button_ReOpenFeedback.setVisibility(View.VISIBLE);
        }
        pendingWithUserCode=fieldData.getPendingWithUserCode();

        if (fieldData.getLocationName() == null || fieldData.getLocationName().equals("")) {
            txtvw_Location.setText("N/A");
        } else {
            txtvw_Location.setText(fieldData.getLocationName());
        }


        if (fieldData.getDepartmentName() == null || fieldData.getDepartmentName().equals("")) {
            txtvw_Department.setText("N/A");
        } else {
            txtvw_Department.setText(fieldData.getDepartmentName());
        }

        if (fieldData.getCategoryName() == null || fieldData.getCategoryName().equals("")) {
            txtvw_Category.setText("N/A");
        } else {
            txtvw_Category.setText(fieldData.getCategoryName());
        }

        //  txtvw_Category.setText(fieldData.getCategoryName());

        txtvw_Class.setText(fieldData.getClassCode());

        txtvw_Title.setText(fieldData.getTitle());

        txtvw_Remarks.setText(fieldData.getRemarks());
        List<FeedbackLog> feedbackLogList = fieldData.getFeedbackLogs();
      //  Collections.reverse(feedbackLogList);
        if (feedbackLogList != null && feedbackLogList.size() > 0) {

            FeedbackLogsAdapter feedbackImagesAdapter = new FeedbackLogsAdapter(getActivity(), feedbackLogList, listenerMainActivity);
            recycler_feedback.setAdapter(feedbackImagesAdapter);
        }

        List<FeedbackImage> feedbackImageList = fieldData.getFeedbackImages();
        if (feedbackImageList != null && feedbackImageList.size() > 0) {
            FeedbackImagesAdapter feedbackImagesAdapter = new FeedbackImagesAdapter(getActivity(), feedbackImageList, listenerMainActivity);
            recycler_feedback_images.setAdapter(feedbackImagesAdapter);


        }

    }

}