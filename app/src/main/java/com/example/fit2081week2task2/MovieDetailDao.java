package com.example.fit2081week2task2;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

//DAO Methods for the database
/*A DAO is a Data Access Object and allows the program to interact with a database.*/
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

    @Query("delete from MovieDetails where MovieID = (Select MAX(MovieID) from MovieDetails)")
    void deleteLastMovie();

    @Query("DELETE FROM MovieDetails where MovieYear > 2000")
    void deleteYoungerThan2000();

    @Query("DELETE FROM MovieDetails where MovieCost < 100")
    void deleteLessThan100();

    @Query("SELECT * FROM MovieDetails WHERE MovieCost > 100")
    LiveData<List<MovieDetails>> listMoreThan100();
}
