package com.learnjava.recyclerview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class CharacterViewAdapter extends RecyclerView.Adapter<CharacterViewAdapter.ViewHolder> {
    private ArrayList<Character> charactersList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.characters_list_item,parent,false);
        return new ViewHolder(view);
    }

    public CharacterViewAdapter() {

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Character character = charactersList.get(position);
        holder.characterName.setText(character.getName());
        holder.itemImage.setImageBitmap(character.getImageBitmap());

        if(character.getImageBitmap()==null){
            new DownloadImageTask(character).execute();
        }


    }

    public void setCharactersList(ArrayList<Character> charactersList) {
        this.charactersList = charactersList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return charactersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView characterName;
        ImageView itemImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             itemImage = itemView.findViewById(R.id.itemImage);
            characterName = itemView.findViewById(R.id.name);
        }
    }

    private class DownloadImageTask extends AsyncTask<String, String, Bitmap> {
        Character character;

        public DownloadImageTask(Character character) {
            this.character = character;

        }



        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap imageBitmap = null;


            try{
                InputStream in = new java.net.URL(character.getImageUrl()).openStream();
                imageBitmap = BitmapFactory.decodeStream(in);
            }
            catch ( IOException e){
                e.printStackTrace();
            }
            return imageBitmap;
        }

        @Override
        protected void onPostExecute(Bitmap imageBitmap) {
            character.setImageBitmap(imageBitmap);
            notifyDataSetChanged();
            System.out.println("Fetched Image");
        }
    }


}
