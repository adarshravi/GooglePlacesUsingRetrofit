package app.pankaj.googleplacesusingretrofit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import app.pankaj.googleplacesusingretrofit.fragment.DataFragment;
import app.pankaj.googleplacesusingretrofit.helper.CommonUtils;
import app.pankaj.googleplacesusingretrofit.navigation.AboutUs;
import app.pankaj.googleplacesusingretrofit.navigation.Logout;

public class Navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String LoggedInPreferences = "LoggedInData" ;
    Switch switchHindi;
    SharedPreferences sharedpreferences;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        DataFragment dataFragment=new DataFragment();
        fragmentTransaction.replace(R.id.screen_area,dataFragment);
//        fragmentTransaction.replace(R.id.linearParent,dataFragment);
        fragmentTransaction.commit();

        sharedpreferences = getSharedPreferences(LoggedInPreferences, Context.MODE_PRIVATE);
        navigationView= (NavigationView) findViewById(R.id.nav_view);
        View view=navigationView.getMenu().getItem(2).getActionView();
        switchHindi= (Switch) view.findViewById(R.id.switchHindi);
        switchHindi.setChecked(sharedpreferences.getBoolean("hindiLanguage",false));
        switchHindi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e("value=====","val========"+isChecked);
                SharedPreferences.Editor et=sharedpreferences.edit();
                et.putBoolean("hindiLanguage",isChecked);
                et.commit();

                startActivity(new Intent(Navigation.this,Navigation.class));
                finish();
            }
        });
        String language="en";
        if(sharedpreferences.getBoolean("hindiLanguage",false))
            language="hi";
        CommonUtils.setLanguage(this,language);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment=null;
        int id = item.getItemId();

        if (id == R.id.nav_aboutus) {
            fragment = new AboutUs();

        } else if (id == R.id.nav_logut) {
            fragment = new Logout();



        }
        if (fragment !=null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.screen_area,fragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
