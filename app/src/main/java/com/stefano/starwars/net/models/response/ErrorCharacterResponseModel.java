package com.stefano.starwars.net.models.response;

import com.google.gson.annotations.SerializedName;

public class ErrorCharacterResponseModel {

    @SerializedName("error")
    private Error error;

    public Error getError(){
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
    public static class Error{

        @SerializedName("code")
        Integer code;
        @SerializedName("message")
        String message;

        public Integer getCode(){
            return code;
        }

        public String getMessage(){
            return message;
        }
    }
}