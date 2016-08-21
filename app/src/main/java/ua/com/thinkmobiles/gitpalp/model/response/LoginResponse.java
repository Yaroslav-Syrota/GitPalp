package ua.com.thinkmobiles.gitpalp.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by CAT_Caterpiller on 21.08.2016.
 */
public final class LoginResponse {


    @SerializedName("id")
    public String id;
    @SerializedName("url")
    public String url;
    @SerializedName("app")
    public AppBean app;
    @SerializedName("token")
    public String token;
    @SerializedName("hashed_token")
    public String hashedToken;
    @SerializedName("token_last_eight")
    public String tokenLastEight;
    @SerializedName("note")
    public String note;
    @SerializedName("note_url")
    public String noteUrl;
    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("updated_at")
    public String updatedAt;
    @SerializedName("fingerprint")
    public String fingerprint;
    @SerializedName("scopes")
    public List<String> scopes;

    public static class AppBean {
        @SerializedName("name")
        public String name;
        @SerializedName("url")
        public String url;
        @SerializedName("client_id")
        public String clientId;
    }
}
