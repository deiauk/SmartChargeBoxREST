package newest.box.smart.com.smartchargeboxnewest.retrofit.models.locations;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by Deividas on 2017-09-10.
 */

@Parcel
public class Connectors {
    private String id;

    @SerializedName("tarrif_id")
    private String tariffId;

    @SerializedName("terms_and_conditions")
    private String termsAndConditions;

    public String getId() {
        return id;
    }

    public String getTariffId() {
        return tariffId;
    }

    public String getTermsAndConditions() {
        return termsAndConditions;
    }
}
