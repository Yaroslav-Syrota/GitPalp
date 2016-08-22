package ua.com.thinkmobiles.gitpalp.model.response;

import android.content.Context;

import ua.com.thinkmobiles.gitpalp.R;
import ua.com.thinkmobiles.gitpalp.model.Model;

/**
 * Created by CAT_Caterpiller on 22.08.2016.
 */

public class ErrorResponse extends Throwable implements Model {

    public static final int INTERNET_MISSING        = 99;
    public static final int TIME_OUT_EXCEPTION      = 98;
    public static final int INTERCEPTOR_EXCEPTION   = 97;
    private Throwable throwable;
    private int code;
    private String error;

    public ErrorResponse() {
    }

    public ErrorResponse(String message, Throwable throwable) {
        error           = message;
        this.throwable  = throwable;
    }

    public ErrorResponse(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public int getCode() {
        return code;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setError(String error) {
        this.error = error;
    }

    // TODO not used
    public static String getErrorByCode(Context context, int code) {
        String message;
        switch (code){
            case INTERNET_MISSING:
                message = context.getString(R.string.error_no_internet);
                break;
            case TIME_OUT_EXCEPTION:
                message = context.getString(R.string.error_timeout);
                break;
            default:
                message = context.getString(R.string.error_unknown);
        }
        return message;
    }
}
