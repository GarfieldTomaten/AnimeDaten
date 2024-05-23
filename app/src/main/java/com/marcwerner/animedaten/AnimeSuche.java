package com.marcwerner.animedaten;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Anime_Table")
public class AnimeSuche {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String firstName;
    private String lastName;

    public AnimeSuche(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
