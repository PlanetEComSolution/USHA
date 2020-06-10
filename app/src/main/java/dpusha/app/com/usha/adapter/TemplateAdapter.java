package dpusha.app.com.usha.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dpusha.app.com.usha.R;
import dpusha.app.com.usha.listeners.BookOrderByTemplateListener;
import dpusha.app.com.usha.model.ProductCategory;
import dpusha.app.com.usha.model.Template;
import dpusha.app.com.usha.orders_home.util.utility;


/**
 * Created by ravi on 16/11/17.
 */

public class TemplateAdapter extends RecyclerView.Adapter<TemplateAdapter.MyViewHolder> {
    private Context context;

    List<Template> templateList;
BookOrderByTemplateListener bookOrderByTemplateListener;

    public TemplateAdapter(Context context, List<Template> templateList, BookOrderByTemplateListener bookOrderByTemplateListener) {
        this.context = context;
        this.templateList = templateList;

        this.bookOrderByTemplateListener = bookOrderByTemplateListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_template_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.txtvw_templateName.setText(String.valueOf(templateList.get(position).getTemplateName()));

        String date=templateList.get(position).getCreatedDate();
        if(date.contains("T")){
            date=date.substring(0,date.indexOf("T"));
            date=  utility.chageDateFormat("yyyy-MM-dd",date,"dd-MM-yyyy");
        }


        holder.txtvw_templateDate.setText(date);
        //  Picasso.load(templateList.get(position).getImageName()).into( holder.imgvw_category);
    }

    @Override
    public int getItemCount() {
        //     return MenuItem.length;
        return templateList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtvw_templateName;
        public TextView txtvw_templateDate;
        Button button_book_order, button_Delete;

        public MyViewHolder(View view) {
            super(view);
            txtvw_templateName = view.findViewById(R.id.txtvw_templateName);
            txtvw_templateDate = view.findViewById(R.id.txtvw_templateDate);

            button_book_order = view.findViewById(R.id.button_book_order);
            button_Delete = view.findViewById(R.id.button_Delete);

            button_Delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    bookOrderByTemplateListener.onDeleteTemplateClick(String.valueOf(templateList.get(getAdapterPosition()).getId()));
                }
            });
            button_book_order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    bookOrderByTemplateListener.onBookOrderClick(templateList.get(getAdapterPosition()).getOrderId());
                }
            });

        }


    }
}

