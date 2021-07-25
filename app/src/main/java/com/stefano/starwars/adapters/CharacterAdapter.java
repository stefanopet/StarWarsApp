package com.stefano.starwars.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.stefano.starwars.R;
import com.stefano.starwars.net.models.CharacterModel;
import com.stefano.starwars.ui.CharacterItemView;

import java.util.ArrayList;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {

    private final ArrayList<CharacterModel> characterModelList;
    private int layout;

    public CharacterAdapter() {
        characterModelList = new ArrayList<>();
    }

    public void clearAdapter(){
        this.characterModelList.clear();
    }

    public void updateAdapter(@Nullable ArrayList<CharacterModel> characterModelList, @LayoutRes int layout) {
        this.layout = layout;
        if (characterModelList != null) {
            this.characterModelList.addAll(characterModelList);
        }
        notifyDataSetChanged();
    }

    public @NonNull
    ArrayList<CharacterModel> getList(){
        return characterModelList;
    }

    @Override
    public @NonNull CharacterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterAdapter.ViewHolder holder, int position) {
        holder.characterItemView.setCharacter(characterModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return characterModelList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        CharacterItemView characterItemView;

        ViewHolder(View itemView) {
            super(itemView);
            characterItemView = itemView.findViewById(R.id.character_item_view);
        }
    }
}
