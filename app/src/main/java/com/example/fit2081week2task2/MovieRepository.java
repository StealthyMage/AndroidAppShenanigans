package com.example.fit2081week2task2;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MovieRepository {
    private MovieDetailDao mMovieDetailsDao;
    private LiveData<List<MovieDetails>> mAllMovies;
    private LiveData<List<MovieDetails>> mMoviesMore100;

    MovieRepository(Application application){
        MovieDatabase db = MovieDatabase.getDatabase(application);
        mMovieDetailsDao = db.movieDetailDao();
        mAllMovies = mMovieDetailsDao.getAllMovies();
        mMoviesMore100 = mMovieDetailsDao.listMoreThan100();
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

    public void deleteYoungerThan2000() {
        MovieDatabase.databaseWriteExecutor.execute(() ->{mMovieDetailsDao.deleteYoungerThan2000();});
    }

    public void deleteLessThan100() {
        MovieDatabase.databaseWriteExecutor.execute(() ->{mMovieDetailsDao.deleteLessThan100();});
    }

    LiveData<List<MovieDetails>> listMoreThan100() {
        return mMoviesMore100;
    }
}
