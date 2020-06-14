package dpusha.app.com.usha.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dpusha.app.com.usha.R;
import dpusha.app.com.usha.activity.DrawerMainActivity;


/**
 * Created by ravi on 16/11/17.
 */

public class DownloadsSubMenuAdapter extends RecyclerView.Adapter<DownloadsSubMenuAdapter.MyViewHolder> {
    private Context context;

    String items[]={"Schemes","Account Statement","Invoices"};
    List<String> drawerSubItems=new ArrayList<>(Arrays.asList(items));


    public DownloadsSubMenuAdapter(Context context) {
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.drawer_sub_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.text.setText(drawerSubItems.get(position));
       // holder.icon.setImageResource(drawerSubItems.get(position).getIcon());


        //  Picasso.get().load(drawerSubItems.get(position).getLogo()).into( holder.menu_icon);

    }

    @Override
    public int getItemCount() {
        //     return MenuItem.length;
        return drawerSubItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView text;
        public ImageView icon;


        public MyViewHolder(View view) {
            super(view);

            text = view.findViewById(R.id.text);
            icon = view.findViewById(R.id.icon);
            icon.setVisibility(View.GONE);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (context instanceof DrawerMainActivity) {
                        ((DrawerMainActivity) context).onLeftDrawerDownloadsItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }


}
