package dpusha.app.com.usha.fragment;


import android.app.AlertDialog;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dpusha.app.com.usha.R;
import dpusha.app.com.usha.listeners.MainListner;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {





   private MainListner listenerMainActivity;
   private FragmentActivity activity;
    AlertDialog alertDialog ;

    public HomeFragment() {


        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Unbinder unbinder = ButterKnife.bind(this, view);
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
            R.id.rl_AdminModule,
            R.id.rl_Dashboard,
            R.id.rl_ProductCatalogue,
            R.id.rl_StockStatus,
            R.id.rl_Orders,
            R.id.rl_Reports,
            R.id.rl_Account_Statement,
            R.id.rl_Payment,
    })


    public void onClick(@NonNull View view) {
       // Toast.makeText(getActivity(),"Add your fragment HERE in HomeFragment: ",Toast.LENGTH_SHORT).show();
        switch (view.getId()) {
            case R.id.rl_AdminModule:

               // listenerMainActivity.addFragment( new MusicDetailFragment(), "MusicDetailFragment", false);

                break;
            case R.id.rl_Dashboard:

                // listenerMainActivity.addFragment( new MusicDetailFragment(), "MusicDetailFragment", false);

                break;
            case R.id.rl_ProductCatalogue:

                // listenerMainActivity.addFragment( new MusicDetailFragment(), "MusicDetailFragment", false);

                break;
            case R.id.rl_StockStatus:

                // listenerMainActivity.addFragment( new MusicDetailFragment(), "MusicDetailFragment", false);

                break;
            case R.id.rl_Orders:
              //  showDialogForBookAnOrder();
               //  listenerMainActivity.addFragment( new OrderByItemCodeFragment(), "MusicDetailFragment", false);

                break;
            case R.id.rl_Reports:
                //   Toast.makeText(getActivity(),"Add your fragment HERE in HomeFragment: ",Toast.LENGTH_SHORT).show();

                /*Intent i = new Intent(getContext(), ClientOrdersActivity.class);
                startActivity(i);*/
                listenerMainActivity.addFragment(new orderListFragment(), "orderListFragment", true);
                // listenerMainActivity.addFragment( new MusicDetailFragment(), "MusicDetailFragment", false);

                break;

            case R.id.rl_Account_Statement:

                // listenerMainActivity.addFragment( new MusicDetailFragment(), "MusicDetailFragment", false);

                break;
            case R.id.rl_Payment:

                // listenerMainActivity.addFragment( new MusicDetailFragment(), "MusicDetailFragment", false);

                break;
        }
    }



    void showDialogForBookAnOrder(){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_order_by_item_code, null);
        dialogBuilder.setView(dialogView);

        Button buttonByItemCode = (Button) dialogView.findViewById(R.id.buttonByItemCode);
        Button button_ByCategory = (Button) dialogView.findViewById(R.id.button_ByCategory);
        Button button_ByCart = (Button) dialogView.findViewById(R.id.button_ByCart);
        Button button_ByTemplate = (Button) dialogView.findViewById(R.id.button_ByTemplate);


        buttonByItemCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                listenerMainActivity.addFragment( new OrderByItemCodeFragment(), "OrderByItemCodeFragment", true);
            }
        });
        button_ByCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        button_ByCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        button_ByTemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

         alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
