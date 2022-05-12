package com.example.fit2081week2task2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

//Defines the ViewModel that the program will use to display each of the Database entries.
public class MovieViewModel extends AndroidViewModel {
    private MovieRepository mRepository;
    private LiveData<List<MovieDetails>> mAllMovies;
    private LiveData<List<MovieDetails>> mMoviesMoreThan100;

    public MovieViewModel(@NonNull Application application){
        super(application);
        mRepository = new MovieRepository(application);
        mAllMovies = mRepository.getAllMovies();
        mMoviesMoreThan100 = mRepository.listMoreThan100();
    }
    public LiveData<List<MovieDetails>> getAllMovies(){return mAllMovies;}
    public void insert(MovieDetails movie){mRepository.insert(movie);}
    public void deleteAll(){mRepository.deleteAll();}
    public void deleteLast(){mRepository.deleteLast();}

    public void deleteYoungerThan2000() {
        mRepository.deleteYoungerThan2000();
    }

    public void deleteLessThan100() {
        mRepository.deleteLessThan100();
    }

    public LiveData<List<MovieDetails>> listMoreThan100() {
        return mMoviesMoreThan100;
    }
    public void deleteHighestCost(){mRepository.deleteHighestCost();}
}
