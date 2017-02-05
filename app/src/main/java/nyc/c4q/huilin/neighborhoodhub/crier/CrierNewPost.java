package nyc.c4q.huilin.neighborhoodhub.crier;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import nyc.c4q.huilin.neighborhoodhub.R;
import nyc.c4q.huilin.neighborhoodhub.model.CrierPosts.CrierPost;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by rook on 2/4/17.
 */

public class CrierNewPost extends Fragment {

    View view;
    EditText etNewPostTitle;
    EditText etNewPostBody;
    TextView tvSavePost;
    FirebaseAuth firebaseAuth;
    String user;

    public static CrierNewPost newInstance() {
        CrierNewPost crierNewPost= new CrierNewPost();
        Bundle args = new Bundle();
        crierNewPost.setArguments(args);
        return crierNewPost;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_new_post, container, false);
        etNewPostTitle = (EditText) view.findViewById(R.id.et_new_post_title);
        etNewPostBody = (EditText) view.findViewById(R.id.et_new_post_body);
        tvSavePost = (TextView) view.findViewById(R.id.tv_save_post);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            user = "User";
        } else {
            user = firebaseAuth.getCurrentUser().getDisplayName();
        }

        tvSavePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etNewPostTitle.getText() == null && etNewPostBody == null) {
                    Toast.makeText(getContext(), "Title and body are empty!", Toast.LENGTH_SHORT).show();
                } else if (etNewPostTitle == null) {
                    Toast.makeText(getContext(), "Title is empty!", Toast.LENGTH_SHORT).show();
                } else if (etNewPostBody == null) {
                    Toast.makeText(getContext(), "Post body is empty!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Post saved.", Toast.LENGTH_SHORT).show();
                    cupboard().withDatabase(CrierRecyclerFragment.database)
                            .put(new CrierPost(
                                    etNewPostTitle.getText().toString(),
                                    etNewPostBody.getText().toString(),
                                    user,
                                    0
                            ));
                    getActivity().onBackPressed();
                }
            }
        });
    }
}
