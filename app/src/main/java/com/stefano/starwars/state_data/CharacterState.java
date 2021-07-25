package com.stefano.starwars.state_data;

import android.os.Bundle;
import android.view.View;

import com.stefano.starwars.net.models.CharacterModel;

import java.util.ArrayList;

import lombok.Getter;

@Getter
public class CharacterState extends View.BaseSavedState {
    public static final String STATE = "CharacterView.STATE";

    private ArrayList<CharacterModel> characterList;
    private int previousCharacterIndex;
    private int nextPage;
    private boolean isLastPage;

    public CharacterState(
            final Bundle superState,
            final ArrayList<CharacterModel> characterList,
            final int previousCharacterIndex,
            final int nextPage,
            final boolean isLastPage) {
        super(superState);
        this.characterList = characterList;
        this.previousCharacterIndex = previousCharacterIndex;
        this.nextPage = nextPage;
        this.isLastPage = isLastPage;
    }
}