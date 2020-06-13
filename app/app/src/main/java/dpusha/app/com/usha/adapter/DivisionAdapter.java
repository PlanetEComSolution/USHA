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
import dpusha.app.com.usha.model.ProductCategory;
import dpusha.app.com.usha.model.ProductDivision;
import dpusha.app.com.usha.orders_home.util.Constants;
import dpusha.app.com.usha.shared_preference.SharedPreferencesUtil;


/**
 * Created by ravi on 16/11/17.
 */

public class DivisionAdapter extends RecyclerView.Adapter<DivisionAdapter.MyViewHolder> {
    private Context context;

    List<ProductDivision> productDivisionList;

    MainListner listenerMainActivity;

    Integer Icon[]={R.drawable.fan,R.drawable.fan2,R.drawable.fan3,R.drawable.fan4};

    public DivisionAdapter(Context context, List<ProductDivision> productDivisionList,MainListner listenerMainActivity) {
        this.context = context;
        this.productDivisionList = productDivisionList;
        this.listenerMainActivity = listenerMainActivity;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_division_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.txtvw_divName.setText(String.valueOf(productDivisionList.get(position).getDivName()));
         /* if(position<Icon.length){
              holder.imgvw_div.setImageResource(Icon[position]);
          }else {
              holder.imgvw_div.setImageResource(Icon[0]);
          }*/

        Picasso.with(context)
                .load(Constants.DIVISION_IMAGE_URL_PREFIX +productDivisionList.get(position).getImageName())
                .fit()
                .placeholder(R.drawable.thumb_no_mage)
                .error(R.drawable.thumb_no_mage)
                .into(holder.imgvw_div);
    }

    @Override
    public int getItemCount() {
        //     return MenuItem.length;
        return productDivisionList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgvw_div;
        public TextView txtvw_divName;


        public MyViewHolder(View view) {
            super(view);
            imgvw_div = view.findViewById(R.id.imgvw_div);
            txtvw_divName = view.findViewById(R.id.txtvw_divName);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle b=new Bundle();
                    b.putString("DivisionCode",productDivisionList.get(getAdapterPosition()).getDivCode());
                    b.putString("DivisionName",productDivisionList.get(getAdapterPosition()).getDivName());

                    Fragment fragment=new AddItem();
                    fragment.setArguments(b);
                  //  SharedPreferencesUtil.clearCartItems(context,listenerMainActivity);
                    listenerMainActivity.clearCart(context);
                    listenerMainActivity.addFragment(fragment, "AddItem", true);


                }
            });


        }


    }
}

