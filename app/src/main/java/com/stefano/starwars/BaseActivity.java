package com.stefano.starwars;

import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.stefano.starwars.utils.Tools;

public abstract class BaseActivity extends AppCompatActivity {

    public void hideKeyboard(){
        View view = this.getCurrentFocus();
        Tools.hideKeyboard(view);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindPresenter();
    }

    protected abstract void unbindPresenter();

    public boolean isLoading(){
        View loadingView = findViewById(R.id.loading_view);
        return loadingView != null && loadingView.getVisibility() == View.VISIBLE;
    }

    public void showLoader(){
        if(!isLoading())
            setLoadingVisibility(true);
    }

    public void hideLoader(){
        setLoadingVisibility(false);
    }

    private void setLoadingVisibility(boolean show){
        View loadingView = findViewById(R.id.loading_view);
        if(loadingView != null)
            loadingView.setVisibility(show ? View.VISIBLE : View.GONE);
        else
            Log.i(getString(R.string.warning),getString(R.string.error_loading_visibility));
    }
}