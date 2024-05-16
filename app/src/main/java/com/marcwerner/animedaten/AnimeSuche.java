package com.marcwerner.animedaten;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Anime")
public class AnimeSuche {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public AnimeSuche(String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void delete(AnimeSuche anime) {
    }
}
