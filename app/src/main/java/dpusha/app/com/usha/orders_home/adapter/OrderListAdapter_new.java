package dpusha.app.com.usha.orders_home.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dpusha.app.com.usha.R;
import dpusha.app.com.usha.activity.DrawerMainActivity;
//import dpusha.app.com.usha.fragment.cart.CartFragment;
import dpusha.app.com.usha.fragment.cart.PlaceOrder;
import dpusha.app.com.usha.fragment.orders.Order_List_Details;
import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.orders_home.model.OrderList;
import dpusha.app.com.usha.orders_home.util.Constants;
import dpusha.app.com.usha.orders_home.util.utility;

public class OrderListAdapter_new extends RecyclerView.Adapter<OrderListAdapter_new.MyViewHolder> implements Filterable {


//start

    List<OrderList> List_order;
    List<OrderList> All_List_order;
    Context context;
    LinearLayout l_detail_list;
    private MainListner listenerMainActivity;
    private FragmentActivity activity;
    FragmentManager fragmentManager;


    public OrderListAdapter_new(Context context, List<OrderList> List_order, MainListner listenerMainActivity) {
        this.context = context;
        this.List_order = List_order;
        this.All_List_order = new ArrayList<>(List_order);
        this.listenerMainActivity = listenerMainActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
               // .inflate(R.layout.recycler_orderlist_new, parent, false);

 .inflate(R.layout.recycler_order_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int i) {


        final String created_date = String.valueOf(List_order.get(i).getCreatedDate());

        String create_date1[] = created_date.split("T");


        String create_date2 = utility.covertDateAndTimeFormat(create_date1[0]);

        final String request_date = String.valueOf(List_order.get(i).getRequestDeliveryDate());

        String request_date1[] = request_date.split("T");

        String request_date2 = utility.covertDateAndTimeFormat(request_date1[0]);


        final String order_status = String.valueOf(List_order.get(i).getOrderStatus());

        final String ship_to_party = String.valueOf(List_order.get(i).getShipToParty());

        final String reference_no = String.valueOf(List_order.get(i).getOrderId());

        //  final String sap_order_no;


        holder.l_detail_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                b.putString("id", reference_no);
                b.putBoolean(Constants.FROM_PLACED_ORDER, true);
               if(List_order.get(i).getOrderStatus().equals("Progress in ERP")){
                   b.putBoolean("isOrderEditable", false);
               }else {
                   b.putBoolean("isOrderEditable", true);
               }
               // Order_List_Details fr = new Order_List_Details();
               // fr.setArguments(b);
               // listenerMainActivity.addFragment(fr, "Order_List_Details", true);

                Fragment fragment=new PlaceOrder();
                fragment.setArguments(b);
                listenerMainActivity.addFragment(fragment, "PlaceOrder", true);
            }
        });

        holder.create_date.setText(create_date2);


        // holder.request_date.setText(request_date2);

        if (order_status.equals("null")) {
            holder.order_status.setText("");
        } else {
            holder.order_status.setText(order_status);

        }

        // holder.ship_to_party.setText(ship_to_party);
        holder.reference_no.setText(reference_no);

        holder.index_no.setText(String.valueOf(i + 1));

        // on click for layout


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

            List<OrderList> filterList = new ArrayList<>();

            if (constraint.toString().isEmpty()) {
                filterList.addAll(All_List_order);

            } else {

                /*for(OrderList List_order :All_List_order){

                    if(List_order.toString().toLowerCase().contains(constraint.toString().toLowerCase())){
                        filterList.add(List_order);
                    }
                }*/


                for (OrderList List_order : All_List_order) {

                    String ship_party = List_order.getShipToParty().toLowerCase();
                    String ref_no = List_order.getOrderId().toLowerCase();
                    String order_status01 = String.valueOf(List_order.getOrderStatus()).toLowerCase();
                    String created_date01 = String.valueOf(List_order.getCreatedDate()).toLowerCase();

                    String create_date1[] = created_date01.split("t");
                    String create_date2 = utility.covertDateAndTimeFormat(create_date1[0]);
                   /* String status = List_order.getOrderStatus().toLowerCase();


                   Log.d("Test",status);*/

                    //Request date

                    String request_date = String.valueOf(List_order.getRequestDeliveryDate()).toLowerCase();

                    String request_date1[] = request_date.split("t");

                    String request_date2 = utility.covertDateAndTimeFormat(request_date1[0]);


                    Log.d("Test", request_date2);

                    if (ship_party.contains(constraint.toString().toLowerCase()) || ref_no.contains(constraint.toString().toLowerCase()) || order_status01.contains(constraint.toString().toLowerCase()) || request_date2.contains(constraint.toString().toLowerCase()) || create_date2.contains(constraint.toString().toLowerCase())) {

                        /*if(request_date2.contains(constraint.toString().toLowerCase())){*/
                        filterList.add(List_order);


                    }


                }



                /*for(int i=0;i<All_List_order.size();i++){

                    final String reference_no=String.valueOf(List_order.get(i).getOrderId());

//                  String list01= All_List_order.get(i).toString().toLowerCase();
                    String ship_to_party=String.valueOf(List_order.get(i).getShipToParty()).toLowerCase();

                   if(ship_to_party.contains(constraint.toString().toLowerCase())) {

                        filterList.add(All_List_order.get(i));
                   }
                }*/


            }


            FilterResults filterResults = new FilterResults();

            filterResults.values = filterList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            List_order.clear();
            List_order.addAll((Collection<? extends OrderList>) results.values);
            notifyDataSetChanged();

        }
    };

    public class MyViewHolder extends RecyclerView.ViewHolder {

        // TextView Task, Client, JobName, Job_desc, Hours, Secondary_Tech, Checklist;

        Button index_no;
        TextView create_date, request_date, order_status, ship_to_party, reference_no;
        LinearLayout l_detail_list;

        public MyViewHolder(View convertview) {
            super(convertview);

           /* index_no = (Button) convertview.findViewById(R.id.serial_no);
            Task = (TextView) convertview.findViewById(R.id.Task);*/
            l_detail_list = convertview.findViewById(R.id.l_detail_list);
            index_no = (Button) convertview.findViewById(R.id.serial_no);
            create_date = convertview.findViewById(R.id.Creates_date);
            // request_date= convertview.findViewById(R.id.RDelivery_date);

            order_status = convertview.findViewById(R.id.order_status);
            // ship_to_party= convertview.findViewById(R.id.ship_to_party);
            reference_no = convertview.findViewById(R.id.reference_no);

        }
    }


    //End


}
