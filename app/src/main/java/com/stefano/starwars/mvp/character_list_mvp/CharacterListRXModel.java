package com.stefano.starwars.mvp.character_list_mvp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.stefano.starwars.net.BaseService;
import com.stefano.starwars.net.api.StarWarsObservableApi;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

class CharacterListRXModel implements CharacterListMVP.Model{
    private StarWarsObservableApi starWarsApi;
    private final CompositeDisposable disposables;

    CharacterListRXModel(){
        starWarsApi = BaseService.getCharacterRXClient();
        disposables = new CompositeDisposable();
    }

    @Override
    public void getCharacters(
            @NonNull CharacterListCallBack callback,
            @Nullable Integer page) {
        disposables.add(
                starWarsApi.getCharacters(
                        page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                responseModel -> callback.onGetCharacters(responseModel, null),
                                throwable -> callback.onGetCharacters(null, throwable)
                        )
        );
    }

    @Override
    public void updateCharacters(
            @NonNull CharacterListCallBack callback,
            @Nullable Integer page) {
        disposables.add(
                starWarsApi.getCharacters(
                        page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                responseModel -> callback.onUpdateCharacters(responseModel, null),
                                throwable -> callback.onUpdateCharacters(null, throwable)
                        )
        );
    }

    @Override
    public void getCharactersFromName(@NonNull CharacterListCallBack callback, String name) {
        disposables.add(
                starWarsApi.getCharactersFromName(name)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                responseModel -> callback.onGetCharacters(responseModel, null),
                                throwable -> callback.onGetCharacters(null, throwable)
                        )
        );
    }

    @Override
    public void dispose() {
        disposables.dispose();
        starWarsApi = null;
    }
}
