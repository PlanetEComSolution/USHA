package dpusha.app.com.usha.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dpusha.app.com.usha.R;
import dpusha.app.com.usha.fragment.feedback.FeedbackDetailFragment;
import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.model.Feedback;
import dpusha.app.com.usha.model.feedback_data.FeedbackDetail;
import dpusha.app.com.usha.orders_home.util.utility;


public class FeedbackListAdapter extends RecyclerView.Adapter<FeedbackListAdapter.MyViewHolder> {


    List<Feedback> feedbackList;

    Context context;
    private MainListner listenerMainActivity;

    public FeedbackListAdapter(Context context, List<Feedback> feedbackList, MainListner listenerMainActivity) {
        this.context = context;
        this.feedbackList = feedbackList;
        this.listenerMainActivity = listenerMainActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_feedback_item, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int i) {

        holder.ticket_code.setText(feedbackList.get(i).getTicketCode());
        holder.current_status.setText(feedbackList.get(i).getCurrentFeedbackStatus());

        if (feedbackList.get(i).getEstimatedDate() == null)
            holder.estimated_date.setText("N/A");
        else {
            String date=feedbackList.get(i).getEstimatedDate();
            if(date.contains("T")){
                date=date.substring(0,date.indexOf("T"));
                date=  utility.chageDateFormat("yyyy-MM-dd",date,"dd-MM-yyyy");
            }


            holder.estimated_date.setText(date);
        }
    }


    @Override
    public int getItemCount() {
        return feedbackList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        Button index_no;
        TextView estimated_date, current_status, ticket_code;


        public MyViewHolder(View convertview) {
            super(convertview);

            index_no = (Button) convertview.findViewById(R.id.serial_no);
            estimated_date = convertview.findViewById(R.id.estimated_date);
            current_status = convertview.findViewById(R.id.current_status);
            ticket_code = convertview.findViewById(R.id.ticket_code);

            convertview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle b=new Bundle();
                    b.putString("id",feedbackList.get(getAdapterPosition()).getId()+"");
                    Fragment fragment=new FeedbackDetailFragment();
                    fragment.setArguments(b);
                    listenerMainActivity.addFragment(fragment,"FeedbackDetailFragment",true);
                }
            });
        }
    }

}
