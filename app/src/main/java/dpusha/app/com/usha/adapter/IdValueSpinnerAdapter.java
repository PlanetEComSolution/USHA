package dpusha.app.com.usha.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import dpusha.app.com.usha.R;
import dpusha.app.com.usha.model.SchemeSpinnerResponse;

public class IdValueSpinnerAdapter extends ArrayAdapter {


    private Context mContext;
    private List<SchemeSpinnerResponse> list = new ArrayList<>();

    public IdValueSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<SchemeSpinnerResponse> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.list = objects;
    }
    @Override
    public boolean isEnabled(int position) {
        return position != 0;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);
        TextView tv = (TextView) view;
        if(position == 0){
            // Set the hint text color gray
            //tv.setTextColor(Color.GRAY);
            tv.setTextColor(ContextCompat.getColor(mContext, R.color.highlight_text_color));

        }
        else {

            tv.setTextColor(Color.BLACK);
        }
        tv.setText(list.get(position).getLabel());
        return view;
    }

    @NonNull

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.spinner_text, parent,false);
        SchemeSpinnerResponse model = list.get(position);
        TextView name = listItem.findViewById(android.R.id.text1);
        name.setText(model.getLabel());
        return listItem;
    }


}
