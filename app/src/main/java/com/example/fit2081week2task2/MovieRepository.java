package com.example.fit2081week2task2;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MovieRepository {
    private MovieDetailDao mMovieDetailsDao;
    private LiveData<List<MovieDetails>> mAllMovies;

    MovieRepository(Application application){
        MovieDatabase db = MovieDatabase.getDatabase(application);
        mMovieDetailsDao = db.movieDetailDao();
        mAllMovies = mMovieDetailsDao.getAllMovies();
    }

    LiveData<List<MovieDetails>>getAllMovies(){return mAllMovies;}

    void insert(MovieDetails movie){
        MovieDatabase.databaseWriteExecutor.execute(() -> mMovieDetailsDao.addMovie(movie));
    }
    void deleteAll(){
        MovieDatabase.databaseWriteExecutor.execute(() ->{mMovieDetailsDao.deleteAllMovies();});
    }
    void deleteLast(){
        MovieDatabase.databaseWriteExecutor.execute(() ->{mMovieDetailsDao.deleteLastMovie();});
    }
}