package newest.box.smart.com.smartchargeboxnewest.retrofit.models.locations;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Deividas on 2017-09-02.
 */

public class Evse {
    private String uid;

    @SerializedName("evse_id")
    private String evseUid;
    private String status;

    private List<Connectors> connectors;

    public String getUid() {
        return uid;
    }

    public String getEvseUid() {
        return evseUid;
    }

    public String getStatus() {
        return status;
    }


    public List<Connectors> getConnectors() {
        return connectors;
    }
}
