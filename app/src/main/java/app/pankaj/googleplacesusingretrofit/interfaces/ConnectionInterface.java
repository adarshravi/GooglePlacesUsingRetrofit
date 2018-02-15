package app.pankaj.googleplacesusingretrofit.interfaces;

import app.pankaj.googleplacesusingretrofit.model.ResponseData;
import retrofit.Call;
import retrofit.Response;
import retrofit.http.GET;
import retrofit.http.Query;



public interface ConnectionInterface {

    @GET("api/place/nearbysearch/json?sensor=true&key=AIzaSyDHxSsf1YQlDhyxr9Ho-jKokac2xN_pBxc")
    Call<ResponseData> getNearbyPlaces(@Query("type") String type, @Query("location") String location, @Query("radius") int radius);
}
