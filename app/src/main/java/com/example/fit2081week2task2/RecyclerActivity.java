package com.example.fit2081week2task2;

import android.content.Intent;
import android.os.Bundle;

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
    ArrayList<MovieDetails> datasource = new ArrayList<MovieDetails>();

    ArrayList<MovieDetails> dataSource;
    Week6TaskAdapter adapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_layout);
        Intent intent = getIntent();
       // mMovieName = intent.getStringExtra(MainActivity.EXTRA_MOVIE_NAME);
       // mMovieYear = intent.getStringExtra(MainActivity.EXTRA_MOVIE_YEAR);
       // mMovieCountry = intent.getStringExtra(MainActivity.EXTRA_MOVIE_COUNTRY);
       // mMovieCost = intent.getStringExtra(MainActivity.EXTRA_MOVIE_COST);
       // mMovieGenre = intent.getStringExtra(MainActivity.EXTRA_MOVIE_GENRE);
      //  mMovieKeywords = intent.getStringExtra(MainActivity.EXTRA_MOVIE_KEYWORDS);
        Bundle args = intent.getBundleExtra("BUNDLE");
        datasource = (ArrayList<MovieDetails>) args.getSerializable("ARRAYLIST");

        recyclerView=findViewById(R.id.recycler_layout_id);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        dataSource=new ArrayList<MovieDetails>();
        adapter=new Week6TaskAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setData(datasource);
        addItem();
    }
    public void addItem() {
        MovieDetails newDetails = new MovieDetails("Movie name: " + mMovieName,"Movie year: " +mMovieYear,"Movie country: " + mMovieCountry,"Movie cost: " +mMovieCost,"Movie genre: " +mMovieGenre,"Movie keywords: " +mMovieKeywords);
        datasource.add(newDetails);
        adapter.notifyDataSetChanged();

    }

}
