package com.example.mojaaplikacja;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Movie> movies;


    public RecyclerViewAdapter(Context mContext, List<Movie> movies) {
        this.mContext = mContext;
        this.movies = movies;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_movie, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.movieTitleTextView.setText(movies.get(position).getTitle());
        Picasso.get().load(movies.get(position).getThumbnail()).into(holder.movieThumbImageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MovieActivity.class);
                intent.putExtra("MovieTitle", movies.get(position).getTitle());
                intent.putExtra("Description", movies.get(position).getDescription());
                intent.putExtra("Thumbnail", movies.get(position).getThumbnail());
                intent.putExtra("Category", movies.get(position).getCategory());
                intent.putExtra("Rate", movies.get(position).getRate());
                intent.putExtra("Key", movies.get(position).getKey());
                //start the activity
                mContext.startActivity(intent);
            }
        });

        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(mContext).setTitle("Delete")
                        .setMessage("Are you sure to delete this movie?").setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String movieID = movies.get(position).getKey();
                        FirebaseDatabase.getInstance().getReference("movies").child(movieID).removeValue();
                    }
                })
                        .setNegativeButton(android.R.string.cancel, null).setIcon(android.R.drawable.ic_dialog_alert).show();
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView movieTitleTextView;
        ImageView movieThumbImageView;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitleTextView = itemView.findViewById(R.id.movieTitleTextViewId);
            movieThumbImageView = itemView.findViewById(R.id.movieThumbnailImageViewId);
            cardView = itemView.findViewById(R.id.cardViewId);
        }
    }


}
