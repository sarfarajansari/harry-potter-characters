package com.learnjava.recyclerview;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;



import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;

public class CharacterViewActivity extends AppCompatActivity {
    private static Character character;
    private ImageView characterImage;
    private TextView name;
    private TextView house;
    private TextView species;
    private TextView ancestry;
    private TextView gender;
    private TextView eyeColor;
    private TextView hairColor;
    private TextView actorName;
    private TextView dateOfBirth;
    private TextView isWizard;
    private TextView isHogwartsStudent;
    private TextView isHogwartsStaff;
    private TextView isAlive;


    public static void setCharacter(Character c) {
        character = c;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle(character.getName());
        setContentView(R.layout.activity_character_view);
        characterImage = findViewById(R.id.characterImage);


        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.arrow_white);

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        try {
            Picasso.get().load(character.getImageUrl()).into(characterImage);
        } catch (IllegalArgumentException e) {
            System.out.println("Image not found");

        }




        name = findViewById(R.id.characterName);
        house = findViewById(R.id.house);
        species = findViewById(R.id.species);
        ancestry = findViewById(R.id.ancestry);
        gender = findViewById(R.id.gender);
        eyeColor = findViewById(R.id.eyeColour);
        hairColor = findViewById(R.id.hairColour);
        actorName = findViewById(R.id.actor);
        dateOfBirth = findViewById(R.id.dateOfBirth);
        isWizard = findViewById(R.id.isWizard);
        isHogwartsStudent = findViewById(R.id.isHogwartsStudent);
        isHogwartsStaff = findViewById(R.id.isHogwartsStaff);
        isAlive = findViewById(R.id.isAlive);

        name.setText(character.getName());
        house.setText(character.getHouse());
        species.setText(character.getSpecies());
        ancestry.setText(character.getAncestry());
        gender.setText(character.getGender());
        eyeColor.setText(character.getEyeColour());
        hairColor.setText(character.getHairColour());
        actorName.setText(character.getActor());
        dateOfBirth.setText(character.getDateOfBirth());

        isWizard.setText(booleanToString(character.getWizard()));
        isHogwartsStudent.setText(booleanToString(character.isHogwartsStudent()));
        isHogwartsStaff.setText(booleanToString(character.isHogwartsStaff()));
        isAlive.setText(booleanToString(character.isAlive()));

    }


    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private String booleanToString(Boolean a) {
        return a ? "Yes" : "No";
    }
}