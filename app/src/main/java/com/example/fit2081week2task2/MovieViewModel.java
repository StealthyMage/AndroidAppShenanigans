package com.example.fit2081week2task2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

//
public class MovieViewModel extends AndroidViewModel {
    private MovieRepository mRepository;
    private LiveData<List<MovieDetails>> mAllMovies;

    public MovieViewModel(@NonNull Application application){
        super(application);
        mRepository = new MovieRepository(application);
        mAllMovies = mRepository.getAllMovies();
    }
    public LiveData<List<MovieDetails>> getAllMovies(){return mAllMovies;}
    public void insert(MovieDetails movie){mRepository.insert(movie);}
    public void deleteAll(){mRepository.deleteAll();}
    public void deleteLast(){mRepository.deleteLast();}
}
