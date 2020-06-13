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
import dpusha.app.com.usha.fragment.book_order.by_category.Division;
import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.model.ProductCategory;
import dpusha.app.com.usha.orders_home.util.Constants;


/**
 * Created by ravi on 16/11/17.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    private Context context;

    List<ProductCategory> productCategoryList;
    MainListner listenerMainActivity;


    Integer Icon[]={R.drawable.fan,R.drawable.fan,R.drawable.fan,R.drawable.fan};

    public CategoryAdapter(Context context, List<ProductCategory> productCategoryList,MainListner listenerMainActivity) {
        this.context = context;
        this.productCategoryList = productCategoryList;
        this.listenerMainActivity = listenerMainActivity;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_category_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.txtvw_categoryName.setText(String.valueOf(productCategoryList.get(position).getCatName()));
         /* if(position<Icon.length){
              holder.imgvw_category.setImageResource(Icon[position]);
          }else {
              holder.imgvw_category.setImageResource(Icon[0]);
          }*/
        Picasso.with(context)
                .load(Constants.CATEGORY_IMAGE_URL_PREFIX +productCategoryList.get(position).getCatName()+".png")
                .fit()
                .placeholder(R.drawable.puma_offer)
                .error(R.drawable.puma_offer)
                .into(holder.imgvw_category);
    }

    @Override
    public int getItemCount() {
        //     return MenuItem.length;
        return productCategoryList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgvw_category;
        public TextView txtvw_categoryName;


        public MyViewHolder(View view) {
            super(view);
            imgvw_category = view.findViewById(R.id.imgvw_category);
            txtvw_categoryName = view.findViewById(R.id.txtvw_categoryName);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle b=new Bundle();
                    b.putString("CategoryCode",productCategoryList.get(getAdapterPosition()).getCatCode());

                    Fragment fragment=new Division();
                    fragment.setArguments(b);
                    listenerMainActivity.addFragment(fragment, "Division", true);


                }
            });


        }


    }
}

