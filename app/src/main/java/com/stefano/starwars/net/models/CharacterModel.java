package com.stefano.starwars.net.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

import lombok.Getter;

@Getter
public class CharacterModel{
    @SerializedName("name")
    public String name;

    @SerializedName("height")
    public String height;

    @SerializedName("mass")
    public String mass;

    @SerializedName("hair_color")
    public String hair_color;

    @SerializedName("skin_color")
    public String skin_color;

    @SerializedName("eye_color")
    public String eye_color;

    @SerializedName("birth_year")
    public String birth_year;

    @SerializedName("gender")
    public String gender;

    @SerializedName("homeworld")
    public String homeworld;

    @SerializedName("films")
    public List<String> films;

    @SerializedName("species")
    public List<String> species;

    @SerializedName("vehicles")
    public List<String> vehicles;

    @SerializedName("starships")
    public List<String> starships;

    @SerializedName("created")
    public Date created;

    @SerializedName("edited")
    public Date edited;

    @SerializedName("url")
    public String url;
}