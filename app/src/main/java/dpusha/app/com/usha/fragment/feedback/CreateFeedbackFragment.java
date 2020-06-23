package dpusha.app.com.usha.fragment.feedback;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gun0912.tedpicker.ImagePickerActivity;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dpusha.app.com.usha.R;
import dpusha.app.com.usha.adapter.CreateFeedbackImagesAdapter;
import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.model.AllCategory;
import dpusha.app.com.usha.model.AllClass;
import dpusha.app.com.usha.model.DepartmentDatum;
import dpusha.app.com.usha.model.LocationDatum;
import dpusha.app.com.usha.network.APIError;
import dpusha.app.com.usha.network.ProgressRequestBody;
import dpusha.app.com.usha.network.RequestListener;
import dpusha.app.com.usha.network.RetrofitManager;
import dpusha.app.com.usha.orders_home.util.Constants;
import dpusha.app.com.usha.orders_home.util.ProgressHUD;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Response;

//import dpusha.app.com.usha.model.Dashboard;


public class CreateFeedbackFragment extends Fragment implements RequestListener, ProgressRequestBody.UploadCallbacks {
    View view;
    private FragmentActivity activity;
    private MainListner listenerMainActivity;
    private RetrofitManager retrofitManager = RetrofitManager.getInstance();


    @BindView(R.id.et_location)
    AutoCompleteTextView et_location;


    @BindView(R.id.et_department)
    AutoCompleteTextView et_department;

    @BindView(R.id.et_title)
    EditText et_title;


    @BindView(R.id.et_remarks)
    EditText et_remarks;

   /* @BindView(R.id.et_images)
    EditText et_images;*/

    @BindView(R.id.spinnerCategory)
    Spinner spinnerCategory;

    @BindView(R.id.spinnerClass)
    Spinner spinnerClass;
    List<AllCategory> _allCategoryList = new ArrayList<>();
    @BindView(R.id.recycler_feedback_images)
    RecyclerView recycler_feedback_images;
    boolean APICallInProgress = false;
    private static final int INTENT_REQUEST_GET_IMAGES = 13;
    ArrayList<String> imagesList = new ArrayList<String>();

    int Count_Image_Uploaded = 0;

    ProgressHUD mProgressHUD;

    List<LocationDatum> locationDataList = new ArrayList<>();
    List<DepartmentDatum> departmentDataList = new ArrayList<>();

    String uploadedImages = "", title, remarks;
    LocationDatum locationData;
    DepartmentDatum departmentData;
    AllCategory _category;
    AllClass _class;


    public CreateFeedbackFragment() {
        // Required empty public constructor
    }

    public static CreateFeedbackFragment newInstance() {

        return new CreateFeedbackFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }


    @OnClick({
            R.id.button_addImages, R.id.button_save, R.id.button_cancel
            //, R.id.et_images,

    })
    public void onClick(@NonNull View view) {

        switch (view.getId()) {
           // case R.id.et_images:
            case R.id.button_addImages:
                requestPermission();
                break;


            case R.id.button_save:


                validateAndSaveFeedback();

                break;

            case R.id.button_cancel:
                getActivity().onBackPressed();
                break;

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.feedback_create, container, false);
        ButterKnife.bind(this, view);


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

        hitAPIGetAllCategory();
        setViewListener();


    }

