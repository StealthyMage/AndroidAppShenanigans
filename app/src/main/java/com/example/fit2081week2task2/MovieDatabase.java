package com.example.fit2081week2task2;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {MovieDetails.class}, version = 4)
public abstract class MovieDatabase extends RoomDatabase{
    public static final String MOVIE_DATABASE_NAME = "Movie_Database"; //Quite literally the DB name
    public abstract MovieDetailDao movieDetailDao(); //Creates a new instance of the DAO interface

    //A volatile variable syncs all copies of the variables in memory.
    private static volatile MovieDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    /*Executors allow the servicing of incoming threads for asynchronous tasks.*/

    static MovieDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            /*The Synchronized keyword locks a set of resources such that only one thread may
            * use it at a time, in this case the MovieDatabase class.*/
            synchronized (MovieDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MovieDatabase.class, MOVIE_DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return  INSTANCE;
    }

}
