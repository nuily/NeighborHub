package nyc.c4q.huilin.neighborhoodhub;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private final String TAG = this.getClass().getSimpleName();
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth firebaseAuth;
    private Uri gPhotoUrl;
    private String gmail;
    private String googleName;
    private View view;
    private TextView nameTV;
    private ImageView profileIV;
    private TextView gmailTV;

    public ProfileFragment() {
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        authListener = getAuthStateListener();
    }

    @NonNull
    private FirebaseAuth.AuthStateListener getAuthStateListener() {
        return new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    googleName = user.getDisplayName();
                    gmail = user.getEmail();
                    gPhotoUrl = user.getPhotoUrl();
                    Picasso.with(getContext()).load(gPhotoUrl).error(R.drawable.fbook_icon).into(profileIV);
                    nameTV.setText(googleName);
                    gmailTV.setText(gmail);
                } else {
                    googleName = "Person's Name";
                    gmail = "person@gmail.com";
                }


            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        profileIV = (ImageView) view.findViewById(R.id.iv_profpic);
        nameTV = (TextView) view.findViewById(R.id.tv_person_name);
        gmailTV = (TextView) view.findViewById(R.id.tv_gmail);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            firebaseAuth.removeAuthStateListener(authListener);
        }
    }
}
