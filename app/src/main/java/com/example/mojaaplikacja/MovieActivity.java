package com.example.mojaaplikacja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MovieActivity extends AppCompatActivity {

    TextView titleTextView, descriptionTextView, categoryTextView, rateTextView;
    ImageView movieImageView;
    DatabaseReference referenceDatabase;
    RatingBar ratingBar;
    float myRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        titleTextView = findViewById(R.id.titleTextViewId);
        descriptionTextView = findViewById(R.id.descriptionTextViewId);
        categoryTextView = findViewById(R.id.categoryTextViewId);
        movieImageView = findViewById(R.id.thumbnailInActivityImageViewId);
        ratingBar = findViewById(R.id.ratingBarId);
        rateTextView = findViewById(R.id.userRateTextViewId);

        Intent receivedIntent = getIntent();
        String title = receivedIntent.getExtras().getString("MovieTitle");
        String description = receivedIntent.getExtras().getString("Description");
        String category = receivedIntent.getExtras().getString("Category");
        String image = receivedIntent.getExtras().getString("Thumbnail");
        String key = receivedIntent.getExtras().getString("Key");
        int rate = receivedIntent.getExtras().getInt("Rate");

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                referenceDatabase = FirebaseDatabase.getInstance().getReference().child("movies");
                referenceDatabase.child(key).child("rate").setValue(rating);
                rateTextView.setText(" " + rating);
            }
        });
        titleTextView.setText(title);
        descriptionTextView.setText(description);
        categoryTextView.setText(category);
        Picasso.get().load(image).into(movieImageView);
        ratingBar.setRating(rate);
    }
}