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
        Bundle args = intent.getBundleExtra("BUNDLE");
        datasource = (ArrayList<MovieDetails>) args.getSerializable("ARRAYLIST");

        recyclerView=findViewById(R.id.recycler_layout_id);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        dataSource=new ArrayList<MovieDetails>();
        adapter=new Week6TaskAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setData(datasource);
    }

}
