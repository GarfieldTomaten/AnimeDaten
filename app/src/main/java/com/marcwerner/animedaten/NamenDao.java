package com.marcwerner.animedaten;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NamenDao {
    @Delete
    void delete(AnimeSuche anime);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(AnimeSuche anime);

    @Query("SELECT * FROM Anime_Table ORDER BY lastName ASC")
    LiveData<List<AnimeSuche>> getAlphabetizedWords();

    @Query("SELECT * FROM Anime_Table WHERE firstName LIKE :firstName AND lastName LIKE :lastName")
    LiveData<List<AnimeSuche>> getAnimeByFullName(String firstName, String lastName);
}
