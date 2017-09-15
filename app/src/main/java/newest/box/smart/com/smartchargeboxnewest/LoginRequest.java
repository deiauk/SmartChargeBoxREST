package newest.box.smart.com.smartchargeboxnewest;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import newest.box.smart.com.smartchargeboxnewest.events.TokenEvent;
import newest.box.smart.com.smartchargeboxnewest.preferences.PreferencesManager;
import newest.box.smart.com.smartchargeboxnewest.retrofit.ApiService;
import newest.box.smart.com.smartchargeboxnewest.retrofit.models.login.Result;
import newest.box.smart.com.smartchargeboxnewest.retrofit.models.login.TokenData;
import newest.box.smart.com.smartchargeboxnewest.retrofit.models.login.TokenModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Deividas on 2017-09-02.
 */

public class LoginRequest {
    private ApiService service;

    public LoginRequest(ApiService service) {
        this.service = service;
    }

    public void login(TokenModel model) {
        Call<Result> call = service.authUser(model);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    TokenData tokenData = response.body().getLoginResponse().getData();
                    String token = tokenData.getToken();
                    EventBus.getDefault().post(new TokenEvent(token));
                } else {
                    EventBus.getDefault().post(new TokenEvent(null));
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                EventBus.getDefault().post(new TokenEvent(null));
            }
        });
    }
}
