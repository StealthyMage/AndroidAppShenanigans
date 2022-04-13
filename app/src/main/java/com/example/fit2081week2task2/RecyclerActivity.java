package com.example.fit2081week2task2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {
    String mMovieName;
    String mMovieYear;
    String mMovieCountry;
    String mMovieGenre;
    String mMovieCost;
    String mMovieKeywords;

    ArrayList<MovieDetails> dataSource;
    Week6TaskAdapter adapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_layout);
        Intent intent = getIntent();
        mMovieName = intent.getStringExtra(MainActivity.EXTRA_MOVIE_NAME);
        mMovieYear = intent.getStringExtra(MainActivity.EXTRA_MOVIE_YEAR);
        mMovieCountry = intent.getStringExtra(MainActivity.EXTRA_MOVIE_COUNTRY);
        mMovieCost = intent.getStringExtra(MainActivity.EXTRA_MOVIE_COST);
        mMovieGenre = intent.getStringExtra(MainActivity.EXTRA_MOVIE_GENRE);
        mMovieKeywords = intent.getStringExtra(MainActivity.EXTRA_MOVIE_KEYWORDS);

        recyclerView=findViewById(R.id.recycler_layout_id);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        dataSource=new ArrayList<MovieDetails>();
        adapter=new Week6TaskAdapter(dataSource);
        recyclerView.setAdapter(adapter);
    }
    public void addItem(View view) {
        MovieDetails newDetails = new MovieDetails(mMovieName,mMovieYear,mMovieCountry,mMovieCost,mMovieGenre,mMovieKeywords);
        dataSource.add(newDetails);
        adapter.notifyDataSetChanged();

    }

}
