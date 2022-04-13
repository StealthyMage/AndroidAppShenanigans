package com.example.fit2081week2task2;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {
    ArrayList<MovieDetails> datasource = new ArrayList<MovieDetails>();

    ArrayList<MovieDetails> dataSource;
    Week6TaskAdapter adapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private MovieViewModel mMovieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_layout);
        //gets the intent and the bundle holding the arraylist.
        Intent intent = getIntent();
        //Bundle args = intent.getBundleExtra("BUNDLE");
        //datasource = (ArrayList<MovieDetails>) args.getSerializable("ARRAYLIST");

        recyclerView=findViewById(R.id.recycler_layout_id);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        dataSource=new ArrayList<MovieDetails>();
        adapter=new Week6TaskAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setData(datasource);

        //Week7
        mMovieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        mMovieViewModel.getAllMovies().observe(this, datasource ->{adapter.setData((ArrayList<MovieDetails>) datasource);
        adapter.notifyDataSetChanged();});
        /*The above code instantiates a new instance of ViewModelProvider. This creates new ViewModels for the
        current class. Every time this activity is started, ViewModelProviders get all the movies currently
        stored in the database using both .observe and the .getAllMovies() function defined in the
        MovieDetailDAO*/
    }

}
