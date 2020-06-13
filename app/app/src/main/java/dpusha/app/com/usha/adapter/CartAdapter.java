package dpusha.app.com.usha.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import dpusha.app.com.usha.R;
import dpusha.app.com.usha.fragment.cart.CartFragment;
import dpusha.app.com.usha.listeners.CartItemChangedListener;
import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.model.Cart;
import dpusha.app.com.usha.model.CartItem;
import dpusha.app.com.usha.model.Item;
import dpusha.app.com.usha.model.ProductDescription;
import dpusha.app.com.usha.orders_home.util.Constants;
import dpusha.app.com.usha.orders_home.util.utility;
import dpusha.app.com.usha.shared_preference.SharedPreferencesUtil;


/**
 * Created by ravi on 16/11/17.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    private Context context;

    List<Cart> items;
    CartItemChangedListener cartItemChangedListener;
    MainListner listenerMainActivity;
    Integer Icon[] = {R.drawable.fan, R.drawable.fan2, R.drawable.fan3, R.drawable.fan4};

    public CartAdapter(Context context, List<Cart> items, CartItemChangedListener listener, MainListner listenerMainActivity) {
        this.context = context;
        this.items = items;
        this.cartItemChangedListener = listener;

        this.listenerMainActivity = listenerMainActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_cart_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.txtvw_SKU.setText("SKU :" + items.get(position).getSKU());
        holder.txtvw_Description.setText(items.get(position).getDescription());

        holder.txtvw_variable_qnty.setText(String.valueOf(items.get(position).getQuantity()));
       /* if (position < Icon.length) {
            holder.icon.setImageResource(Icon[position]);
        } else {
            holder.icon.setImageResource(Icon[0]);
        }*/

        Picasso.with(context)
                .load(Constants.PRODUCT_IMAGE_URL_PREFIX + items.get(position).getImageName())
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
        public ImageView icon;
        public TextView txtvw_SKU, txtvw_Description, txtvw_minus_qnty, txtvw_plus_qnty, txtvw_variable_qnty;
        LinearLayout button_addToCart;

        public MyViewHolder(View view) {
            super(view);
            icon = view.findViewById(R.id.icon);

            txtvw_SKU = view.findViewById(R.id.txtvw_SKU);
            txtvw_Description = view.findViewById(R.id.txtvw_Description);
            txtvw_minus_qnty = view.findViewById(R.id.txtvw_minus_qnty);
            txtvw_plus_qnty = view.findViewById(R.id.txtvw_plus_qnty);
            txtvw_variable_qnty = view.findViewById(R.id.txtvw_variable_qnty);
            button_addToCart = view.findViewById(R.id.button_addToCart);
            txtvw_minus_qnty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int quantity = items.get(getAdapterPosition()).getQuantity();
                    if (quantity != 1) {
                        quantity = quantity - 1;
                        items.get(getAdapterPosition()).setQuantity(quantity);
                    }

                    notifyDataSetChanged();
                    cartItemChangedListener.onCartRefresh();


                }
            });
            txtvw_plus_qnty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int quantity = items.get(getAdapterPosition()).getQuantity();
                    quantity = quantity + 1;
                    items.get(getAdapterPosition()).setQuantity(quantity);

                    notifyDataSetChanged();
                    cartItemChangedListener.onCartRefresh();
                }
            });
            button_addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProductDescription item = utility.convertCart_To_Description(items.get(getAdapterPosition()));
                    utility.addItemToCartPreference(item, context,listenerMainActivity);

                  //  listenerMainActivity.addFragment(new CartFragment(), "CartFragment", true);

                    utility.showToast(context,"Item added to cart!");
                }
            });

        }


    }


}

