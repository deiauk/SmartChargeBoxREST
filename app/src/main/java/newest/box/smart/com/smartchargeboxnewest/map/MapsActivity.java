package newest.box.smart.com.smartchargeboxnewest.map;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.algo.Algorithm;
import com.google.maps.android.clustering.algo.GridBasedAlgorithm;
import com.google.maps.android.clustering.algo.PreCachingAlgorithmDecorator;

import java.util.List;

import newest.box.smart.com.smartchargeboxnewest.R;
import newest.box.smart.com.smartchargeboxnewest.detailLocation.DetailLocationActivity;
import newest.box.smart.com.smartchargeboxnewest.map.clusters.ClusterCustomRendering;
import newest.box.smart.com.smartchargeboxnewest.preferences.PreferencesManager;
import newest.box.smart.com.smartchargeboxnewest.retrofit.ApiService;
import newest.box.smart.com.smartchargeboxnewest.retrofit.models.RetrofitSingleton;
import newest.box.smart.com.smartchargeboxnewest.retrofit.models.locations.LocationData;
import newest.box.smart.com.smartchargeboxnewest.retrofit.models.locations.LocationsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, ClusterManager.OnClusterItemClickListener, GoogleMap.OnCameraIdleListener {

    private GoogleMap mMap;
    private ApiService apiService;
    private ClusterManager<LocationData> clusterManager;
    private ClusterCustomRendering customRenderer;
    private Algorithm algorithm;
    private LatLngBounds mBounds;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        token = PreferencesManager.getInstance(getApplicationContext()).getString(PreferencesManager.SESSION_TOKEN);
        Log.d("SDSDIGDSD", token + "");

        RetrofitSingleton retrofitSingleton = RetrofitSingleton.getInstance();
        apiService = retrofitSingleton.getService();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnCameraIdleListener(this);

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        setUpCluserManager();
    }

    private void setUpCluserManager() {
        clusterManager = new ClusterManager<>(getApplicationContext(), mMap);
        algorithm = new PreCachingAlgorithmDecorator<>(new GridBasedAlgorithm<LocationData>());

        clusterManager.setAlgorithm(algorithm);
        customRenderer = new ClusterCustomRendering(getApplicationContext(), mMap, clusterManager);
        clusterManager.setRenderer(customRenderer);

        clusterManager.setOnClusterItemClickListener(this);
        clusterManager.setOnClusterClickListener(new ClusterManager.OnClusterClickListener<LocationData>() {
            @Override
            public boolean onClusterClick(Cluster<LocationData> cluster) {
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        cluster.getPosition(), (float) Math.floor(mMap
                                .getCameraPosition().zoom + 1)), 1,
                        null);
                return true;
            }
        });

        mMap.setOnCameraChangeListener(clusterManager);
        mMap.setOnMarkerClickListener(clusterManager);
    }



    private void drawMarkers(List<LocationData> locationDataList) {
//        for (LocationData data : locationDataList) {
//            mMap.addMarker(new MarkerOptions().position(data.getCoordinates()).title(data.getName()));
//            //mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
//        }

        clusterManager.addItems(locationDataList);
        clusterManager.cluster();

        Log.d("SISUDD", clusterManager.getMarkerCollection().getMarkers().size() + "");
    }

    @Override
    public boolean onClusterItemClick(ClusterItem clusterItem) {
        LocationData data = (LocationData) clusterItem;
        Intent intent = new Intent(getApplicationContext(), DetailLocationActivity.class);
        Log.d("SDSGYUODSD", data.getId() + "");
        intent.putExtra(DetailLocationActivity.MARKER_ID, data.getId());
        startActivity(intent);
        return false;
    }


    @Override
    public void onCameraIdle() {
        LatLngBounds currentBounds = mMap.getProjection().getVisibleRegion().latLngBounds;
        if (mBounds == null) {
            downloadMarkers(currentBounds);
            mBounds = currentBounds;
        } else if (!(mBounds.contains(currentBounds.northeast) || mBounds.contains(currentBounds.southwest))) {
            downloadMarkers(currentBounds);
            mBounds = mBounds.including(currentBounds.northeast);
            mBounds = mBounds.including(currentBounds.southwest);
        }
    }

    private void downloadMarkers(LatLngBounds currentBounds) {
        Call<LocationsResponse> call = apiService.getLocations(
                token,
                currentBounds.southwest.longitude,
                currentBounds.southwest.latitude,
                currentBounds.northeast.longitude,
                currentBounds.northeast.latitude
        );
        call.enqueue(new Callback<LocationsResponse>() {
            @Override
            public void onResponse(Call<LocationsResponse> call, Response<LocationsResponse> response) {
                if (response.isSuccessful()) {
                    drawMarkers(response.body().getResult().getData());
                } else {
                    Log.d("SDUSVODSDSD", "!!!response.isSuccessful() ");
                }
            }
            @Override
            public void onFailure(Call<LocationsResponse> call, Throwable t) {
                Log.d("SDUSVODSDSD", "error " + t.getMessage());
            }
        });
    }
}
