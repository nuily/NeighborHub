package nyc.c4q.huilin.neighborhoodhub;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity implements LocationFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addLocationFragment();
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
