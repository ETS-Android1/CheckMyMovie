package com.example.mojaaplikacja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class AddMovieActivity extends AppCompatActivity {

    Movie movie;
    Button addButon;
    EditText titleEditText, descriptionEditText, categoryEditText;
    DatabaseReference referenceDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        addButon = findViewById(R.id.addButtonId);
        titleEditText= findViewById(R.id.addTitleEditTextId);
        descriptionEditText = findViewById(R.id.addDescriptionEditTextId);
        categoryEditText = findViewById(R.id.addCategoryEditTextId);
        referenceDatabase = FirebaseDatabase.getInstance().getReference().child("movies");
        movie = new Movie();

        addButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movie.setCategory(categoryEditText.getText().toString().trim());
                movie.setDescription(descriptionEditText.getText().toString().trim());
                movie.setTitle(titleEditText.getText().toString().trim());
                //----------------------set default thumbnail-------------------------------------
                // you should copy URI from default picture in storage database
                movie.setThumbnail("https://firebasestorage.googleapis.com/v0/b/mojprojekt-24429.appspot.com/o/krists-luhaers-AtPWnYNDJnM-unsplash.jpg?alt=media&token=1a37c640-01dd-497c-849a-901906d808b6");
                //--------------------------------------------------------------------------------
                referenceDatabase.child(UUID.randomUUID().toString()).setValue(movie);
                Toast.makeText(getApplicationContext(),"Video was added successfully!",Toast.LENGTH_SHORT).show();
            }
        });

    }
}