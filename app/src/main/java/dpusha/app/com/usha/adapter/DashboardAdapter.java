package dpusha.app.com.usha.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import dpusha.app.com.usha.R;
import dpusha.app.com.usha.fragment.book_order.by_category.AddItem;
import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.model.DashboardModel;
import dpusha.app.com.usha.model.ProductDivision;
import dpusha.app.com.usha.orders_home.util.Constants;


/**
 * Created by ravi on 16/11/17.
 */

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.MyViewHolder> {
    private Context context;

    List<DashboardModel> productDivisionList;

    MainListner listenerMainActivity;

    /*Integer Icon[]={R.drawable.fan,R.drawable.fan2,R.drawable.fan3,R.drawable.fan4};*/

    public DashboardAdapter(Context context, List<DashboardModel> productDivisionList, MainListner listenerMainActivity) {
        this.context = context;
        this.productDivisionList = productDivisionList;
        this.listenerMainActivity = listenerMainActivity;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_dashboard_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.txtvw_title.setText(String.valueOf(productDivisionList.get(position).getTitle()));
        holder.txtvw_subtitle1.setText(String.valueOf(productDivisionList.get(position).getSubTitle1()));
        holder.txtvw_subtitle2.setText(String.valueOf(productDivisionList.get(position).getSubTitle2()));

        holder.imgvw.setImageResource(productDivisionList.get(position).getImage());
      /*  Picasso.with(context)
                .load(Constants.DIVISION_IMAGE_URL_PREFIX +productDivisionList.get(position).getImageName())
                .fit()
                .placeholder(R.drawable.thumb_no_mage)
                .error(R.drawable.thumb_no_mage)
                .into(holder.imgvw_div);*/
    }

    @Override
    public int getItemCount() {
        //     return MenuItem.length;
        return productDivisionList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgvw;
        public TextView txtvw_title,txtvw_subtitle1,txtvw_subtitle2;


        public MyViewHolder(View view) {
            super(view);
            imgvw = view.findViewById(R.id.imgvw);
            txtvw_title = view.findViewById(R.id.txtvw_title);
            txtvw_subtitle1 = view.findViewById(R.id.txtvw_subtitle1);
            txtvw_subtitle2 = view.findViewById(R.id.txtvw_subtitle2);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });


        }


    }
}

