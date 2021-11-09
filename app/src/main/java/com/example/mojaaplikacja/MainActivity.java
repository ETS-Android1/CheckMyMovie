package com.example.mojaaplikacja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Animation topAnim, bottomAnim;
    ImageView movieImage;
    TextView checkMyMovieTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_anim);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        movieImage = findViewById(R.id.movieImageViewId);
        movieImage.setAnimation(topAnim);

        checkMyMovieTextView = findViewById(R.id.checkMyMovieTextViewId);
        checkMyMovieTextView.setAnimation(bottomAnim);

    }

    public void goToAppMethod(View view) {
        Intent goToMenuIntent;
        goToMenuIntent = new Intent(getApplicationContext(),MoviesCollectionActivity.class);
        startActivity(goToMenuIntent);
    }
}
