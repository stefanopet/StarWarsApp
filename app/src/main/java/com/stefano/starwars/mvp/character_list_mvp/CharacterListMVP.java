package com.stefano.starwars.mvp.character_list_mvp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import com.stefano.starwars.net.models.CharacterModel;
import com.stefano.starwars.net.models.response.CharacterResponseModel;

import java.util.ArrayList;

interface CharacterListMVP {
    interface  View {

        void setCharacterAdapter(@Nullable ArrayList<CharacterModel> characterList);

        void clearAdapter();

        void showLoader(boolean isShown);

        void showError(@StringRes int messageResId);
    }

    interface ViewPresenter {

        void getCharacters();

        void updateCharacters();

        void getCharactersFromName(String search);

        int getNextPage();

        void setNextPage(int nextPage);

        boolean isLastPage();

        void setLastPage(boolean isLastPage);

        void dispose();
    }

    interface ModelPresenter extends Model.CharacterListCallBack{}

    interface Presenter extends ViewPresenter, ModelPresenter{}

    interface Model {

        interface CharacterListCallBack {

            void onGetCharacters(@Nullable CharacterResponseModel characterResponseModel, @Nullable Throwable throwable);

            void onUpdateCharacters(@Nullable CharacterResponseModel characterResponseModel, @Nullable Throwable throwable);

        }

        void getCharacters(@NonNull final CharacterListCallBack callback, @Nullable Integer page);

        void updateCharacters(@NonNull final CharacterListCallBack callback, @Nullable Integer page);

        void getCharactersFromName(@NonNull final CharacterListCallBack callback, String name);

        void dispose();
    }
}
