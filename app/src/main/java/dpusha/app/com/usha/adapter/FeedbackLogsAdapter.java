package dpusha.app.com.usha.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dpusha.app.com.usha.R;
import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.model.feedback_data.FeedbackLog;
import dpusha.app.com.usha.orders_home.util.utility;


public class FeedbackLogsAdapter extends RecyclerView.Adapter<FeedbackLogsAdapter.MyViewHolder> {


    List<FeedbackLog> feedbackList;

    Context context;
    private MainListner listenerMainActivity;

    public FeedbackLogsAdapter(Context context, List<FeedbackLog> feedbackList, MainListner listenerMainActivity) {
        this.context = context;
        this.feedbackList = feedbackList;
        this.listenerMainActivity = listenerMainActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                //    .inflate(R.layout.recycler_feedback_log_item, parent, false);
                .inflate(R.layout.recycler_feedback_log_item_new, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int i) {

        if (feedbackList.get(i).getAssignedUserRemarks() == null) {
            holder.remarks.setText("N/A");
        } else {
            holder.remarks.setText(String.valueOf(feedbackList.get(i).getAssignedUserRemarks()));
        }

        holder.status.setText(feedbackList.get(i).getFeedbackStatus());
         holder. index_no.setText(String.valueOf(i+1));


        if (feedbackList.get(i).getETR() == null)
            holder.etr.setText("N/A");
        else {
            String date = feedbackList.get(i).getETR();
            if (date.contains("T")) {
                date = date.substring(0, date.indexOf("T"));
                date = utility.chageDateFormat("yyyy-MM-dd", date, "dd-MM-yyyy");
            }
            holder.etr.setText(date);
        }


      //  holder.Pending_with.setText(feedbackList.get(i).getPendingEmployeeName());


        if (feedbackList.get(i).getPendingEmployeeName() == null  || feedbackList.get(i).getPendingEmployeeName().equals("")) {
            holder.Pending_with.setText("N/A");
        } else {
            holder.Pending_with.setText(String.valueOf(feedbackList.get(i).getPendingEmployeeName()));
        }


        if (feedbackList.get(i).getCreatedDate() == null)
            holder.date.setText("N/A");
        else {
            String date = feedbackList.get(i).getCreatedDate();
            if (date.contains("T")) {
                date = date.substring(0, date.indexOf("T"));
                date = utility.chageDateFormat("yyyy-MM-dd", date, "dd-MM-yyyy");
            }


            holder.date.setText(date);
        }
    }


    @Override
    public int getItemCount() {
        return feedbackList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        Button index_no;
        TextView remarks, status, etr, Pending_with, date;


        public MyViewHolder(View convertview) {
            super(convertview);

            index_no = (Button) convertview.findViewById(R.id.serial_no);
            remarks = convertview.findViewById(R.id.remarks);
            status = convertview.findViewById(R.id.status);
            etr = convertview.findViewById(R.id.etr);
            Pending_with = convertview.findViewById(R.id.Pending_with);
            date = convertview.findViewById(R.id.date);


        }
    }

}
