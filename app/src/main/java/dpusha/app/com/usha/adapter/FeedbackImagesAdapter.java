package dpusha.app.com.usha.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import dpusha.app.com.usha.R;
import dpusha.app.com.usha.fragment.feedback.FullscreenImageView;
import dpusha.app.com.usha.listeners.CartItemChangedListener;
import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.model.Cart;
import dpusha.app.com.usha.model.ProductDescription;
import dpusha.app.com.usha.model.feedback_data.FeedbackImage;
import dpusha.app.com.usha.orders_home.util.Constants;
import dpusha.app.com.usha.orders_home.util.utility;


/**
 * Created by ravi on 16/11/17.
 */

public class FeedbackImagesAdapter extends RecyclerView.Adapter<FeedbackImagesAdapter.MyViewHolder> {
    private Context context;

    List<FeedbackImage> items;

    MainListner listenerMainActivity;


    public FeedbackImagesAdapter(Context context, List<FeedbackImage> items, MainListner listenerMainActivity) {
        this.context = context;
        this.items = items;

        this.listenerMainActivity = listenerMainActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feedback_image, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        Picasso.with(context)
                .load(Constants.FEEDBACK_IMAGE_URL_PREFIX + items.get(position).getImageName())
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

        public MyViewHolder(View view) {
            super(view);
            icon = view.findViewById(R.id.icon);
            icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(context, FullscreenImageView.class);
                    i.putExtra("url",Constants.FEEDBACK_IMAGE_URL_PREFIX + items.get(getAdapterPosition()).getImageName());
                   context.startActivity(i);
                }
            });

        }


    }


}

