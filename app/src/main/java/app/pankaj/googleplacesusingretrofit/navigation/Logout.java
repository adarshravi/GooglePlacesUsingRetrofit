package app.pankaj.googleplacesusingretrofit.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import app.pankaj.googleplacesusingretrofit.LoginActivity;
import app.pankaj.googleplacesusingretrofit.Navigation;
import app.pankaj.googleplacesusingretrofit.R;
import app.pankaj.googleplacesusingretrofit.activity.SplashScreen;
import app.pankaj.googleplacesusingretrofit.helper.User;

/**
 * Created by adarshraj on 14/02/18.
 */

public class Logout extends Fragment {

    SharedPreferences pref;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_logout,null);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btn_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pref =getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
                boolean isEmailEmpty = pref.getString("email", "").isEmpty();
                boolean isPasswordEmpty =pref.getString("Password", "").isEmpty();
//                return isEmailEmpty || isPasswordEmpty;
                Toast.makeText(getActivity(),"You are logout",Toast.LENGTH_SHORT).show();


                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);

             //   ((Activity) getActivity()).overridePendingTransition(0,0);
            }
        });

        view.findViewById(R.id.btn_no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(getActivity(),"You are in app",Toast.LENGTH_SHORT).show();


                Intent i = new Intent(getActivity(), Navigation.class);
                startActivity(i);
              //  ((Activity) getActivity()).overridePendingTransition(0,0);
            }
        });
    }


}
