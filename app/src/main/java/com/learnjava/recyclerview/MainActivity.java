package com.learnjava.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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
    private final  int limit = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fetchData();
        setViewData();

    }



    private void setViewData(){
        ArrayList<Thread> threads = new ArrayList<>();

        CharacterViewAdapter adapter = new CharacterViewAdapter();
        ArrayList<Character> charactersList = new ArrayList<>();
        for(int i=offset;i<offset+limit;i++){
            Character character =allCharactersList.get(i);
            charactersList.add(character);

            if(character.getImageBitmap()==null){
                threads.add(character.loadImage());
            }

        }

        for(Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        adapter.setCharactersList(charactersList);


        charactersRecView = findViewById(R.id.charactersRecView);
        charactersRecView.setAdapter(adapter);
        charactersRecView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void fetchData() {

        Thread thread = new Thread() {
            public void run() {
                try {

                    InputStream in = new java.net.URL(getString(R.string.harry_potter_api_url)).openStream();
                    String json = streamToString(in); // input stream to string

                    JSONArray jsonArray = new JSONArray(json);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);


                        Character character = new Character(
                                object.getString("name"),
                                object.getString("image").replaceFirst("http","https"),
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
                                object.getBoolean("hogwartsStaff") ,
                                object.getBoolean("alive"));


                        allCharactersList.add(character);
                    }




                } catch (IOException | JSONException ex) {
                    ex.printStackTrace();
                }
            }
        };

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    private static String streamToString(InputStream inputStream) {
        String text = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
        return text;
    }
}