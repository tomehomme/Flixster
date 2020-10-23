package com.example.flixster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import java.util.List;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    // context so we can inflate the view - where the adapter view is
    Context context;
    List<Movie> movies; // List of movies for the view to hold on

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    // Involves inflating a layout from XML and return the holder
    // creates the initial views (should run once for all the views)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter", "onCreateViewHolder");
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    // populating the data into the item thru holder
    // gets called when new views are on the screen
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("MovieAdapter", "onBindViewHodler" + position);
        // get the movie position at is passed in thru position
        Movie movie = movies.get(position);
        // bind the movie data into the view holder
        holder.bind(movie);
    }

    // returns the number of movies
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;
        // bind each view in the viewholder
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // define where each come from in the itemView obj
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
        }

        public void bind(Movie movie) {
            // will bind the movie
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            // use the Glide library to render images
            // we will load the movie poster path into our ivPoster
            String imgUrl;
            // if phone is in landscape, then set imgURL to backdrop
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                imgUrl = movie.getBackdropPath();
            }
            else {
                imgUrl = movie.getPosterPath();
            }
            // else default to poser image
            Glide.with(context).load(imgUrl).into(ivPoster);

        }
    }
}