    void setViewListener() {
        setTemporaryAdapterForCategorySpinner();
        setTemporaryAdapterForClassSpinner();


        et_location.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et_location.isPerformingCompletion()) {

                } else {

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (APICallInProgress) return;
                            String Prefix = et_location.getText().toString().trim();
                            if (!Prefix.isEmpty()) {
                                setAPICallInProgress();
                                hitAPIGetLocation(Prefix);
                            }
                        }
                    }, 1500);


                }


            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        et_department.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et_department.isPerformingCompletion()) {

                } else {
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (APICallInProgress) return;
                            String Prefix = et_department.getText().toString().trim();
                            if (!Prefix.isEmpty()) {
                                setAPICallInProgress();
                                hitAPIGetDepartment(Prefix);

                            }
                        }
                    }, 1500);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        et_location.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {

                locationData = (LocationDatum) parent.getItemAtPosition(pos);

            }
        });


        et_department.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {

                departmentData = (DepartmentDatum) parent.getItemAtPosition(pos);

            }
        });


        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AllCategory item = (AllCategory) parent.getItemAtPosition(position);
                String Cate_Id = String.valueOf(item.getCategoryCode());
                List<AllClass> listClass = new ArrayList<>();
                listClass.add(new AllClass("0", "Select Class"));
                if (Cate_Id.equals("0")) return;
                for (AllCategory allCategory : _allCategoryList) {
                    if (String.valueOf(allCategory.getParentId()).equals(String.valueOf(item.getId()))) {

                        listClass.add(new AllClass(
                                allCategory.getCategoryCode(),
                                allCategory.getCategoryName(),
                                allCategory.getParentId(),
                                allCategory.getSequence(),
                                allCategory.getId(),
                                allCategory.getCreatedBy(),
                                allCategory.getIsActive(),
                                allCategory.getIsDeleted(),
                                allCategory.getIP(),
                                allCategory.getCallType(),
                                allCategory.getResultCode()


                        ));
                    }
                }
                ArrayAdapter<AllClass> dataAdapter = new ArrayAdapter<AllClass>(getActivity(), android.R.layout.simple_spinner_item, listClass);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerClass.setAdapter(dataAdapter);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void hitAPIGetLocation(String Prefix) {
        retrofitManager.getLocation(this, getActivity(), Constants.API_TYPE.GET_LOCATION, Prefix, true);
    }

    private void hitAPIGetDepartment(String Prefix) {
        retrofitManager.getDepartment(this, getActivity(), Constants.API_TYPE.GET_DEPARTMENT, Prefix, true);
    }

    private void hitAPIGetAllCategory() {
        retrofitManager.getAllCategory(this, getActivity(), Constants.API_TYPE.GET_ALL_CATEGORY, true);
    }

    private void hitAPIUploadMedia(MultipartBody.Part[] IMAGES) {
        retrofitManager.uploadMedia(this, getActivity(), Constants.API_TYPE.UPLOAD_MEDIA, IMAGES, false);
    }

    private void hitAPISaveFeedback(final String LocationCode, String DepartmentCode, String CategoryCode, String ClassCode, String Title, String Remarks, String ImageNames) {
        retrofitManager.saveFeedback(this, getActivity(), Constants.API_TYPE.SAVE_FEEDBACK, LocationCode, DepartmentCode, CategoryCode, ClassCode, Title, Remarks, ImageNames, true);
    }


    @Override
    public void onSuccess(Response<ResponseBody> response, Constants.API_TYPE apiType) {

        try {
            String strResponse = response.body().string();
            Log.e("Res", response.body().toString());
            // Toast.makeText(this, apiType+"Response  "+strResponse,Toast.LENGTH_SHORT).show();
            if (apiType == Constants.API_TYPE.GET_LOCATION) {
                /*  List<LocationDatum> locationDataList = new ArrayList<>();*/
                Gson gson = new Gson();
                Type listType = new TypeToken<List<LocationDatum>>() {
                }.getType();
                locationDataList = gson.fromJson(strResponse, listType);

                if (locationDataList != null && !locationDataList.isEmpty()) {
                    ArrayAdapter<LocationDatum> adapter = new ArrayAdapter<LocationDatum>
                            (getActivity(), android.R.layout.simple_spinner_dropdown_item, locationDataList);

                    et_location.setAdapter(adapter);
                    et_location.showDropDown();
                } else
                    Toast.makeText(getActivity(), "No data found!", Toast.LENGTH_SHORT).show();


            } else if (apiType == Constants.API_TYPE.GET_DEPARTMENT) {
                /* List<DepartmentDatum> departmentDataList = new ArrayList<>();*/
                Gson gson = new Gson();
                Type listType = new TypeToken<List<DepartmentDatum>>() {
                }.getType();
                departmentDataList = gson.fromJson(strResponse, listType);

                if (departmentDataList != null && !departmentDataList.isEmpty()) {
                    ArrayAdapter<DepartmentDatum> adapter = new ArrayAdapter<DepartmentDatum>
                            (getActivity(), android.R.layout.simple_spinner_dropdown_item, departmentDataList);

                    et_department.setAdapter(adapter);
                    et_department.showDropDown();
                } else
                    Toast.makeText(getActivity(), "No data found!", Toast.LENGTH_SHORT).show();


            } else if (apiType == Constants.API_TYPE.GET_ALL_CATEGORY) {
                List<AllCategory> allCategoryList = new ArrayList<>();
                _allCategoryList.clear();
                Gson gson = new Gson();
                Type listType = new TypeToken<List<AllCategory>>() {
                }.getType();
                _allCategoryList = gson.fromJson(strResponse, listType);
                allCategoryList.add(new AllCategory("0", "Select Category"));
                for (AllCategory allCategory : _allCategoryList) {
                    if (allCategory.getParentId() == null) {
                        allCategoryList.add(allCategory);
                    }
                }

                ArrayAdapter<AllCategory> dataAdapter = new ArrayAdapter<AllCategory>(getActivity(), android.R.layout.simple_spinner_item, allCategoryList);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCategory.setAdapter(dataAdapter);

                if (allCategoryList.isEmpty()) {
                    Toast.makeText(getActivity(), "No data found!", Toast.LENGTH_SHORT).show();
                }
            } else if (apiType == Constants.API_TYPE.UPLOAD_MEDIA) {
                // mProgressHUD.dismiss();
                mProgressHUD.setMessage("");
                uploadedImages = strResponse;

                hitAPISaveFeedback(locationData.getSalesOfficeCode(), departmentData.getDepartmentCode(),
                        _category.getCategoryCode(), _class.getCategoryCode(), title, remarks, uploadedImages);

                // Toast.makeText(getActivity(), strResponse, Toast.LENGTH_SHORT).show();

            } else if (apiType == Constants.API_TYPE.SAVE_FEEDBACK) {
                mProgressHUD.dismiss();
                Toast.makeText(getActivity(), strResponse, Toast.LENGTH_SHORT).show();
                //  getActivity().getSupportFragmentManager().popBackStackImmediate();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void onFailure(Response<ResponseBody> response, Constants.API_TYPE apiType) {
     if(mProgressHUD!=null && mProgressHUD.isShowing())
        mProgressHUD.dismiss();
        Log.e("Failure:" + apiType, response.toString());
    }

    @Override
    public void onApiException(APIError error, Response<ResponseBody> response, Constants.API_TYPE apiType) {
        if(mProgressHUD!=null && mProgressHUD.isShowing())
        mProgressHUD.dismiss();
        Log.e("ApiException:" + apiType, response.toString());
    }

    private void setAPICallInProgress() {
        APICallInProgress = true;
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                APICallInProgress = false;
            }
        }, 2000);
    }

    void setTemporaryAdapterForCategorySpinner() {
        List<AllCategory> allCategoryList = new ArrayList<>();
        allCategoryList.add(new AllCategory("0", "Select Category"));

        ArrayAdapter<AllCategory> dataAdapter = new ArrayAdapter<AllCategory>(getActivity(), android.R.layout.simple_spinner_item, allCategoryList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(dataAdapter);
    }

    void setTemporaryAdapterForClassSpinner() {
        List<AllClass> allCategoryList = new ArrayList<>();
        allCategoryList.add(new AllClass("0", "Select Class"));

        ArrayAdapter<AllClass> dataAdapter = new ArrayAdapter<AllClass>(getActivity(), android.R.layout.simple_spinner_item, allCategoryList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClass.setAdapter(dataAdapter);
    }

    private void requestPermission() {
        if (
                ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED

        ) {
            ActivityCompat
                    .requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            getImages();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[2] == PackageManager.PERMISSION_GRANTED
                ) {
                    getImages();
                } else {
                    // Permission Denied
                    Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    private void getImages() {
        Intent intent = new Intent(getActivity(), ImagePickerActivity.class);
        startActivityForResult(intent, INTENT_REQUEST_GET_IMAGES);
    }


    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == INTENT_REQUEST_GET_IMAGES && resultCode == Activity.RESULT_OK) {
            ArrayList<Uri> image_uris = data.getParcelableArrayListExtra(ImagePickerActivity.EXTRA_IMAGE_URIS);


            for (Uri imageUris : image_uris) {
                imagesList.add(imageUris.toString());
            }

            refreshRecyclerAdapter(imagesList);


        }
    }


    void refreshRecyclerAdapter(List<String> list) {


        CreateFeedbackImagesAdapter feedbackImagesAdapter = new CreateFeedbackImagesAdapter(getActivity(), list, listenerMainActivity);
        recycler_feedback_images.setAdapter(feedbackImagesAdapter);


    }

    private void multipartImageUpload() {
        final ArrayList<String> list_UploadImageName = new ArrayList<>();
        if (Count_Image_Uploaded < imagesList.size()) Count_Image_Uploaded++;
        // totalSize = 0;
        mProgressHUD = ProgressHUD.show(getActivity(), "Uploading " + Count_Image_Uploaded + "/" + imagesList.size() + ", Please wait...", false);
        try {
            MultipartBody.Part[] surveyImagesParts = new MultipartBody.Part[imagesList.size()];
            for (int index = 0; index < imagesList.size(); index++) {
                String path = imagesList.get(index);
                File file = new File(path);
                ProgressRequestBody surveyBody = new ProgressRequestBody(file, this);
                surveyImagesParts[index] = MultipartBody.Part.createFormData("Files", file.getName(), surveyBody);
            }
            hitAPIUploadMedia(surveyImagesParts);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onProgressUpdate(int percentage) {
        // textView.setText(percentage + "%");
        //   uploadProgressDialog.setProgress(percentage);
    }

    @Override
    public void onError() {
    }

    @Override
    public void onFinish() {
        if (Count_Image_Uploaded < imagesList.size())
            Count_Image_Uploaded++;
        mProgressHUD.setMessage("Uploading " + Count_Image_Uploaded + "/" + imagesList.size() + ", Please wait...");
    }

    @Override
    public void uploadStart() {
        //  textView.setText("0%");
        //  }
    }


    void validateAndSaveFeedback() {
        boolean validate = true;
        String location = et_location.getText().toString().trim();
        String department = et_department.getText().toString().trim();
        title = et_title.getText().toString().trim();
        remarks = et_remarks.getText().toString().trim();


        _category = (AllCategory) spinnerCategory.getSelectedItem();
        _class = (AllClass) spinnerClass.getSelectedItem();

        boolean isCorrectLocation = false;
        boolean isCorrectDepartment = false;
        for (LocationDatum locationDatum : locationDataList) {
            if (locationDatum.getSalesofficeName().equals(location)) {
                isCorrectLocation = true;
                break;
            }
        }

        for (DepartmentDatum departmentDatum : departmentDataList) {
            if (departmentDatum.getDeptLongDesc().trim().equals(department)) {
                isCorrectDepartment = true;
                break;
            }
        }

        if (location.isEmpty() || !isCorrectLocation) {
            Toast.makeText(getActivity(), "Please select correct Location", Toast.LENGTH_SHORT).show();
            validate = false;
        } else if (department.isEmpty() || !isCorrectDepartment) {
            Toast.makeText(getActivity(), "Please select correct Department", Toast.LENGTH_SHORT).show();
            validate = false;
        } else if (_category.getCategoryCode().equals("0")) {
            Toast.makeText(getActivity(), "Please select Category", Toast.LENGTH_SHORT).show();
            validate = false;
        } else if (_class.getCategoryCode().equals("0")) {
            Toast.makeText(getActivity(), "Please select Class", Toast.LENGTH_SHORT).show();
            validate = false;
        } else if (title.isEmpty()) {
            Toast.makeText(getActivity(), "Please enter Title", Toast.LENGTH_SHORT).show();
            validate = false;
        } else if (remarks.isEmpty()) {
            Toast.makeText(getActivity(), "Please enter Remarks", Toast.LENGTH_SHORT).show();
            validate = false;
        } else {

           if(!imagesList.isEmpty()) {
               multipartImageUpload();
           }else {
               hitAPISaveFeedback(locationData.getSalesOfficeCode(), departmentData.getDepartmentCode(),
                       _category.getCategoryCode(), _class.getCategoryCode(), title, remarks, uploadedImages);
           }


        }

    }

}