package newest.box.smart.com.smartchargeboxnewest.retrofit.models.login;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Deividas on 2017-08-08.
 */

public class TokenModel {
    private String url;
    private String token;

    @SerializedName("party_id")
    private String partyId;

    @SerializedName("country_code")
    private String countryCode;

    @SerializedName("business_details")
    private BusinessDetails details;

    public TokenModel(String url, String token, String partyId, String countryCode, BusinessDetails details) {
        this.url = url;
        this.token = token;
        this.partyId = partyId;
        this.countryCode = countryCode;
        this.details = details;
    }

    public String getUrl() {
        return url;
    }

    public String getToken() {
        return token;
    }

    public String getPartyId() {
        return partyId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public BusinessDetails getDetails() {
        return details;
    }
}
