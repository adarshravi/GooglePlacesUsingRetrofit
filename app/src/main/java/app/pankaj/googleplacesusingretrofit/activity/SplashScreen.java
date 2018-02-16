package app.pankaj.googleplacesusingretrofit.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

import app.pankaj.googleplacesusingretrofit.LoginActivity;
import app.pankaj.googleplacesusingretrofit.Navigation;
import app.pankaj.googleplacesusingretrofit.R;

public class SplashScreen extends AppCompatActivity {
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {



            @Override
            public void run() {

                pref=getSharedPreferences("data",MODE_PRIVATE);
                if(pref.contains("email") && pref.contains("password")){
                    startActivity(new Intent(SplashScreen.this,Navigation.class));
                    finish();
                }


               else{
                   Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(i);




                finish();
                }
            }
        }, SPLASH_TIME_OUT);
    }

}