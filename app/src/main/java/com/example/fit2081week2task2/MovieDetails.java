package com.example.fit2081week2task2;

public class MovieDetails {
    private String movie_name;
    private String movie_year;
    private String movie_country;
    private String movie_cost;
    private String movie_genre;
    private String movie_keywords;

    public MovieDetails(String movie_name, String movie_year, String movie_country, String movie_cost, String movie_genre, String movie_keywords) {
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

    public String getMovie_cost() {
        return movie_cost;
    }

    public String getMovie_genre() {
        return movie_genre;
    }

    public String getMovie_keywords() {
        return movie_keywords;
    }
}
