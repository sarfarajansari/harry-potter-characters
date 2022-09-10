package com.learnjava.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.*;

public class MainActivity extends AppCompatActivity {

    ArrayList<Character> allCharactersList = new ArrayList<>();
    private RecyclerView charactersRecView;
    private int offset = 0;
    private final int limit = 7;
    Button previous = null;
    Button next = null;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);

        new FetchData().execute();

    }

    private void setButtons(){
        previous= findViewById(R.id.previous);
        next= findViewById(R.id.next);

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(offset==0)return;

                if(offset-limit>0){
                    offset-=limit;
                }
                else {
                    offset=0;
                }

                charactersRecView = findViewById(R.id.charactersRecView);
                charactersRecView.setAdapter(setViewData());
                charactersRecView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(offset>=allCharactersList.size())return;

                if(offset+limit<=allCharactersList.size()){
                    offset+=limit;
                }
                else {
                    offset=allCharactersList.size();
                }

                charactersRecView = findViewById(R.id.charactersRecView);
                charactersRecView.setAdapter(setViewData());
                charactersRecView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


            }
        });

    }

    private CharacterViewAdapter setViewData(){
        CharacterViewAdapter adapter = new CharacterViewAdapter();
        ArrayList<Character> charactersList = new ArrayList<>();
        for (int i = offset; i < offset + limit; i++) {
            Character character = allCharactersList.get(i);
            charactersList.add(character);
        }

        adapter.setCharactersList(charactersList);

        return adapter;
    }


    private void initializeViewData() {

        setContentView(R.layout.activity_main);
        setButtons();
        charactersRecView = findViewById(R.id.charactersRecView);
        charactersRecView.setAdapter(setViewData());
        charactersRecView.setLayoutManager(new LinearLayoutManager(this));

    }




    private class FetchData extends AsyncTask<String, String, ArrayList<Character>> {
        public FetchData() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected ArrayList<Character> doInBackground(String... strings) {

            ArrayList<Character> characters = new ArrayList<>();
            try {

                InputStream in = new java.net.URL(getString(R.string.harry_potter_api_url)).openStream();
                String json = streamToString(in); // input stream to string

                JSONArray jsonArray = new JSONArray(json);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);


                    Character character = new Character(
                            object.getString("name"),
                            object.getString("image").replaceFirst("http", "https"),
                            object.getString("species"),
                            object.getString("gender"),
                            object.getString("house"),
                            object.getString("dateOfBirth"),
                            object.getBoolean("wizard"),
                            object.getString("ancestry"),
                            object.getString("eyeColour"),
                            object.getString("hairColour"),
                            object.getString("actor"),
                            object.getBoolean("hogwartsStudent"),
                            object.getBoolean("hogwartsStaff"),
                            object.getBoolean("alive"));


                    characters.add(character);
                }


            } catch (IOException | JSONException ex) {
                ex.printStackTrace();
            }

            return characters;


        }

        @Override
        protected void onPostExecute(ArrayList<Character> characters) {
            MainActivity.this.allCharactersList = characters;
            initializeViewData();
        }
    }


    private static String streamToString(InputStream inputStream) {
        String text = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
        return text;
    }
}