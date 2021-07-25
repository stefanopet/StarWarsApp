package com.stefano.starwars.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.stefano.starwars.R;

public class LoadingView extends FrameLayout {

    public LoadingView(Context context) {
        super(context);
        init(context);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP) public LoadingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.component_loading_view, this, true);
        setBackgroundColor(getResources().getColor(R.color.dark_grey_transparent));
        setClickable(true);
        ProgressBar progressBar = findViewById(R.id.progress_bar);
        progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.yellow_beer), PorterDuff.Mode.MULTIPLY);
    }

    public void setTransparentBackground() {
        setBackgroundColor(getResources().getColor(android.R.color.transparent));
    }
}