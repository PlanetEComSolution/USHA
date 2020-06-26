package dpusha.app.com.usha.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import dpusha.app.com.usha.R;
import dpusha.app.com.usha.listeners.CartItemChangedListener;
import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.model.Cart;
import dpusha.app.com.usha.model.CartItem;
import dpusha.app.com.usha.model.Item;
import dpusha.app.com.usha.orders_home.util.Constants;
import dpusha.app.com.usha.orders_home.util.utility;


/**
 * Created by ravi on 16/11/17.
 */

public class CartItemsAdapterNew extends RecyclerView.Adapter<CartItemsAdapterNew.MyViewHolder> {
    private Context context;

    List<Cart> items;


    boolean isOrderEditable = false;

    Integer Icon[] = {R.drawable.fan, R.drawable.fan, R.drawable.fan, R.drawable.fan};

    MainListner mainListner;
//    Integer Icon[]={R.drawable.fan,R.drawable.fan2,R.drawable.fan3,R.drawable.fan4};


    public CartItemsAdapterNew(Context context,List<Cart> items,  MainListner mainListner, boolean isOrderEditable) {
        this.context = context;

        this.items = items;

        this.mainListner = mainListner;
        this.isOrderEditable = isOrderEditable;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item_new, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.txtvw_SKU.setText("SKU :" + items.get(position).getsKU());
        holder.txtvw_Description.setText(items.get(position).getDescription());



        if(items.get(position).getQuantity()==0){
            holder.et_qty.setText("");
        }
        else {
            holder.et_qty.setText(String.valueOf(items.get(position).getQuantity()));
        }

       /* holder.txtvw_variable_qnty.setText(String.valueOf(items.get(position).getQuantity()));

        holder.txtvw_minus_qnty.setEnabled(isOrderEditable);
        holder.txtvw_plus_qnty.setEnabled(isOrderEditable);*/

       /* if(isOrderEditable){
            holder.imgvw_delete.setVisibility(View.VISIBLE);
        }else {
            holder.imgvw_delete.setVisibility(View.GONE);
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
        public ImageView icon, imgvw_delete;
        public TextView txtvw_SKU, txtvw_Description;
        EditText et_qty;


        public MyViewHolder(View view) {
            super(view);
            icon = view.findViewById(R.id.icon);
            imgvw_delete = view.findViewById(R.id.imgvw_delete);
            txtvw_SKU = view.findViewById(R.id.txtvw_SKU);
            txtvw_Description = view.findViewById(R.id.txtvw_Description);
            et_qty = view.findViewById(R.id.et_qty);

           /* imgvw_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    items.remove(getAdapterPosition());
                    cartItem.setItems(items);

                    notifyDataSetChanged();
                    // SharedPreferencesUtil.setCartItems(context,  utility.convertCartToJSONString(cartItem));
                    utility.setCartItems(context, utility.convertCartToJSONString(cartItem), mainListner);
                    cartItemChangedListener.onCartRefresh(items.size());

                }
            });*/
            et_qty.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                   String quantity= et_qty.getText().toString().trim();
                   if(quantity.equals("")) return;
                   int qty=Integer.parseInt(quantity);
                    items.get(getAdapterPosition()).setQuantity(qty);
                  // notifyDataSetChanged();

                }
            });

        }


    }
}

