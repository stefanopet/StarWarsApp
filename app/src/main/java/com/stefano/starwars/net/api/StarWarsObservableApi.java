package com.stefano.starwars.net.api;

import androidx.annotation.Nullable;

import com.stefano.starwars.net.models.response.CharacterResponseModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StarWarsObservableApi {
    /* TODO */
    String BASE_ENDPOINT = "people";

    /**
     *
     * @return characters for parameters
     */
    @GET(BASE_ENDPOINT)
    Observable<CharacterResponseModel> getCharacters(
            @Nullable @Query("page") Integer page);

    /**
     *
     * @return characters for name
     */
    @GET(BASE_ENDPOINT)
    Observable<CharacterResponseModel> getCharactersFromName(
            @Nullable @Query("search") String search);

    /**
     *
     * @return character from id
     */
    /*
    @GET(BASE_ENDPOINT + "/{id}")
    Observable<CharacterResponseModel> getCharacterFromId(
            @Path("id") int id);*/
}
