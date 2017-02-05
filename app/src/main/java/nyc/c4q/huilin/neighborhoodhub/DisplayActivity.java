package nyc.c4q.huilin.neighborhoodhub;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import nyc.c4q.huilin.neighborhoodhub.chat.ChatFragment;
import nyc.c4q.huilin.neighborhoodhub.crier.CrierRecyclerFragment;

public class DisplayActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView mNavHeaderMainTextV;
    String username;

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
        View view = myNavigationList.getHeaderView(0);
        mNavHeaderMainTextV = (TextView)  view.findViewById(R.id.nav_header_username_textview);
        username = getIntent().getStringExtra("nyc.c4q.USERNAME");
        mNavHeaderMainTextV.setText(username);

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
                break;
            case R.id.nav_profile:
                addProfileFragment();
                break;
            case R.id.nav_settings:
                break;
            case R.id.nav_chat:
                addChatFragment();
                break;
            case R.id.nav_logout:
                Intent intent = new Intent(DisplayActivity.this, LoginActivity.class);
                intent.putExtra("nyc.c4q.LOGOUT", true);
                startActivity(intent);
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

    private void addProfileFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_display, ProfileFragment.newInstance())
                .commit();
    }

    private void addChatFragment(){
        ChatFragment myFrag = new ChatFragment();
        Bundle bundle = new Bundle();
        bundle.putString("nyc.c4q.USERNAME", username);
        myFrag.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_display, myFrag)
                .commit();
    }
}
