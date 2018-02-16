package app.pankaj.googleplacesusingretrofit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import app.pankaj.googleplacesusingretrofit.helper.SessionManager;


public class LoginActivity extends AppCompatActivity {

    private static final String TAG = RegisterActivity.class.getSimpleName();
    private Button btnLogin;
    private EditText inputEmail;
    private EditText inputPassword;
    private SessionManager session;
    public static final String LoggedInPreferences = "LoggedInData" ;
    SharedPreferences sharedpreferences ,prefs;
    //Signin constant to check the activity result

    String oldName, oldPass;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inputEmail= (EditText) findViewById(R.id.et_login_email);
        inputPassword = (EditText) findViewById(R.id.et_login_password);


        TextView register = (TextView) findViewById(R.id.tv_register_new);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });

        btnLogin = (Button) findViewById(R.id.et_login_button);
        sharedpreferences = getSharedPreferences(LoggedInPreferences, Context.MODE_PRIVATE);

        session = new SessionManager(getApplicationContext());

        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(LoginActivity.this, Navigation.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.animation, R.anim.animation2);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String email = inputEmail.getText().toString().trim().toLowerCase();
                String password = inputPassword.getText().toString().trim().toLowerCase();
                pref =getSharedPreferences("data",Context.MODE_PRIVATE);
                oldName=pref.getString("email","NotFound");
                oldPass =pref.getString("password","NoPassword");

                if(TextUtils.isEmpty(email)){

                    inputEmail.setError("Please Enter Email Or Username");
                }
                if(TextUtils.isEmpty(password)){
                    inputPassword.setError("Please Enter The Password");
                }
                else{
                    if(oldName.equalsIgnoreCase(email)&&oldPass.equalsIgnoreCase(password)){
                        Intent i = new Intent(LoginActivity.this, Navigation.class);
                        startActivity(i);
                        finish();

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Please enter valid email address and password ",Toast.LENGTH_LONG).show();


                    }

                }



            }

        });



    }
}
