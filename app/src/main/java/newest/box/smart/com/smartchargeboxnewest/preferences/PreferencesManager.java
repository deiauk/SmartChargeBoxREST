package newest.box.smart.com.smartchargeboxnewest.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Deividas on 2017-09-02.
 */

public class PreferencesManager {
    private static PreferencesManager instance;
    private static SharedPreferences preferences;

    private static final String NAME = "USER_PREF";
    public static final String TOKEN = "TOKEN";
    public static final String SESSION_TOKEN = "SESSION_TOKEN";
    public static final String USER_NAME = "USER_NAME";
    public static final String LOGIN_TYPE = "LOGIN_TYPE";

    private PreferencesManager(Context context) {
        preferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public static PreferencesManager getInstance(Context context) {
        if(instance == null) {
            instance = new PreferencesManager(context);
        }
        return instance;
    }

    public void writeString(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key) {
        Log.d("SDSUGDYSDSD", key);
        return preferences.getString(key, null);
    }

    public void writeInt(String key, int value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public int getInt(String key) {
        return preferences.getInt(key, -1);
    }
}
