package com.stefano.starwars.net.models.response;

import com.google.gson.annotations.SerializedName;
import com.stefano.starwars.net.models.CharacterModel;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder @Getter
public class CharacterResponseModel {
    @SerializedName("count")
    public int count;
    @SerializedName("next")
    public String next;
    @SerializedName("previous")
    public String previous;
    @SerializedName("results")
    public ArrayList<CharacterModel> results;
}
