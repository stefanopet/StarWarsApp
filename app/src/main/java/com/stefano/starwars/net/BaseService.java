package com.stefano.starwars.net;

import androidx.annotation.NonNull;

import com.stefano.starwars.BuildConfig;
import com.stefano.starwars.net.api.StarWarsObservableApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseService {

    private static final String baseUrl = "https://swapi.dev/api/";

    private BaseService() {
    }

    /**
     * @return builded StarWarsObservableApi client
     */
    public static @NonNull
    StarWarsObservableApi getCharacterRXClient() {
        return createRetrofit().create(StarWarsObservableApi.class);
    }

    private static @NonNull
    Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static @NonNull
    OkHttpClient getClient() {
        return new OkHttpClient.Builder().addInterceptor(getInterceptor()).build();
    }

    private static @NonNull
    HttpLoggingInterceptor getInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            interceptor.level(HttpLoggingInterceptor.Level.BODY);
        }
        return interceptor;
    }
}
