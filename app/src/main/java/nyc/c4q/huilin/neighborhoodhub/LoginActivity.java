package nyc.c4q.huilin.neighborhoodhub;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.net.URI;

/**
 * Created by rook on 1/29/17.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LocationFragment.OnFragmentInteractionListener {

    ImageView profileImage;
    TextView googleLogin;
    TextView facebookLogin;
    URI userProfilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        profileImage = (ImageView) findViewById(R.id.iv_login);
        googleLogin = (TextView) findViewById(R.id.tv_google_login);
        facebookLogin = (TextView) findViewById(R.id.tv_facebook_login);
        userProfilePic = null;

    }

    @Override
    protected void onResume() {
        super.onResume();

        Picasso.with(this).load(R.drawable.default_profile).error(R.drawable.default_profile).into(profileImage);
        googleLogin.setOnClickListener(this);
        facebookLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tv_google_login:
                googleLoginProcess();
                break;
            case R.id.tv_facebook_login:
                facebookLoginProcess();
                break;
            default:
                break;
        }

    }

    private void facebookLoginProcess() {
        Toast.makeText(this, "Logging in using Facebook", Toast.LENGTH_SHORT).show();
    }

    private void googleLoginProcess() {
        Toast.makeText(this, "Logging in using Google", Toast.LENGTH_SHORT).show();
        startLocationServices();
    }

    private void startLocationServices() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, LocationFragment.newInstance())
                .commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}