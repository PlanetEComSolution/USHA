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
import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.model.ProductCatalougeList;
import dpusha.app.com.usha.model.SocialNetworkingList;
import dpusha.app.com.usha.orders_home.util.Constants;


public class SocialNetworkingAdapter extends RecyclerView.Adapter<SocialNetworkingAdapter.MyViewHolder> {

    private List<SocialNetworkingList> mList;
    MainListner listenerMainActivity;
    private Context context;

    public SocialNetworkingAdapter(Context context, List<SocialNetworkingList> mList, MainListner listenerMainActivity){

        this.context = context;
        this.mList = mList;
        this.listenerMainActivity = listenerMainActivity;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_social_networking, parent, false);
        return new SocialNetworkingAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(mList.get(position).getTitle());
        Picasso.with(context)
                .load(Constants.CATEGORY_IMAGE_URL_PREFIX +mList.get(position).getImageName())
                .fit()
                .placeholder(R.drawable.usha_icon)
                .error(R.drawable.usha_icon)
                .into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url= mList.get(position).getUrl();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                //startActivity(intent);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
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
