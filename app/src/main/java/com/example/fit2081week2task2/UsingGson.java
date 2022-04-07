package com.example.fit2081week2task2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class UsingGson extends AppCompatActivity {

    ArrayList<MovieDetails> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.using_gson);

        Gson gson=new Gson();

        SharedPreferences sP=getSharedPreferences(MainActivity.ITEMS_SP_FILE_NAME,MODE_PRIVATE);
        String dbStr=sP.getString(MainActivity.ITEMS_KEY,"");

        Type type = new TypeToken<ArrayList<MovieDetails>>() {}.getType();
        items = gson.fromJson(dbStr,type);
        android.widget.Toast.makeText(this, items.size()+"", Toast.LENGTH_SHORT).show();
    }
}
