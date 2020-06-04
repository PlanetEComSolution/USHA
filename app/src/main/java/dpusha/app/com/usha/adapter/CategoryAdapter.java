package dpusha.app.com.usha.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import dpusha.app.com.usha.R;
import dpusha.app.com.usha.listeners.CartItemChangedListener;
import dpusha.app.com.usha.model.CartItem;
import dpusha.app.com.usha.model.Item;
import dpusha.app.com.usha.model.ProductCategory;
import dpusha.app.com.usha.orders_home.util.utility;
import dpusha.app.com.usha.shared_preference.SharedPreferencesUtil;


/**
 * Created by ravi on 16/11/17.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    private Context context;

    List<ProductCategory> productCategoryList;



    Integer Icon[]={R.drawable.fan,R.drawable.fan2,R.drawable.fan3,R.drawable.fan4};

    public CategoryAdapter(Context context, List<ProductCategory> productCategoryList) {
        this.context = context;
        this.productCategoryList = productCategoryList;


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
          if(position<Icon.length){
              holder.imgvw_category.setImageResource(Icon[position]);
          }else {
              holder.imgvw_category.setImageResource(Icon[0]);
          }
         //  Picasso.load(productCategoryList.get(position).getImageName()).into( holder.imgvw_category);
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


                }
            });


        }


    }
}

