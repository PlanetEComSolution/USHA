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

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

import dpusha.app.com.usha.R;
import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.model.LinkData;
import dpusha.app.com.usha.model.ProductCategory;
import dpusha.app.com.usha.orders_home.util.Constants;

public class LinksAdapter extends RecyclerView.Adapter<LinksAdapter.MyViewHolder> {

    private List<LinkData> links;
    MainListner listenerMainActivity;
    private Context context;

    public LinksAdapter(Context context, List<LinkData> links, MainListner listenerMainActivity) {
        this.context = context;
        this.links = links;
        this.listenerMainActivity = listenerMainActivity;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_useful_links, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //final LinkData mModel=links.get(position);
       // holder.name.setText(mModel.getTitle());
        holder.name.setText(links.get(position).getTitle());
        Picasso.with(context)
                .load(Constants.CATEGORY_IMAGE_URL_PREFIX +links.get(position).getImageName())
                .fit()
                .placeholder(R.drawable.puma_offer)
                .error(R.drawable.puma_offer)
                .into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String url= links.get(position).getUrl();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                //startActivity(intent);
                context.startActivity(intent);
            }
        });

        //Glide.with(context).load(Constants.PRODUCT_IMAGE_URL_PREFIX +links.get(position).getImageName()).into(holder.image);


    }

    @Override
    public int getItemCount() {
        return links.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView image;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            name = itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.image);
        }
    }
}
