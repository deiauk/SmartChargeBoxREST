package newest.box.smart.com.smartchargeboxnewest.map.clusters;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

import newest.box.smart.com.smartchargeboxnewest.retrofit.models.locations.LocationData;

/**
 * Created by Deividas on 2017-09-02.
 */

public class ClusterCustomRendering extends DefaultClusterRenderer<LocationData> {

    public ClusterCustomRendering(Context context, GoogleMap map, ClusterManager<LocationData> clusterManager) {
        super(context, map, clusterManager);
    }

    @Override
    protected void onBeforeClusterItemRendered(LocationData item, MarkerOptions markerOptions) {
        super.onBeforeClusterItemRendered(item, markerOptions);

//        if (item.isActive()) {
//            BitmapDescriptor iconBitmap = BitmapDescriptorFactory.fromResource(R.drawable.availableaaa);
//            markerOptions.icon(iconBitmap);
//        } else {
//            BitmapDescriptor iconBitmap = BitmapDescriptorFactory.fromResource(R.drawable.notavailableaaa);
//            markerOptions.icon(iconBitmap);
//        }

    }
}
