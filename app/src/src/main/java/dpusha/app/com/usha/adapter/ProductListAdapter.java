package dpusha.app.com.usha.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import dpusha.app.com.usha.R;
import dpusha.app.com.usha.activity.DrawerMainActivity;
import dpusha.app.com.usha.listeners.OnDrawerItemClickListener;
import dpusha.app.com.usha.model.DrawerItem;
import dpusha.app.com.usha.model.ProductItem;


/**
 * Created by ravi on 16/11/17.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.MyViewHolder>  {
    private Context context;

    List<ProductItem> items;


    public ProductListAdapter(Context context, List<ProductItem> items) {
        this.context = context;

        this.items = items;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.txtvw_SKU.setText("SKU :"+items.get(position).getSKU());
        holder.txtvw_Description.setText(items.get(position).getSKU());
        holder.icon.setImageDrawable(context.getResources().getDrawable(R.drawable.fan));
     //   Picasso.load(items.get(position).getImage()).into( holder.menu_icon);
    }

    @Override
    public int getItemCount() {
        //     return MenuItem.length;
        return items.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView icon, imgvw_delete;
        public TextView txtvw_SKU, txtvw_Description, txtvw_minus_qnty, txtvw_plus_qnty;


        public MyViewHolder(View view) {
            super(view);
            icon = view.findViewById(R.id.icon);
            imgvw_delete = view.findViewById(R.id.imgvw_delete);
            txtvw_SKU = view.findViewById(R.id.txtvw_SKU);
            txtvw_Description = view.findViewById(R.id.txtvw_Description);
            txtvw_minus_qnty = view.findViewById(R.id.txtvw_minus_qnty);
            txtvw_plus_qnty = view.findViewById(R.id.txtvw_plus_qnty);

            imgvw_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            txtvw_minus_qnty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            txtvw_plus_qnty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


        }


    }}

