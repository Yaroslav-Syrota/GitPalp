package ua.com.thinkmobiles.gitpalp.model.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ua.com.thinkmobiles.gitpalp.model.Model;

/**
 * Created by CAT_Caterpiller on 21.08.2016.
 */
public final class LoginRequest implements Model {

    @SerializedName("app_key")
    public String appKey;
    @SerializedName("app_secret")
    public String appSecret;
    @SerializedName("note")
    public String note;
    @SerializedName("fingerprint")
    public String fingerprint;
    @SerializedName("scopes")
    public List<String> scopes;
}
