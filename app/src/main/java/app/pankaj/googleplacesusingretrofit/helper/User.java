package app.pankaj.googleplacesusingretrofit.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;

import app.pankaj.googleplacesusingretrofit.RegisterActivity;

/**
 * Created by adarshraj on 15/02/18.
 */

public class User {
    private  String inputPass;
    private String  inputEmail;

    Context context;
    SharedPreferences sharedPreferences;
    public User(Context context) {
        this.context=context;
        sharedPreferences = context.getSharedPreferences("login_details",Context.MODE_PRIVATE);
    }

    public String getInputPass() {
       inputPass= sharedPreferences.getString(inputPass,"");
        return inputPass;
    }

    public void setInputPass(String inputPass) {
        this.inputPass = inputPass;
        sharedPreferences.edit().putString("password",inputPass).commit();
    }

    public String getInputEmail() {
        inputEmail= sharedPreferences.getString(inputEmail,"");
        return inputEmail;
    }

    public void setInputEmail(String inputEmail) {
        this.inputEmail = inputEmail;
        sharedPreferences.edit().putString("email",inputEmail).commit();
    }
    public void removeUser()
    {
        sharedPreferences.edit().clear().commit();
    }
}
