package com.example.fit2081week2task2;

import android.provider.BaseColumns;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "MovieDetails")
public class MovieDetails /*implements Serializable */{
    public static final String TABLE_NAME = "MovieDetails";
    public static final String COLUMN_ID = BaseColumns._ID;
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "MovieID")
    private int id;
    @ColumnInfo(name = "MovieName")
    private String movie_name;
    @ColumnInfo(name = "MovieYear")
    private String movie_year;
    @ColumnInfo(name = "MovieCountry")
    private String movie_country;
    @ColumnInfo(name = "MovieCost")
    private int movie_cost;
    @ColumnInfo(name = "MovieGenre")
    private String movie_genre;
    @ColumnInfo(name = "MovieKeywords")
    private String movie_keywords;


    public MovieDetails(String movie_name, String movie_year, String movie_country, int movie_cost, String movie_genre, String movie_keywords) {
        this.movie_name = movie_name;
        this.movie_year = movie_year;
        this.movie_country = movie_country;
        this.movie_cost = movie_cost;
        this.movie_genre = movie_genre;
        this.movie_keywords = movie_keywords;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public String getMovie_year() {
        return movie_year;
    }

    public String getMovie_country() {
        return movie_country;
    }

    public int getMovie_cost() {
        return movie_cost;
    }

    public String getMovie_genre() {
        return movie_genre;
    }

    public String getMovie_keywords() {
        return movie_keywords;
    }
    public int getId(){return id;}
    public void setId(@NonNull int id){this.id = id;}
}
