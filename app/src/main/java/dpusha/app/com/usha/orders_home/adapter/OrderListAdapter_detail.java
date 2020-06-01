package dpusha.app.com.usha.orders_home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dpusha.app.com.usha.R;
import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.orders_home.model.Array_data_Model;

public class OrderListAdapter_detail extends RecyclerView.Adapter<OrderListAdapter_detail.MyViewHolder> implements Filterable {


//start

    List<Array_data_Model> List_order;
    List<Array_data_Model> All_List_order;
    Context context;
    LinearLayout l_detail_list;
    private MainListner listenerMainActivity;
    private FragmentActivity activity;
    FragmentManager fragmentManager ;



    public OrderListAdapter_detail(Context context, List<Array_data_Model> List_order) {
        this.context = context;
        this.List_order = List_order;
        this.All_List_order=new ArrayList<>(List_order);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_orderlist_details, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int i) {



        final String sku= String.valueOf((List_order.get(i).getSKU()));

        final String descrip= String.valueOf((List_order.get(i).getDescription()));

        final String quantity= String.valueOf((List_order.get(i).getQuantity()));

        holder.index_no.setText(String.valueOf(i + 1));
        holder.sku.setText((sku));

        holder.descr.setText((descrip));










    }




    @Override
    public int getItemCount() {
        return List_order.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<Array_data_Model> filterList = new ArrayList<>();

            if(constraint.toString().isEmpty()){
                filterList.addAll(All_List_order);

            }/*else{

                *//*for(OrderList List_order :All_List_order){

                    if(List_order.toString().toLowerCase().contains(constraint.toString().toLowerCase())){
                        filterList.add(List_order);
                    }
                }*//*


                for(OrderList List_order :All_List_order){

                   String ship_party= List_order.getShipToParty().toLowerCase();
                    String ref_no= List_order.getOrderId().toLowerCase();
                    String order_status01=String.valueOf(List_order.getOrderStatus()).toLowerCase();
                    String created_date01 = String.valueOf(List_order.getCreatedDate()).toLowerCase();

                    String create_date1[]= created_date01.split("t");
                    String create_date2= utility.covertDateAndTimeFormat( create_date1[0]);
                   *//* String status = List_order.getOrderStatus().toLowerCase();


                   Log.d("Test",status);*//*

                   //Request date

                    String request_date = String.valueOf(List_order.getRequestDeliveryDate()).toLowerCase();

                    String request_date1[]=request_date.split("t");

                    String request_date2= utility.covertDateAndTimeFormat(request_date1[0]);



                    Log.d("Test",request_date2);

                    if(ship_party.contains(constraint.toString().toLowerCase())|| ref_no.contains(constraint.toString().toLowerCase()) || order_status01.contains(constraint.toString().toLowerCase())|| request_date2.contains(constraint.toString().toLowerCase()) || create_date2.contains(constraint.toString().toLowerCase())){

                    *//*if(request_date2.contains(constraint.toString().toLowerCase())){*//*
                        filterList.add(List_order);





                    }



                }



                *//*for(int i=0;i<All_List_order.size();i++){

                    final String reference_no=String.valueOf(List_order.get(i).getOrderId());

//                  String list01= All_List_order.get(i).toString().toLowerCase();
                    String ship_to_party=String.valueOf(List_order.get(i).getShipToParty()).toLowerCase();

                   if(ship_to_party.contains(constraint.toString().toLowerCase())) {

                        filterList.add(All_List_order.get(i));
                   }
                }*//*


                }*/


                FilterResults filterResults = new FilterResults();

                filterResults.values=filterList;
                return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            List_order.clear();
          //  List_order.addAll((Collection<? extends OrderList>) results.values);
            List_order.addAll((Collection<? extends Array_data_Model>) results.values);
            notifyDataSetChanged();

        }
    };

    public class MyViewHolder extends RecyclerView.ViewHolder {

       // TextView Task, Client, JobName, Job_desc, Hours, Secondary_Tech, Checklist;

            Button index_no;
            TextView sku,descr,quant;


        public MyViewHolder(View convertview) {
            super(convertview);

            index_no=convertview.findViewById(R.id.serial_no);

            sku= convertview.findViewById(R.id.sku);

            descr= convertview.findViewById(R.id.description);


            //quant= convertview.findViewById(R.id.)
        }
    }




    //End


}
