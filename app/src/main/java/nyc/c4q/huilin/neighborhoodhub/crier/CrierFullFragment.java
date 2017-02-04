package nyc.c4q.huilin.neighborhoodhub.crier;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import nyc.c4q.huilin.neighborhoodhub.R;
import nyc.c4q.huilin.neighborhoodhub.model.CrierPosts.CrierPost;

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
        bundle.putSerializable("frag_ser_crier_post", crierPost);

        return fragment;
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

        loadPost((CrierPost) getArguments().getSerializable("frag_ser_crier_post"));
    }

    private void loadPost(CrierPost post) {
        crierPost = post;
        tvFullTitle.setText(post.getTitle());
        tvFullDesc.setText(post.getDescription());
        tvSupportersFull.setText(post.getSupporters() + " Neighbors have pledged support!");
    }

    @Override
    public void onClick(View view) {

        // TODO: 2/4/17 Set OnClickListeners for Pledge and Pass buttons
        switch (view.getId()) {
            case R.id.tv_pledge:
                Toast.makeText(view.getContext(), "Clicked Pledge", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_pass:
                Toast.makeText(view.getContext(), "Clicked Pass", Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
                break;
            default:
                break;
        }
    }
}
