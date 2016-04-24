package com.adityaladwa.letsalert;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    public static final String TAG = MainActivity.class.getSimpleName();

    SharedPreferences mSharedPreferences;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupDrawer();
        mSharedPreferences = ((App) getApplication()).getNetComponent().getSharedPreference();

        boolean login = mSharedPreferences.getBoolean(getString(R.string.pref_login), false);
        if (!login) {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
            finish();
        }
        if (findViewById(R.id.main_frame_container) != null) {
            Log.d(TAG, "Frame container present");
            bundle = new Bundle();
            bundle.putString(getString(R.string.bundle_fragment), getString(R.string.bundle_main));
            startFragment(bundle);
        }

    }

    private void startFragment(Bundle bundle) {
        Fragment fragment = new MainFragment();
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container, fragment).commit();
    }

    private void setupDrawer() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            bundle.putString(getString(R.string.bundle_fragment), getString(R.string.bundle_police));
            startFragment(bundle);
        } else if (id == R.id.nav_gallery) {
            bundle.putString(getString(R.string.bundle_fragment), getString(R.string.bundle_electricity));
            startFragment(bundle);

        } else if (id == R.id.nav_slideshow) {
            bundle.putString(getString(R.string.bundle_fragment), getString(R.string.bundle_water));
            startFragment(bundle);

        } else if (id == R.id.nav_manage) {
            bundle.putString(getString(R.string.bundle_fragment), getString(R.string.bundle_college));
            startFragment(bundle);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
