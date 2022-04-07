package com.example.fit2081week2task2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Week6TaskAdapter extends RecyclerView.Adapter<Week6TaskAdapter.MyViewHolder> {

    ArrayList<MovieDetails> ds;
    int counter;


    public Week6TaskAdapter(ArrayList<MovieDetails> ds) {
        this.ds = ds;
        counter = 0;
    }

    @NonNull
    @Override
    public Week6TaskAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);

        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Week6TaskAdapter.MyViewHolder holder, int position) {


        holder.movieName.setText(ds.get(position).getMovie_name());
        holder.movieYear.setText(ds.get(position).getMovie_year());
        holder.movieCountry.setText(ds.get(position).getMovie_country());
        holder.movieCost.setText(ds.get(position).getMovie_cost());
        holder.movieGenre.setText(ds.get(position).getMovie_genre());
        holder.movieKeywords.setText(ds.get(position).getMovie_keywords());


    }

    @Override
    public int getItemCount() {
        return ds.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView movieName;
        TextView movieYear;
        TextView movieCountry;
        TextView movieCost;
        TextView movieGenre;
        TextView movieKeywords;
        int holderId;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            holderId = counter++;
            movieName = itemView.findViewById(R.id.movie_name_id);
            movieYear = itemView.findViewById(R.id.movie_year_id);
            movieCountry = itemView.findViewById(R.id.movie_country_id);
            movieCost = itemView.findViewById(R.id.movie_cost_id);
            movieGenre = itemView.findViewById(R.id.movie_genre_id);
            movieKeywords = itemView.findViewById(R.id.movie_keywords_id);

        }
    }
}
