package newest.box.smart.com.smartchargeboxnewest.retrofit;

import newest.box.smart.com.smartchargeboxnewest.retrofit.models.addReservation.Reservation;
import newest.box.smart.com.smartchargeboxnewest.retrofit.models.addReservation.ReserveResponse;
import newest.box.smart.com.smartchargeboxnewest.retrofit.models.location.LocationResponse;
import newest.box.smart.com.smartchargeboxnewest.retrofit.models.locations.LocationsResponse;
import newest.box.smart.com.smartchargeboxnewest.retrofit.models.login.Result;
import newest.box.smart.com.smartchargeboxnewest.retrofit.models.login.TokenModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Deividas on 2017-08-08.
 */

public interface ApiService {

    @POST("other/authentification")
    Call<Result> authUser(@Body TokenModel tokenModel);

    @GET("other/locations/border")
    Call<LocationsResponse> getLocations(
            @Header("Authorization") String authorization,
            @Query("leftLongitude") double leftLongitude,
            @Query("leftLatitude") double leftLatitude,
            @Query("rightLongitude") double rightLongitude,
            @Query("rightLatitude") double rightLatitude
    );

    @GET("2.0/locations/{locationId}")
    Call<LocationResponse> getLocation(
            @Header("Authorization") String authorization,
            @Path("locationId") String locationId
    );

    @GET("2.0/locations/{locationId}/{stationId}")
    Call<LocationResponse> getStation(
            @Header("Authorization") String authorization,
            @Path("locationId") String locationId,
            @Path("stationId") String stationId
    );

    @POST("other/commands/RESERVE")
    Call<ReserveResponse> addReservation(@Header("Authorization") String authorization, @Body Reservation reservation);
}
