package com.example.fit2081week2task2;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDetailDao {
    @Query("select*from MovieDetails")
    LiveData<List<MovieDetails>> getAllMovies();

    @Query("select * from MovieDetails where MovieName =:name")
    List<MovieDetails> getMovie(String name);

    @Insert
    void addMovie(MovieDetails movie);

    @Query("delete FROM MovieDetails")
    void deleteAllMovies();

    @Query("delete FROM MovieDetails where MovieID = (SELECT MAX(MovieID)) in MovieDetails")
    void deleteLastMovie();

}