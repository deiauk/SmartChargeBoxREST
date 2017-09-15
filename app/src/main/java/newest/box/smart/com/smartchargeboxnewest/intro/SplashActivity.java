package newest.box.smart.com.smartchargeboxnewest.intro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import newest.box.smart.com.smartchargeboxnewest.LoginRequest;
import newest.box.smart.com.smartchargeboxnewest.R;
import newest.box.smart.com.smartchargeboxnewest.events.TokenEvent;
import newest.box.smart.com.smartchargeboxnewest.map.MapsActivity;
import newest.box.smart.com.smartchargeboxnewest.preferences.PreferencesManager;
import newest.box.smart.com.smartchargeboxnewest.retrofit.ApiService;
import newest.box.smart.com.smartchargeboxnewest.retrofit.models.RetrofitSingleton;
import newest.box.smart.com.smartchargeboxnewest.retrofit.models.login.BusinessDetails;
import newest.box.smart.com.smartchargeboxnewest.retrofit.models.login.LoginResponse;
import newest.box.smart.com.smartchargeboxnewest.retrofit.models.login.Logo;
import newest.box.smart.com.smartchargeboxnewest.retrofit.models.login.Result;
import newest.box.smart.com.smartchargeboxnewest.retrofit.models.login.TokenData;
import newest.box.smart.com.smartchargeboxnewest.retrofit.models.login.TokenModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        token = PreferencesManager.getInstance(getApplicationContext()).getString(PreferencesManager.TOKEN);

        if(token != null) {
            String name = PreferencesManager.getInstance(getApplicationContext()).getString(PreferencesManager.USER_NAME);
            int loginType = PreferencesManager.getInstance(getApplicationContext()).getInt(PreferencesManager.LOGIN_TYPE);

            String partyId = loginType == 1 ? "FACEBOOK" : "GOOGLE";

            RetrofitSingleton retrofitSingleton = RetrofitSingleton.getInstance();
            ApiService service = retrofitSingleton.getService();

            BusinessDetails businessDetails = new BusinessDetails(name, null, "fb");
            TokenModel tokenModel = new TokenModel("fb", token, partyId, "LT", businessDetails);

            LoginRequest loginRequest = new LoginRequest(service);
            loginRequest.login(tokenModel);

        } else {
            Intent intent = new Intent(getApplicationContext(), IntroActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(token != null && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe
    public void onTokenArrived(TokenEvent obj) {
        if(obj.getToken() == null) {
            Toast.makeText(getApplicationContext(), "Klaida", Toast.LENGTH_SHORT).show();
            return;
        }

        PreferencesManager.getInstance(getApplicationContext()).writeString(PreferencesManager.SESSION_TOKEN, obj.getToken());
        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
        startActivity(intent);
        finish();
    }
}
