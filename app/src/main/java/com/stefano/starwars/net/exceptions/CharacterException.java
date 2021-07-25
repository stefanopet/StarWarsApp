package com.stefano.starwars.net.exceptions;

import com.stefano.starwars.net.models.response.ErrorCharacterResponseModel;

public class CharacterException extends Exception{
    private final String info;
    private final int code;
    private final String message;

    public CharacterException(String info){
        this.info = info;
        this.code = 0;
        this.message = "GENERIC ERROR";
    }

    public CharacterException(String info, ErrorCharacterResponseModel errorCharacterResponseModel) {
        this.info = info;
        this.code = errorCharacterResponseModel.getError().getCode();
        this.message = errorCharacterResponseModel.getError().getMessage();
    }

    public String getInfo(){
        return info;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
