package nyc.c4q.huilin.neighborhoodhub;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.client.Firebase;

public class DisplayActivity extends AppCompatActivity implements LocationFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addLocationFragment();
        Firebase.setAndroidContext(this);
    }

    private void addLocationFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_display, LocationFragment.newInstance())
                .commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
