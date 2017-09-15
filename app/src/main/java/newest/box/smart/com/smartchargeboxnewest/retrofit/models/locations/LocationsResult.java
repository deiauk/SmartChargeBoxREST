package newest.box.smart.com.smartchargeboxnewest.retrofit.models.locations;

import java.util.List;

/**
 * Created by Deividas on 2017-09-02.
 */

public class LocationsResult {
    private String status;
    private List<LocationData> data;

    public String getStatus() {
        return status;
    }

    public List<LocationData> getData() {
        return data;
    }
}
