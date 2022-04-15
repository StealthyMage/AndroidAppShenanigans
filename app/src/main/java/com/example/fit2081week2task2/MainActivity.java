package com.example.fit2081week2task2;


import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    Button mButton;
    EditText mMovieName;
    EditText mMovieYear;
    EditText mMovieCountry;
    EditText mMovieGenre;
    EditText mMovieCost;
    EditText mMovieKeywords;
    Button mClearButton;
    private DrawerLayout drawerlayout;
    private NavigationView navigationView;
    FloatingActionButton fab;
    ListView newList;
    ArrayList<String> mMovieArray = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    RecyclerView recyclerView;
    ArrayList<MovieDetails> datasource = new ArrayList<MovieDetails>();
    private MovieViewModel mMovieViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_activity_main);
        //SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE); // Opens a new shared preferences file (Week 3 Task 2)
        //SharedPreferences.Editor editor = sharedPref.edit(); //Opens an editor for the shared preferences file (Week 3 Task 2)
        mButton = findViewById(R.id.button); /*This line identifies the button for use by looking for the id given to it, in this case the ID is button*/
        mMovieName = findViewById(R.id.MovieNameEditText);/*This line identifies the text for use by looking for the id given to it, in this case the ID is MovieNameEditText (Generated by the program when I first created it)*/
        mMovieYear = findViewById(R.id.YearEditText);
        mMovieCountry = findViewById(R.id.CountryReleasedEditText);
        mMovieGenre = findViewById(R.id.GenreTextEdit);
        mMovieCost = findViewById(R.id.MovieCostEdit);
        mMovieKeywords = findViewById(R.id.KeywordsTextEdit);
        mClearButton = findViewById(R.id.ClearButton);
        fab = findViewById(R.id.fab);
        newList = findViewById(R.id.MovieList);
        SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE); // Opens a new shared preferences file (Week 3 Task 2)
        SharedPreferences.Editor editor = sharedPref.edit();
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS}, 0); //Requests Perms for reading sms messages. (Week 4 Task 3)
        MyBroadcastReceiver myBroadCastReceiver = new MyBroadcastReceiver(); //Creates new broadcast receiver (Week 4 Task 3)
        registerReceiver(myBroadCastReceiver, new IntentFilter(SMSReceiver.SMS_FILTER)); //Registers the broadcast receiver with the intent filter declared in the SMSReceiver Class. (Week 4 Task 3)

        //Week 5 tasks
        drawerlayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mMovieArray );
        newList.setAdapter(adapter);

        //Week6
        recyclerView=findViewById(R.id.recycler_layout_id);

        mMovieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);




        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Snackbar.make(view,"Adding Movie!", Snackbar.LENGTH_SHORT).setAction("Undo",undoOnClickListener).show();
                editor.putString("Movie Name", mMovieName.getText().toString());
                editor.putString("Year Released", mMovieYear.getText().toString());
                editor.putString("Movie Genre", mMovieGenre.getText().toString().toLowerCase());
                editor.putString("Movie Country", mMovieCountry.getText().toString());
                editor.putString("Movie Cost", mMovieCost.getText().toString());
                editor.putString("Movie Keywords", mMovieKeywords.getText().toString());
                editor.apply();
                addListItem();
                MovieDetails newMovie = new MovieDetails(mMovieName.getText().toString(), mMovieYear.getText().toString(), mMovieCountry.getText().toString(), mMovieCost.getText().toString(), mMovieGenre.getText().toString(), mMovieKeywords.getText().toString());
                mMovieViewModel.insert(newMovie);
                adapter.notifyDataSetChanged();

            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerlayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerlayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new MyNavigationListener());


        if (sharedPref != null) { //This if loop checks to see if there are values in the sharedPref file, and if so, to restore those values. (Week 3 Task 2)
            mMovieName.setText(sharedPref.getString("Movie Name", mMovieName.getText().toString()).toUpperCase());
            mMovieYear.setText(sharedPref.getString("Year Released", mMovieYear.getText().toString()));
            mMovieCountry.setText(sharedPref.getString("Movie Country", mMovieCountry.getText().toString()));
            mMovieGenre.setText(sharedPref.getString("Movie Genre", mMovieGenre.getText().toString()));
            mMovieCost.setText(sharedPref.getString("Movie Cost", mMovieCost.getText().toString()));
            mMovieKeywords.setText(sharedPref.getString("Movie Keywords", mMovieKeywords.getText().toString()));
        }
        mButton.setOnClickListener( /*This tells the program to look for the button being pressed*/
                new View.OnClickListener() { /*This tells the program to let the user view whatever is defined in this function*/
                    public void onClick(View view) {/*This tells the program to do something once the user has clicked on the button*/
                        Toast.makeText(MainActivity.this, mMovieName.getText().toString(), Toast.LENGTH_SHORT).show();/*This generates a toast event. What is displayed by the event is the movie name as defined by mMovieName.getText().toString()*/
                        //These lines make it so that when the button is clicked, the values of the movie most recently inputted are saved to be restored once the app closes. (Task 2)
                        editor.putString("Movie Name", mMovieName.getText().toString());
                        editor.putString("Year Released", mMovieYear.getText().toString());
                        editor.putString("Movie Genre", mMovieGenre.getText().toString().toLowerCase());
                        editor.putString("Movie Country", mMovieCountry.getText().toString());
                        editor.putString("Movie Cost", mMovieCost.getText().toString());
                        editor.putString("Movie Keywords", mMovieKeywords.getText().toString());
                        editor.apply();
                        addListItem();
                        MovieDetails newMovie = new MovieDetails(mMovieName.getText().toString(), mMovieYear.getText().toString(), mMovieCountry.getText().toString(), mMovieCost.getText().toString(), mMovieGenre.getText().toString(), mMovieKeywords.getText().toString());
                        mMovieViewModel.insert(newMovie);
                        adapter.notifyDataSetChanged();
                    }
                }
        );
        mClearButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) { //This just sets all the fields with an empty string to clear them. (Week 3 Task 3)
                        mMovieName.setText("");
                        mMovieYear.setText("");
                        mMovieCountry.setText("");
                        mMovieGenre.setText("");
                        mMovieCost.setText("");
                        mMovieKeywords.setText("");
                    }
                }
        );
    }

    private void addListItem() {
        mMovieArray.add(mMovieName.getText().toString() + " | " + mMovieYear.getText().toString());
        MovieDetails newDetails = new MovieDetails(mMovieName.getText().toString(),mMovieYear.getText().toString(),mMovieCountry.getText().toString(),mMovieCost.getText().toString(),mMovieGenre.getText().toString(),mMovieKeywords.getText().toString());
        //datasource.add(newDetails);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.options_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();

        if (id == R.id.clear_fields_menu_id) {
            mMovieName.setText("");
            mMovieYear.setText("");
            mMovieCountry.setText("");
            mMovieGenre.setText("");
            mMovieCost.setText("");
            mMovieKeywords.setText("");
        }

        return true;
    }


    //(Week 3 Task 1)
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) { //This saves each of the fields for when the phone is reoriented.
        savedInstanceState.putString("Movie Name", mMovieName.getText().toString());
        savedInstanceState.putString("Year Released", mMovieYear.getText().toString());
        savedInstanceState.putString("Movie Genre", mMovieGenre.getText().toString().toLowerCase());
        savedInstanceState.putString("Movie Cost", mMovieCost.getText().toString());
        savedInstanceState.putString("Movie Keywords", mMovieKeywords.getText().toString());
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) { //This restores each of the fields once the phone is reoriented.
        super.onRestoreInstanceState(savedInstanceState);

        mMovieName.setText(savedInstanceState.getString("Movie Name").toUpperCase());
        mMovieYear.setText(savedInstanceState.getString("Year Released"));
        mMovieGenre.setText(savedInstanceState.getString("Movie Genre"));
        mMovieCost.setText(savedInstanceState.getString("Movie Cost"));
        mMovieKeywords.setText(savedInstanceState.getString("Movie Keywords"));

    }

    //Week 4 Task 3
    private class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            //Gets the message from the intent
            String msg = intent.getStringExtra(SMSReceiver.SMS_MSG_KEY);

            //Reads the message to tokenise the values

            StringTokenizer sT = new StringTokenizer(msg, ";");
            String movieName = sT.nextToken();
            String movieYear = sT.nextToken();
            String movieCountry = sT.nextToken();
            String movieGenre = sT.nextToken();
            String movieCost = sT.nextToken();
            String movieKeyword = sT.nextToken();

            //These update the UI to populate the values.
            mMovieName.setText(movieName);
            mMovieYear.setText(movieYear);
            mMovieCountry.setText(movieCountry);
            mMovieGenre.setText(movieGenre);
            mMovieCost.setText(movieCost);
            mMovieKeywords.setText(movieKeyword);
        }
    }

    private class MyNavigationListener implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // get the id of the selected item
            SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE); // Opens a new shared preferences file (Week 3 Task 2)
            SharedPreferences.Editor editor = sharedPref.edit();
            int id = item.getItemId();
            if(id == R.id.RemoveLastMovie){
                //Calls the DeleteLast method as defined in MovieDetailDao.
                mMovieViewModel.deleteLast();
                mMovieArray.remove(mMovieArray.size()-1);
                //datasource.remove(datasource.size()-1);
               adapter.notifyDataSetChanged();
            }
            else if(id == R.id.AddMovieMenuID){
                /*editor.putString("Movie Name", mMovieName.getText().toString());
                editor.putString("Year Released", mMovieYear.getText().toString());
                editor.putString("Movie Genre", mMovieGenre.getText().toString().toLowerCase());
                editor.putString("Movie Country", mMovieCountry.getText().toString());
                editor.putString("Movie Cost", mMovieCost.getText().toString());
                editor.putString("Movie Keywords", mMovieKeywords.getText().toString());
                editor.apply();*/
                addListItem();
                //Creates a new MovieDetails object to insert into the database.
                MovieDetails newMovie = new MovieDetails(mMovieName.getText().toString(), mMovieYear.getText().toString(), mMovieCountry.getText().toString(), mMovieCost.getText().toString(), mMovieGenre.getText().toString(), mMovieKeywords.getText().toString());
                mMovieViewModel.insert(newMovie);
            }
            else if(id == R.id.ViewAllMovies){
                viewAllMovies(recyclerView);


                //getSupportFragmentManager().beginTransaction().add(R.id.frame_layout_id,Fragment1.newInstance()).addToBackStack("F1").commit();
            }
            else if(id == R.id.DeleteMoviesOlderThan2000){
                mMovieViewModel.deleteYoungerThan2000();
            }
            else if(id == R.id.DeleteMoviesLessThan100){
                mMovieViewModel.deleteLessThan100();
            }
            else if(id == R.id.ListMoreThan100){
               listMoreThan100(recyclerView);
            }
            else {
               while(0 < mMovieArray.size()){
                    mMovieArray.remove(mMovieArray.size()-1);
                    //datasource.remove(datasource.size()-1);
                    adapter.notifyDataSetChanged();
                }
               //Deletes all of the entries in the Database.
                mMovieViewModel.deleteAll();
            }
            // close the drawer
            drawerlayout.closeDrawers();
            // tell the OS
            return true;
        }
    }
    //This method starts a new activity with RecyclerActivity.
    public void viewAllMovies(View view){
        Intent intent = new Intent(this, RecyclerActivity.class);
        //Bundle args = new Bundle();
        //args.putSerializable("ARRAYLIST",(Serializable)datasource);
        //intent.putExtra("BUNDLE",args);
        startActivity(intent);
    }
    public void listMoreThan100(View view){
        Intent intent = new Intent(this, NewRecycler.class);
        startActivity(intent);
    }
    View.OnClickListener undoOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mMovieArray.remove(mMovieArray.size()-1);
            adapter.notifyDataSetChanged();
        }
    };
}