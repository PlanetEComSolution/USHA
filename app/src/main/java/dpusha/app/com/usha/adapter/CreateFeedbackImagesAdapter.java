package dpusha.app.com.usha.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import dpusha.app.com.usha.R;
import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.model.feedback_data.FeedbackImage;
import dpusha.app.com.usha.orders_home.util.Constants;


/**
 * Created by ravi on 16/11/17.
 */

public class CreateFeedbackImagesAdapter extends RecyclerView.Adapter<CreateFeedbackImagesAdapter.MyViewHolder> {
    private Context context;

    List<String> items;

    MainListner listenerMainActivity;


    public CreateFeedbackImagesAdapter(Context context, List<String> items, MainListner listenerMainActivity) {
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

        File file = new File(items.get(position));
        Picasso.with(context)
                .load(file)
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


        }


    }


}

