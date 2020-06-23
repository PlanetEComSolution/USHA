package dpusha.app.com.usha.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import dpusha.app.com.usha.R;
import dpusha.app.com.usha.fragment.feedback.FullscreenImageView;
import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.model.LinkData;
import dpusha.app.com.usha.model.ProductCatalougeList;
import dpusha.app.com.usha.orders_home.util.Constants;

public class ProductCatalougeAdapter extends RecyclerView.Adapter<ProductCatalougeAdapter.MyViewHolder> {

    private List<ProductCatalougeList> productCatalougeLists;
    MainListner listenerMainActivity;
    private Context context;

    public ProductCatalougeAdapter(Context context, List<ProductCatalougeList> productCatalougeLists, MainListner listenerMainActivity){

        this.context = context;
        this.productCatalougeLists = productCatalougeLists;
        this.listenerMainActivity = listenerMainActivity;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_product_catalouge, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(productCatalougeLists.get(position).getTitle());
       /* Picasso.with(context)
                .load(Constants.CATEGORY_IMAGE_URL_PREFIX +productCatalougeLists.get(position).getImage()+".png")
                .fit()
                .placeholder(R.drawable.puma_offer)
                .error(R.drawable.puma_offer)
                .into(holder.image);
    }*/
     Picasso.with(context)
            .load(Constants.CATEGORY_IMAGE_URL_PREFIX +productCatalougeLists.get(position).getImageName())
            .fit()
                .placeholder(R.drawable.usha_icon)
                .error(R.drawable.usha_icon)
                .into(holder.image);

      /*  holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url= productCatalougeLists.get(position).getUrl();
               *//* Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                context.startActivity(intent);*//*

                Intent intent = new Intent(context, FullscreenImageView.class);
                intent.putExtra("url",url);
                context.startActivity(intent);

            }
        });*/



    }


    @Override
    public int getItemCount() {
        return productCatalougeLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url= productCatalougeLists.get(getAdapterPosition()).getUrl();
                    Intent intent = new Intent(context, FullscreenImageView.class);
                    intent.putExtra("url",url);
                    context.startActivity(intent);

                }
            });
        }
    }
}
