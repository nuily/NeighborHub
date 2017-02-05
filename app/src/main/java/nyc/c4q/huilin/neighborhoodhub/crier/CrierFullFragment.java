package nyc.c4q.huilin.neighborhoodhub.crier;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import nl.qbusict.cupboard.Cupboard;
import nyc.c4q.huilin.neighborhoodhub.R;
import nyc.c4q.huilin.neighborhoodhub.model.CrierPosts.CrierPost;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by rook on 2/4/17.
 */

public class CrierFullFragment extends Fragment implements View.OnClickListener{

    View view;
    TextView tvFullTitle;
    TextView tvFullDesc;
    TextView tvPledge;
    TextView tvPass;
    TextView tvSupportersFull;

    public static CrierPost crierPost;

    public static CrierFullFragment newInstance(Serializable crierPost) {
        CrierFullFragment fragment = new CrierFullFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("post", crierPost);

        return fragment;
    }

    public CrierFullFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(
                R.layout.fragment_crier_full,
                container,
                false
        );
        initViews();
        return view;
    }

    private void initViews() {
        tvFullTitle = (TextView) view.findViewById(R.id.tv_full_title);
        tvFullDesc = (TextView) view.findViewById(R.id.tv_full_desc);
        tvPledge = (TextView) view.findViewById(R.id.tv_pledge);
        tvPass = (TextView) view.findViewById(R.id.tv_pass);
        tvSupportersFull = (TextView) view.findViewById(R.id.tv_number_pledged_full);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadPost((CrierPost) getArguments().getSerializable("post"));

        tvPledge.setOnClickListener(this);
        tvPass.setOnClickListener(this);
    }

    private void loadPost(CrierPost post) {
        crierPost = post;
        tvFullTitle.setText(post.getTitle());
        tvFullDesc.setText(post.getDescription());
        tvSupportersFull.setText(post.getSupporters() + " Neighbors have pledged support!");
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tv_pledge:

                Toast.makeText(
                        view.getContext(),
                        "You have pledged your support to " + crierPost.getUser() + "'s action!",
                        Toast.LENGTH_SHORT)
                        .show();

                Snackbar.make(view,
                        "You have pledged your support to " + crierPost.getUser() + "'s action!",
                        Snackbar.LENGTH_SHORT);

                int supporters = crierPost.getSupporters();
                cupboard().withDatabase(CrierRecyclerFragment.database).get(crierPost);
                cupboard().withDatabase(CrierRecyclerFragment.database).delete(crierPost);
                crierPost.setSupporters(supporters + 1);
                cupboard().withDatabase(CrierRecyclerFragment.database).put(crierPost);
                CrierRecyclerFragment.adapter.notifyDataSetChanged();
                getActivity().onBackPressed();
                break;
            case R.id.tv_pass:
                Toast.makeText(
                        view.getContext(),
                        "You have declined to support " + crierPost.getUser() + "'s action.",
                        Toast.LENGTH_SHORT)
                        .show();
                getActivity().onBackPressed();
                break;
            default:
                break;
        }
    }
}
