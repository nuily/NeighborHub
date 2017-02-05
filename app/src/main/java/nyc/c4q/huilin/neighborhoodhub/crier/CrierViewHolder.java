package nyc.c4q.huilin.neighborhoodhub.crier;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import nyc.c4q.huilin.neighborhoodhub.R;
import nyc.c4q.huilin.neighborhoodhub.model.CrierPosts.CrierPost;

/**
 * Created by rook on 2/4/17.
 */

public class CrierViewHolder extends RecyclerView.ViewHolder {

    private CardView cvPostItem;
    private LinearLayout llPostCardMain;
    private FrameLayout flFragmentHolder;
    private TextView tvPostTitle;
    private TextView tvPostDescription;
    private TextView tvCardPostedBy;
    private TextView tvNumberPledgedCard;

    public CrierViewHolder(View itemView) {
        super(itemView);
        cvPostItem = (CardView) itemView.findViewById(R.id.cv_post_item);
        llPostCardMain = (LinearLayout) itemView.findViewById(R.id.ll_post_card_main);
        flFragmentHolder = (FrameLayout) itemView.findViewById(R.id.fl_fragment_holder);
        tvPostTitle = (TextView) itemView.findViewById(R.id.tv_card_title);
        tvPostDescription = (TextView) itemView.findViewById(R.id.tv_short_desc);
        tvCardPostedBy = (TextView) itemView.findViewById(R.id.tv_card_posted_by);
        tvNumberPledgedCard = (TextView) itemView.findViewById(R.id.tv_number_pledged_card);

    }

    public void bind(final CrierPost post) {
        tvPostTitle.setText(post.getTitle());
        tvPostDescription.setText(post.getDescription());
        tvCardPostedBy.setText(post.getUser());
        tvNumberPledgedCard.setText(post.getSupporters() + " Neighbors have pledged their support!");
        cvPostItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("post", post);
                CrierFullFragment fragment = new CrierFullFragment();
                fragment.setArguments(bundle);

                ((FragmentActivity) view
                        .getContext())
                        .getFragmentManager()
                        .beginTransaction()
                        .replace(((ViewGroup) view.getParent().getParent()).getId(), fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
