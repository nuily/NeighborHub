package nyc.c4q.huilin.neighborhoodhub;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import nyc.c4q.huilin.neighborhoodhub.crier.CrierRecyclerFragment;

public class DisplayActivity extends AppCompatActivity implements LocationFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragmentInstance(CrierRecyclerFragment.newInstance(0));
    }

    private void addFragmentInstance(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_display, fragment)
                .commit();
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
