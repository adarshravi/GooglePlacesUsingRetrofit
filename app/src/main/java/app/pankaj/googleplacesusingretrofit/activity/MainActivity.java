package app.pankaj.googleplacesusingretrofit.activity;

import android.location.LocationManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import app.pankaj.googleplacesusingretrofit.R;
import app.pankaj.googleplacesusingretrofit.fragment.DataFragment;


public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        DataFragment dataFragment=new DataFragment();
        fragmentTransaction.replace(R.id.linearParent,dataFragment);
        fragmentTransaction.commit();
    }



}
