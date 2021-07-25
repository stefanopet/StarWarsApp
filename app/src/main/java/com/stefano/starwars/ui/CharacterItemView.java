package com.stefano.starwars.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.stefano.starwars.R;
import com.stefano.starwars.net.models.CharacterModel;

public class CharacterItemView extends RelativeLayout {

    private ImageView characterIv;
    private TextView nameTv;

    public CharacterItemView(Context context) {
        super(context);
        init(context);
    }

    public CharacterItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CharacterItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CharacterItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.component_character_item_view, this, true);
        initUiRefs();
    }

    private void initUiRefs() {
        characterIv = findViewById(R.id.character_iv);
        nameTv = findViewById(R.id.name_tv);
    }

    public void setCharacter(CharacterModel characterModel) {
        createImageView(characterModel.getUrl());
        nameTv.setText(characterModel.getName());
    }

    private void createImageView(String peopleUrl) {
        String peoplesUrl = "https://swapi.dev/api/people/";
        String peopleIndex = peopleUrl.replace(peoplesUrl, "").replace("/", "");
        String imageUrl = "https://mobile.aws.skylabs.it/mobileassignments/swapi/$.png";
        String logoUrl = imageUrl.replace("$", peopleIndex);
        Picasso.get().load(logoUrl)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(characterIv);
    }
}