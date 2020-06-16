package dpusha.app.com.usha.fragment.download;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dpusha.app.com.usha.R;


public class InvoiceFragment extends Fragment {


    public InvoiceFragment() {
        // Required empty public constructor
    }


    public static InvoiceFragment newInstance() {
        InvoiceFragment fragment = new InvoiceFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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
        return inflater.inflate(R.layout.fragment_invoice, container, false);
    }
}