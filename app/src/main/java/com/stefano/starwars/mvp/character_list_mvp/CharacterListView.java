package com.stefano.starwars.mvp.character_list_mvp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stefano.starwars.BaseActivity;
import com.stefano.starwars.R;
import com.stefano.starwars.adapters.CharacterAdapter;
import com.stefano.starwars.net.models.CharacterModel;
import com.stefano.starwars.state_data.CharacterState;
import com.stefano.starwars.ui.AlertDialogManager;

import java.util.ArrayList;

public class CharacterListView extends BaseActivity implements CharacterListMVP.View, View.OnClickListener {
    private CharacterListMVP.Presenter presenter;
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private CharacterAdapter adapter;
    private EditText searchForIdEt;
    private RadioGroup radioGroup;
    private Button searchButton;


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(CharacterState.STATE, getCharacterState());
        super.onSaveInstanceState(outState);
    }

    private @NonNull
    CharacterState getCharacterState(){
        return new CharacterState(
                new Bundle(),
                adapter.getList(),
                layoutManager.findFirstCompletelyVisibleItemPosition(),
                presenter.getNextPage(),
                presenter.isLastPage());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(CharacterState.STATE)) {
            restoreCharacterState(savedInstanceState);
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void restoreCharacterState(@NonNull Bundle savedInstanceState){
        CharacterState characterState = savedInstanceState.getParcelable(CharacterState.STATE);
        if(characterState != null){
            adapter.clearAdapter();
            adapter.updateAdapter(characterState.getCharacterList(), R.layout.component_character_list_view);
            int previousIndex = characterState.getPreviousCharacterIndex();
            if(previousIndex > RecyclerView.NO_POSITION){
                recyclerView.smoothScrollToPosition(previousIndex);
            }
            presenter.setNextPage(characterState.getNextPage());
            presenter.setLastPage(characterState.isLastPage());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUiRefs();
        configUiRefs();
    }

    private void initUiRefs() {
        presenter = new CharacterListPresenter(this);
        recyclerView = findViewById(R.id.recyclerview);
        searchForIdEt = findViewById(R.id.for_id_et);
        radioGroup = findViewById(R.id.radio_group);
        searchButton = findViewById(R.id.search_btn);
        adapter = new CharacterAdapter();
        layoutManager = new LinearLayoutManager(this);
    }

    private void configUiRefs() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(createOnScrollListener());
        searchButton.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(createOnCheckedChangeListener());
        radioGroup.check(R.id.search_all_rb);
        selectBrewedSearchMode();
    }

    private void selectIdSearchMode(){
        selectSearchMode(true);
    }

    private void selectBrewedSearchMode(){
        searchForIdEt.setText("");
        selectSearchMode(false);
    }

    private void selectSearchMode(final boolean searchModel){
        searchForIdEt.setEnabled(searchModel);
        searchForIdEt.setFocusableInTouchMode(searchModel);
    }

    private @NonNull RecyclerView.OnScrollListener createOnScrollListener(){
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                onScrollPaginationLogic(dy);
            }
        };
    }

    private @NonNull RadioGroup.OnCheckedChangeListener createOnCheckedChangeListener(){
        return (group, checkedId) -> {
            switch(checkedId)
            {
                case R.id.id_rb:
                    selectIdSearchMode();
                    break;

                case R.id.search_all_rb:
                    selectBrewedSearchMode();
                    break;
            }
        };
    }

    private void onScrollPaginationLogic(final int dy){
        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
        boolean x = presenter.isLastPage();
        if (!isLoading() && !presenter.isLastPage() && dy > 0 &&
                (visibleItemCount + firstVisibleItemPosition) >= totalItemCount &&
                firstVisibleItemPosition >= 0 ) {
            presenter.updateCharacters();
        }
    }

    @Override
    public void clearAdapter(){
        adapter.clearAdapter();
    }

    @Override
    public void setCharacterAdapter(final ArrayList<CharacterModel> characterModelList) {
        adapter.updateAdapter(characterModelList, R.layout.component_character_list_view);
    }

    @Override
    public void showLoader(final boolean isShown) {
        if (isShown) {
            super.showLoader();
        } else {
            super.hideLoader();
        }
    }

    @Override
    public void showError(final int messageResId) {
        AlertDialogManager.showAlertDialog(
                this,
                getString(R.string.warning),
                getString(messageResId),
                (dialog, which) -> {});
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.search_btn) {
            hideKeyboard();
            switch (radioGroup.getCheckedRadioButtonId()){
                case R.id.id_rb:
                    presenter.getCharactersFromName(searchForIdEt.getText().toString());
                    break;

                case R.id.search_all_rb:
                    presenter.getCharacters();
                    break;
            }
        }
    }

    @Override
    protected void unbindPresenter() {
        presenter.dispose();
    }
}
