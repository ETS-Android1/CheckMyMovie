package com.example.mojaaplikacja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MoviesCollectionActivity extends AppCompatActivity {

    List<Movie> movieList;
    ImageButton addMovieBtn;
    RecyclerViewAdapter myAdapter;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_collection);

        movieList = new ArrayList<>();
        final RecyclerView myRecyclerView = findViewById(R.id.recyclerviewId);
        myAdapter = new RecyclerViewAdapter(this,movieList);
        myRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        addMovieBtn = findViewById(R.id.addMovieImageButtonId);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("movies");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                movieList.clear(); //preventing duplication of objects

                for(DataSnapshot dataSnapshot1:snapshot.getChildren())
                {
                    Movie movie = dataSnapshot1.getValue(Movie.class);
                    movie.setKey(dataSnapshot1.getKey()); //get movie id/key from database to assign it to created object
                    movieList.add(movie);
                }
                myRecyclerView.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MoviesCollectionActivity.this, "Opsss..Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void addMovieMethod(View view) {
        Intent intent = new Intent(this,AddMovieActivity.class);
        this.startActivity(intent);
    }
}