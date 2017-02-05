package nyc.c4q.huilin.neighborhoodhub;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import nyc.c4q.huilin.neighborhoodhub.crier.CrierRecyclerFragment;

public class DisplayActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpDrawerToggle();

        addCrierFragment();
    }

    private void setUpDrawerToggle() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        ActionBarDrawerToggle navigationActionBar = new ActionBarDrawerToggle(this, drawerLayout, myToolbar,
                R.string.navigation_toolbar_open, R.string.navigation_toolbar_close);
        navigationActionBar.syncState();

        NavigationView myNavigationList = (NavigationView) findViewById(R.id.nav_view);
        myNavigationList.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id){
            case R.id.nav_news:

                break;
            case R.id.nav_forums:
                addCrierFragment();
                break;
            case R.id.nav_tools:
                Log.d("TAG", "Clickedy Click");
                break;
            case R.id.nav_profile:
                Log.d("TAG", "Clickedy Click");
                break;
            case R.id.nav_settings:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START, true);
        return true;
    }

    private void addCrierFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_display, CrierRecyclerFragment.newInstance())
                .commit();
    }
}
