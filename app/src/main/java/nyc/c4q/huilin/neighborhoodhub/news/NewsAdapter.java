package nyc.c4q.huilin.neighborhoodhub.news;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import nyc.c4q.huilin.neighborhoodhub.R;
import nyc.c4q.huilin.neighborhoodhub.news.model.Complaint;

/**
 * Created by ashiquechowdhury on 2/5/17.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder>{
    List<Complaint> complaintList;

    public NewsAdapter(List<Complaint> complaintList) {

        this.complaintList = complaintList;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.news_row, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.bind(complaintList.get(position));
    }

    @Override
    public int getItemCount() {
        return complaintList.size();
    }
}
