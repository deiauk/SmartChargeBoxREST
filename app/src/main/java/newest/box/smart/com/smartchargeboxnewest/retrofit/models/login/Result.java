package newest.box.smart.com.smartchargeboxnewest.retrofit.models.login;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Deividas on 2017-09-01.
 */

public class Result {
    @SerializedName("result")
    private LoginResponse loginResponse;

    public LoginResponse getLoginResponse() {
        return loginResponse;
    }
}
