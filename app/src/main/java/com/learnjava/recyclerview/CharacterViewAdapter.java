package com.learnjava.recyclerview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class CharacterViewAdapter extends RecyclerView.Adapter<CharacterViewAdapter.ViewHolder> {
    private ArrayList<Character> charactersList = new ArrayList<>();
    Intent intent;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.characters_list_item, parent, false);
        return new ViewHolder(view);
    }

    public CharacterViewAdapter(Intent intent) {
        this.intent = intent;
    }
    public void startCharacterViewActivity(){

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Character character = charactersList.get(position);
        holder.characterName.setText(character.getName());

        try {
            Picasso.get().load(character.getImageUrl()).into(holder.itemImage);
        } catch (IllegalArgumentException e) {
            System.out.println("Image not found");

        }

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharacterViewActivity.setCharacter(character);
                startCharacterViewActivity();
            }
        });
    }

    public void setCharactersList(ArrayList<Character> charactersList) {
        this.charactersList = charactersList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return charactersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView characterName;
        ImageView itemImage;
        MaterialCardView cardItem;
        RelativeLayout container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
            characterName = itemView.findViewById(R.id.name);
            container = itemView.findViewById(R.id.characterItem);
        }
    }


}
