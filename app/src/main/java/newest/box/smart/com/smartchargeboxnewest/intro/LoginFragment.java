package newest.box.smart.com.smartchargeboxnewest.intro;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import newest.box.smart.com.smartchargeboxnewest.LoginRequest;
import newest.box.smart.com.smartchargeboxnewest.R;
import newest.box.smart.com.smartchargeboxnewest.events.TokenEvent;
import newest.box.smart.com.smartchargeboxnewest.map.MapsActivity;
import newest.box.smart.com.smartchargeboxnewest.preferences.PreferencesManager;
import newest.box.smart.com.smartchargeboxnewest.retrofit.models.RetrofitSingleton;
import newest.box.smart.com.smartchargeboxnewest.retrofit.models.login.BusinessDetails;
import newest.box.smart.com.smartchargeboxnewest.retrofit.models.login.Logo;
import newest.box.smart.com.smartchargeboxnewest.retrofit.models.login.TokenModel;
import newest.box.smart.com.smartchargeboxnewest.retrofit.ApiService;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    private CallbackManager callbackManager;
    private Unbinder unbinder;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getActivity());
        callbackManager = CallbackManager.Factory.create();
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject user, GraphResponse graphResponse) {
                        try {
                            String userId = user.getString("id");
                            String name = user.getString("name");
                            String email = user.getString("email");
                            String photoPath = "https://graph.facebook.com/" + userId + "/picture?type=large";
                            saveProfile(1, photoPath, name, email, userId, loginResult.getAccessToken().getToken());
                           // serverValidation(1, loginResult.getAccessToken().getToken());
                        } catch (JSONException e) {
                            Log.d("addfdsfdsf", e.getMessage());
                            e.printStackTrace();
                        }
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, name, email");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                AccessToken.setCurrentAccessToken(null);
            }

            @Override
            public void onError(FacebookException exception) {
                AccessToken.setCurrentAccessToken(null);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        if(!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @OnClick(R.id.facebook_login_button)
    public void loginFb() {
        if (AccessToken.getCurrentAccessToken() != null) {
            LoginManager.getInstance().logOut();
        } else {
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email"));
        }
    }

    private void saveProfile(int loginType, final String url, String name, String email,
                             String userId, String token) {
        Log.d("SDSYVDSDIUSD", loginType + " " + url + " " + name + " " + email + " " + userId);

        PreferencesManager.getInstance(getActivity().getApplicationContext()).writeString(PreferencesManager.TOKEN, token);
        PreferencesManager.getInstance(getActivity().getApplicationContext()).writeString(PreferencesManager.USER_NAME, name);
        PreferencesManager.getInstance(getActivity().getApplicationContext()).writeInt(PreferencesManager.LOGIN_TYPE, loginType);

        String partyId = loginType == 1 ? "FACEBOOK" : "GOOGLE";

        Logo logo = new Logo("fb.lt", url, "OTHER", "jpeg", 512, 512);
        BusinessDetails businessDetails = new BusinessDetails(name, logo, "fb");

        RetrofitSingleton retrofitSingleton = RetrofitSingleton.getInstance();
        ApiService service = retrofitSingleton.getService();

        TokenModel tokenModel = new TokenModel("fb", token, partyId, "LT", businessDetails);

        LoginRequest loginRequest = new LoginRequest(service);
        loginRequest.login(tokenModel);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Subscribe
    public void onTokenArrived(TokenEvent obj) {
        if(obj.getToken() == null) {
            Toast.makeText(getActivity().getApplicationContext(), "Klaida", Toast.LENGTH_SHORT).show();
            return;
        }

        PreferencesManager.getInstance(getActivity().getApplicationContext()).writeString(PreferencesManager.SESSION_TOKEN, obj.getToken());
        Intent intent = new Intent(getActivity().getApplicationContext(), MapsActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
