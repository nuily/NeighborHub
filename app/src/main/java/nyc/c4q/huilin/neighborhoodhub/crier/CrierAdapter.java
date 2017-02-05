package nyc.c4q.huilin.neighborhoodhub.crier;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import nyc.c4q.huilin.neighborhoodhub.R;
import nyc.c4q.huilin.neighborhoodhub.model.CrierPosts.CrierPost;

/**
 * Created by rook on 2/4/17.
 */

public class CrierAdapter extends RecyclerView.Adapter {

    ArrayList<CrierPost> postList = new ArrayList<>();

    public CrierAdapter(ArrayList<CrierPost> crierPosts) {
        postList = crierPosts;
    }

    public void setData(ArrayList<CrierPost> postList) {
        this.postList = postList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new CrierViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CrierViewHolder viewHolder = (CrierViewHolder) holder;
        CrierPost crierPost = postList.get(position);
        viewHolder.bind(crierPost);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }


}
