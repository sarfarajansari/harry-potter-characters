package com.learnjava.recyclerview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

public class Character {
    private String name;
    private String email;
    private String imageUrl;
    private String species;
    private String gender;
    private String house;
    private String dateOfBirth;
    private Boolean isWizard;
    private String ancestry;
    private String eyeColour;
    private String hairColour;
    private String actor;
    private boolean isHogwartsStudent;
    private boolean isHogwartsStaff;
    private boolean isAlive;



    private Bitmap imageBitmap;


    public String getEmail() {
        return email;
    }

    public Character(String name, String imageUrl, String species, String gender, String house, String dateOfBirth, Boolean isWizard, String ancestry, String eyeColour, String hairColour, String actor, boolean isHogwartsStudent, boolean isHogwartsStaff, boolean isAlive) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.species = species;
        this.gender = gender;
        this.house = house;
        this.dateOfBirth = dateOfBirth;
        this.isWizard = isWizard;
        this.ancestry = ancestry;
        this.eyeColour = eyeColour;
        this.hairColour = hairColour;
        this.actor = actor;
        this.isHogwartsStudent = isHogwartsStudent;
        this.isHogwartsStaff = isHogwartsStaff;
        this.isAlive = isAlive;



    }


    public void setImageBitmap(Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    public String getSpecies() {
        return species;
    }

    public String getGender() {
        return gender;
    }

    public String getHouse() {
        return house;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public Boolean getWizard() {
        return isWizard;
    }

    public String getAncestry() {
        return ancestry;
    }

    public String getEyeColour() {
        return eyeColour;
    }

    public String getHairColour() {
        return hairColour;
    }

    public String getActor() {
        return actor;
    }

    public boolean isHogwartsStudent() {
        return isHogwartsStudent;
    }

    public boolean isHogwartsStaff() {
        return isHogwartsStaff;
    }

    public Bitmap getImageBitmap() {
        return imageBitmap;
    }



    public boolean isAlive() {
        return isAlive;
    }

    public String getName() {
        return name;
    }


    public String getImageUrl() {
        return imageUrl;
    }



}
