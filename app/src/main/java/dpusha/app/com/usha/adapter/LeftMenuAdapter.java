package dpusha.app.com.usha.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import dpusha.app.com.usha.adapter.recycler_decorator.RecyclerViewMargin;
import dpusha.app.com.usha.model.DrawerItem;
import dpusha.app.com.usha.R;
import dpusha.app.com.usha.activity.DrawerMainActivity;
import dpusha.app.com.usha.listeners.OnDrawerItemClickListener;


/**
 * Created by ravi on 16/11/17.
 */

public class LeftMenuAdapter extends RecyclerView.Adapter<LeftMenuAdapter.MyViewHolder>  {
    private Context context;

    List<DrawerItem> drawerItems;
OnDrawerItemClickListener onDrawerItemClickListener;

    public LeftMenuAdapter(Context context, List<DrawerItem> drawerItems) {
        this.context = context;

        this.drawerItems = drawerItems;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.drawer_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        onDrawerItemClickListener=(OnDrawerItemClickListener)holder;
        holder.text.setText(drawerItems.get(position).getName());
        holder.icon.setImageResource(drawerItems.get(position).getIcon());
        if (position == 2 || position == 5) {

            holder.right_arrow.setVisibility(View.VISIBLE);
            if (position == 2) {
                holder.recycler_menu_subItem.setAdapter(new OrderSubMenuAdapter(context));
            }
            if (position == 5) {
                holder.recycler_menu_subItem.setAdapter(new DownloadsSubMenuAdapter(context));
            }

        }else {
            holder.right_arrow.setVisibility(View.GONE);
        }

        //  Picasso.get().load(drawerItems.get(position).getLogo()).into( holder.menu_icon);

    }

    @Override
    public int getItemCount() {
        //     return MenuItem.length;
        return drawerItems.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements OnDrawerItemClickListener{
        public ImageView right_arrow, down_arrow;
        public TextView text;
        public ImageView icon;
        public RecyclerView recycler_menu_subItem;

        public MyViewHolder(View view) {
            super(view);
            right_arrow = view.findViewById(R.id.right_arrow);
            down_arrow = view.findViewById(R.id.down_arrow);
            text = view.findViewById(R.id.text);
            icon = view.findViewById(R.id.icon);
            recycler_menu_subItem = view.findViewById(R.id.recycler_menu_subItem);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
            recycler_menu_subItem.setLayoutManager(mLayoutManager);
            recycler_menu_subItem.setItemAnimator(new DefaultItemAnimator());
            RecyclerViewMargin decoration = new RecyclerViewMargin(30, 1);
            recycler_menu_subItem.addItemDecoration(decoration);
            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);
            Animation slideUp = AnimationUtils.loadAnimation(context, R.anim.slide_up);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (context instanceof DrawerMainActivity) {


                        if (getAdapterPosition() == 2 || getAdapterPosition() == 5) {
                            if (recycler_menu_subItem.getVisibility() == View.GONE) {
                                recycler_menu_subItem.setVisibility(View.VISIBLE);
                                recycler_menu_subItem.startAnimation(slideDown);
                                down_arrow.setVisibility(View.VISIBLE);
                                right_arrow.setVisibility(View.GONE);
                            }

                                    else if (recycler_menu_subItem.getVisibility() == View.VISIBLE) {
                                recycler_menu_subItem.startAnimation(slideUp);
                                recycler_menu_subItem.setVisibility(View.GONE);
                                down_arrow.setVisibility(View.GONE);
                                right_arrow.setVisibility(View.VISIBLE);
                            }
                        } else {

                            ((DrawerMainActivity) context).onLeftDrawerItemClick(getAdapterPosition());
                        }


                    }
                }
            });
        }
        @Override
        public void onDrawerClosed() {
           // recycler_menu_subItem.startAnimation(slideUp);
            recycler_menu_subItem.setVisibility(View.GONE);
            down_arrow.setVisibility(View.GONE);
            right_arrow.setVisibility(View.VISIBLE);

        }

        @Override
        public void onDrawerOpened() {


        }
    }

    public void onDrawerClosed() {
        onDrawerItemClickListener.onDrawerClosed();

    }


}
