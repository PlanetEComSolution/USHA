package dpusha.app.com.usha.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dpusha.app.com.usha.R;
import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.model.ProductCatalougeList;
import dpusha.app.com.usha.model.SchemeByUser;

public class SchemeByUserAdapter extends RecyclerView.Adapter<SchemeByUserAdapter.MyViewHolder> {
    private List<SchemeByUser> schemeList;
    MainListner listenerMainActivity;
    private Context context;


    public SchemeByUserAdapter(Context context, List<SchemeByUser> schemeList, MainListner listenerMainActivity){

        this.context = context;
        this.schemeList = schemeList;
        this.listenerMainActivity = listenerMainActivity;

    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_scheme, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    holder.name.setText(schemeList.get(position).getDivName());
    holder.scheme.setText(schemeList.get(position).getSchemeName());
    }

    @Override
    public int getItemCount() {
        return schemeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView scheme;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.item_name);
            scheme=itemView.findViewById(R.id.item_scheme);
        }
    }
}
