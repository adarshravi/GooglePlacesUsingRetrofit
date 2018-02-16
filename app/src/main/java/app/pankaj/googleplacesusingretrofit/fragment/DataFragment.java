package app.pankaj.googleplacesusingretrofit.fragment;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.ArrayList;
import java.util.List;

import app.pankaj.googleplacesusingretrofit.R;
import app.pankaj.googleplacesusingretrofit.adapter.SearchItemAdapter;
import app.pankaj.googleplacesusingretrofit.interfaces.ConnectionInterface;
import app.pankaj.googleplacesusingretrofit.model.ResponseData;
import app.pankaj.googleplacesusingretrofit.model.Result;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class DataFragment extends Fragment  implements LocationListener {

    EditText etSearch;
    ImageView ivSearch;
    RecyclerView recViewData;
    public double latitude=12.972442;
    public double longitude=77.580643;
    private int PROXIMITY_RADIUS = 10000;
    LocationManager locationManager;
    View view;
    List<Result> listResult;
    SearchItemAdapter searchItemAdapter;
    public DataFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return view=inflater.inflate(R.layout.fragment_data, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        etSearch= (EditText) view.findViewById(R.id.etSearch);
        ivSearch= (ImageView) view.findViewById(R.id.ivSearch);
        recViewData= (RecyclerView) view.findViewById(R.id.recViewData);
        listResult=new ArrayList<>();
        recViewData.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchItemAdapter=new SearchItemAdapter(getActivity(),listResult);
        recViewData.setAdapter(searchItemAdapter);

        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchText=etSearch.getText().toString().trim();
                if(searchText.equals(""))
                {
                    Toast.makeText(getActivity(), "Please enter some text to search", Toast.LENGTH_SHORT).show();
                    return;
                }
                search(searchText);
            }
        });

        try {
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,100,this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
        //show error dialog if Google Play Services not available
        if (!isGooglePlayServicesAvailable()) {
            Log.d("onCreate", "Google Play Services not available. Ending Test case.");
            getActivity().finish();
        }
        else {
            Log.d("onCreate", "Google Play Services available. Continuing.");
        }
    }

    private void search(String type) {
        String url = "https://maps.googleapis.com/maps/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ConnectionInterface service = retrofit.create(ConnectionInterface.class);
        Call<ResponseData> call = service.getNearbyPlaces(type, latitude + "," + longitude, PROXIMITY_RADIUS);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Response<ResponseData> response, Retrofit retrofit) {
            if (response.body()!=null) {
                listResult.clear();
                listResult.addAll(response.body().getResults());
                searchItemAdapter.notifyDataSetChanged();
            }else {
                Log.e("got-----","got location======="+latitude+"----"+longitude);
            }
            }
            @Override
            public void onFailure(Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });

    }
    private boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(getActivity());
        if(result != ConnectionResult.SUCCESS) {
            if(googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(getActivity(), result,
                        0).show();
            }
            return false;
        }
        return true;
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude=location.getLatitude();
        longitude=location.getLongitude();
        Log.e("got-----","got location======="+latitude+"----"+longitude);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
