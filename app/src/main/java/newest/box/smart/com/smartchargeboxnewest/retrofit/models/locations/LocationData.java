package newest.box.smart.com.smartchargeboxnewest.retrofit.models.locations;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;
import com.google.maps.android.clustering.ClusterItem;

import java.util.List;

/**
 * Created by Deividas on 2017-09-02.
 */

public class LocationData implements ClusterItem {
    @SerializedName("_id")
    private String id;
    private String type;
    @SerializedName("reverse_geocoded_address")
    private String name;
    private String address;
    private String city;

    private double longitude, latitude;
    private String description;

    @SerializedName("postal_code")
    private String postalCode;
    private String country;
    //private LatLng coordinates;

    @SerializedName("related_locations")
    private String relatedLocations;
    private List<Evse> evses;
    private String directions;

    @SerializedName("last_updated")
    private String lastUpdated;

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }


    //public LatLng getCoordinates() {
       // return coordinates;
   // }

    public String getRelatedLocations() {
        return relatedLocations;
    }

    public List<Evse> getEvses() {
        return evses;
    }

    public String getDirections() {
        return directions;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    @Override
    public LatLng getPosition() {
        return new LatLng(latitude, longitude);
    }
}
