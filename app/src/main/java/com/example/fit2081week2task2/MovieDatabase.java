package com.example.fit2081week2task2;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {MovieDetails.class}, version = 3)
public abstract class MovieDatabase extends RoomDatabase{
    public static final String MOVIE_DATABASE_NAME = "Movie_Database";
    public abstract MovieDetailDao movieDetailDao();

    private static volatile MovieDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static MovieDatabase getDatabase(final Context context){
        if(INSTANCE == null){
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
