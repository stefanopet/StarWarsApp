package com.stefano.starwars.mvp.character_list_mvp;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.stefano.starwars.R;
import com.stefano.starwars.net.models.CharacterModel;
import com.stefano.starwars.net.models.response.CharacterResponseModel;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

class CharacterListPresenter implements CharacterListMVP.Presenter{
    @Getter
    private final String TAG = "CharacterListPresenter";
    private CharacterListMVP.View characterListView;
    private final CharacterListMVP.Model model;
    @Getter @Setter private int nextPage;
    @Getter @Setter private boolean isLastPage;

    CharacterListPresenter(final CharacterListMVP.View characterListView){
        this.characterListView = characterListView;
        this.model = new CharacterListRXModel();
    }

    @Override
    public void getCharacters() {
        characterListView.showLoader(true);
        int FIRST_PAGE = 1;
        isLastPage = false;
        model.getCharacters(this, FIRST_PAGE);
    }

    @Override
    public void updateCharacters() {
        characterListView.showLoader(true);
        model.updateCharacters(this, nextPage);
    }

    @Override
    public void getCharactersFromName(final String name) {
        model.getCharactersFromName(this, name);
    }

    @Override
    public void onGetCharacters(@Nullable final CharacterResponseModel characterResponseModel, @Nullable final Throwable throwable) {
        characterListView.showLoader(false);
        characterListView.clearAdapter();
        if (characterResponseModel != null && characterResponseModel.getResults() != null) {
            isLastPage = characterResponseModel.getNext() == null;
            nextPage = 2;
            characterListView.setCharacterAdapter(characterResponseModel.getResults());
        } else {
            characterListView.setCharacterAdapter(null);
            characterListView.showError(R.string.generic_error);
            if(throwable != null){
                Log.d(TAG, throwable.getMessage());
            }
        }
    }

    @Override
    public void onUpdateCharacters(@Nullable final CharacterResponseModel characterResponseModel, @Nullable final Throwable throwable) {
        characterListView.showLoader(false);
        if (characterResponseModel != null && characterResponseModel.getResults() != null) {
            isLastPage = characterResponseModel.getNext() == null;
            nextPage ++;
            characterListView.setCharacterAdapter(characterResponseModel.getResults());
        } else {
            characterListView.showError(R.string.generic_error);
            if(throwable != null){
                Log.d(TAG, throwable.getMessage());
            }
        }
    }

    @Override
    public void dispose() {
        model.dispose();
        characterListView = null;
    }
}
