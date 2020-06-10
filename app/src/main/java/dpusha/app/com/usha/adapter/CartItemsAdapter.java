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
import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.model.CartItem;
import dpusha.app.com.usha.model.Item;
import dpusha.app.com.usha.orders_home.util.Constants;
import dpusha.app.com.usha.orders_home.util.utility;
import dpusha.app.com.usha.shared_preference.SharedPreferencesUtil;


/**
 * Created by ravi on 16/11/17.
 */

public class CartItemsAdapter extends RecyclerView.Adapter<CartItemsAdapter.MyViewHolder> {
    private Context context;

    List<Item> items;
    CartItem cartItem;
    CartItemChangedListener cartItemChangedListener;
    MainListner mainListner;
    Integer Icon[]={R.drawable.fan,R.drawable.fan2,R.drawable.fan3,R.drawable.fan4};

    public CartItemsAdapter(Context context, CartItem cartItem, CartItemChangedListener listener, MainListner mainListner) {
        this.context = context;
        this.cartItem = cartItem;
        this.items = cartItem.getItems();
        this.  cartItemChangedListener=listener;
        this. mainListner=mainListner;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.txtvw_SKU.setText("SKU :" + items.get(position).getsKU());
        holder.txtvw_Description.setText(items.get(position).getDescription());

        holder.txtvw_variable_qnty.setText(String.valueOf(items.get(position).getQuantity()));
         /* if(position<Icon.length){
              holder.icon.setImageResource(Icon[position]);
          }else {
              holder.icon.setImageResource(Icon[0]);
          }*/

        Picasso.with(context)
                .load(Constants.PRODUCT_IMAGE_URL_PREFIX +items.get(position).getImageName())
                .fit()
                .placeholder(R.drawable.thumb_no_mage)
                .error(R.drawable.thumb_no_mage)
                .into(holder.icon);


    }

    @Override
    public int getItemCount() {
        //     return MenuItem.length;
        return items.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView icon, imgvw_delete;
        public TextView txtvw_SKU, txtvw_Description, txtvw_minus_qnty, txtvw_plus_qnty,txtvw_variable_qnty;


        public MyViewHolder(View view) {
            super(view);
            icon = view.findViewById(R.id.icon);
            imgvw_delete = view.findViewById(R.id.imgvw_delete);
            txtvw_SKU = view.findViewById(R.id.txtvw_SKU);
            txtvw_Description = view.findViewById(R.id.txtvw_Description);
            txtvw_minus_qnty = view.findViewById(R.id.txtvw_minus_qnty);
            txtvw_plus_qnty = view.findViewById(R.id.txtvw_plus_qnty);
            txtvw_variable_qnty= view.findViewById(R.id.txtvw_variable_qnty);
            imgvw_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    items.remove(getAdapterPosition());
                    cartItem.setItems(items);

                    notifyDataSetChanged();
                   // SharedPreferencesUtil.setCartItems(context,  utility.convertCartToJSONString(cartItem));
utility.setCartItems(context, utility.convertCartToJSONString(cartItem),mainListner);
                    cartItemChangedListener.onCartRefresh();

                }
            });
            txtvw_minus_qnty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int quantity = items.get(getAdapterPosition()).getQuantity();
                    if (quantity != 1) {
                        quantity = quantity - 1;
                        items.get(getAdapterPosition()).setQuantity(quantity);
                    }

                    cartItem.setItems(items);
                    notifyDataSetChanged();
                    //SharedPreferencesUtil.setCartItems(context,utility.convertCartToJSONString(cartItem));
                    utility.setCartItems(context,utility.convertCartToJSONString(cartItem),mainListner);

                    cartItemChangedListener.onCartRefresh();


                }
            });
            txtvw_plus_qnty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int quantity = items.get(getAdapterPosition()).getQuantity();
                    quantity = quantity + 1;
                    items.get(getAdapterPosition()).setQuantity(quantity);
                    cartItem.setItems(items);
                    notifyDataSetChanged();
                  //  SharedPreferencesUtil.setCartItems(context,utility.convertCartToJSONString(cartItem));
                    utility.setCartItems(context,utility.convertCartToJSONString(cartItem),mainListner);

                    cartItemChangedListener.onCartRefresh();
                }
            });


        }


    }
}

