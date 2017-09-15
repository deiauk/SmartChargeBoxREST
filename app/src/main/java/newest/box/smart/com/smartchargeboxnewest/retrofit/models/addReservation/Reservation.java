package newest.box.smart.com.smartchargeboxnewest.retrofit.models.addReservation;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Deividas on 2017-09-10.
 */

public class Reservation {
    @SerializedName("response_url")
    private String responseUrl;
    private String token;

    @SerializedName("start_date")
    private String startDate;
    @SerializedName("expire_date")
    private String expireDate;

    @SerializedName("location_id")
    private String locationId;

    @SerializedName("connector_id")
    private String connectorId;

    @SerializedName("evse_uid")
    private String evseUid;

    public Reservation(String responseUrl, String token, String startDate, String expireDate, String locationId, String connectorId, String evseUid) {
        this.responseUrl = responseUrl;
        this.token = token;
        this.startDate = startDate;
        this.expireDate = expireDate;
        this.locationId = locationId;
        this.connectorId = connectorId;
        this.evseUid = evseUid;

        Log.d("SDSDYTISDSD", startDate + " " + expireDate);
    }

    public String getResponseUrl() {
        return responseUrl;
    }

    public String getToken() {
        return token;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public String getLocationId() {
        return locationId;
    }

    public String getConnectorId() {
        return connectorId;
    }

    public String getEvseUid() {
        return evseUid;
    }
}
