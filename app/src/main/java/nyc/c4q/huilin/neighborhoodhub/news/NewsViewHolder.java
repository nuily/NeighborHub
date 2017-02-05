package nyc.c4q.huilin.neighborhoodhub.news;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import nyc.c4q.huilin.neighborhoodhub.R;
import nyc.c4q.huilin.neighborhoodhub.news.model.Complaint;

/**
 * Created by ashiquechowdhury on 2/5/17.
 */
public class NewsViewHolder extends RecyclerView.ViewHolder{
    TextView mComplaintTextV;
    TextView mDescriptionTextV;
    TextView mResolutionTextV;

    public NewsViewHolder(View itemView) {
        super(itemView);
        mComplaintTextV = (TextView) itemView.findViewById(R.id.complaint_type_textview);
        mDescriptionTextV = (TextView) itemView.findViewById(R.id.description_textview);
        mResolutionTextV = (TextView) itemView.findViewById(R.id.resolution_description);

    }

    public void bind(Complaint complaint) {
        mComplaintTextV.setText(complaint.getComplaint_type());
        mDescriptionTextV.setText(complaint.getDescriptor());
        mResolutionTextV.setText(complaint.getResolution_description());
    }
}
