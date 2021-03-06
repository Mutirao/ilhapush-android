package br.com.ilhasoft.push.persistence;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by john-mac on 6/27/16.
 */
public class Preferences {

    public static final String REGISTRATION_COMPLETE = "br.com.ilhasoft.push.RegistrationCompleted";

    public static final String KEY_GCM_SENDER_ID = "gcmSenderId";
    public static final String KEY_IDENTITY = "identity";
    public static final String KEY_CONTACT_UUID = "contactUuid";

    private static final String PREFERENCES_NAME = "br.com.ilhasoft.push.preferences";

    private final SharedPreferences sharedPreferences;
    private final String prefix;

    private final Map<String, String> objects;

    public Preferences(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        this.prefix = context.getPackageName();
        this.objects = new HashMap<>();
    }

    public Preferences setGcmSenderId(String gcmSenderId) {
        this.objects.put(getKey(KEY_GCM_SENDER_ID), gcmSenderId);
        return this;
    }

    public String getGcmSenderId() {
        return sharedPreferences.getString(getKey(KEY_GCM_SENDER_ID), null);
    }

    public String getContactUuid() {
        return sharedPreferences.getString(getKey(KEY_CONTACT_UUID), null);
    }

    public Preferences setContactUuid(String contactUuid) {
        this.objects.put(getKey(KEY_CONTACT_UUID), contactUuid);
        return this;
    }

    public Preferences setIdentity(String identity) {
        this.objects.put(getKey(KEY_IDENTITY), identity);
        return this;
    }

    public String getIdentity() {
        return sharedPreferences.getString(getKey(KEY_IDENTITY), null);
    }

    public void apply() {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        for (String key : objects.keySet()) {
            editor.putString(key, objects.get(key));
        }
        editor.apply();
    }

    @NonNull
    private String getKey(String key) {
        return prefix  + "_" + key;
    }
}
