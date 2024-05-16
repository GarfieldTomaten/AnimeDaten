package com.marcwerner.animedaten;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NamenDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(AnimeSuche word);

    @Query("DELETE FROM Anime")
    void deleteAll();

    @Query("SELECT * FROM Anime ORDER BY lastName ASC")
    LiveData<List<AnimeSuche>> getAlphabetizedWords();

    @Query("SELECT * FROM Anime WHERE firstname LIKE :firstName AND lastName LIKE :lastName")
    LiveData<List<AnimeSuche>> getAnimeByFullName(String firstName, String lastName);


}