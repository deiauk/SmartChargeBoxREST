package newest.box.smart.com.smartchargeboxnewest.retrofit.models.login;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Deividas on 2017-08-18.
 */


public class LoginResponse {
    private String status;

    @SerializedName("data")
    private TokenData data;

    public String getStatus() {
        return status;
    }

    public TokenData getData() {
        return data;
    }
}
