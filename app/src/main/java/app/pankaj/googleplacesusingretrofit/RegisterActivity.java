package app.pankaj.googleplacesusingretrofit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.pankaj.googleplacesusingretrofit.helper.SessionManager;
import app.pankaj.googleplacesusingretrofit.helper.User;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    private Button btnRegister;
    SharedPreferences prefs;
    private EditText input_name;
    private EditText  inputEmail;
    private EditText inputPassword,confirmPassword;
    private SessionManager session;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String name,email,password;
    SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputEmail = (EditText) findViewById(R.id.et_user_email);
        inputPassword = (EditText) findViewById(R.id.et_user_pass);
        confirmPassword=(EditText)findViewById(R.id.et_user_confirm_pass);
        input_name=(EditText)findViewById(R.id.et_name);
        btnRegister = (Button) findViewById(R.id.btn_user_register);
        session = new SessionManager(getApplicationContext());

        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(RegisterActivity.this,
                    Navigation.class);
            startActivity(intent);
            finish();
        }



        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                name = input_name.getText().toString().toLowerCase();

                email = inputEmail.getText().toString().trim().toLowerCase();
                password = inputPassword.getText().toString().trim().toLowerCase();
                String conpassword = confirmPassword.getText().toString().toLowerCase();
                if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"please fill all the details",Toast.LENGTH_LONG).show();


                }
                else
                if (!(password.equals(conpassword))) {

                    Toast.makeText(getApplicationContext(),"Password Mismatch",Toast.LENGTH_LONG).show();

                }

                else

                if (!(email.matches(emailPattern) && email.length() > 0)) {

                    Toast.makeText(getApplicationContext(),"Please enter valid email address",Toast.LENGTH_LONG).show();
                }
                else {

                    pref =getSharedPreferences("data", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor= pref.edit();
                    editor.putString("email",email);
                    editor.putString("password",password);
                    editor.commit();
//                    Toast.makeText(getApplicationContext(),"Please enter valid email address",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this, Navigation.class);
                    startActivity(intent);
                    finish();


                }


            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent nextActivity = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(nextActivity);
        finish();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);


    }




}

