package com.frost.themoviedb.network.response;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {

    @SerializedName("status_code")
    private int statusCode;
    @SerializedName("status_message")
    private String statusMessage;
    @SerializedName("success")
    private boolean success;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
