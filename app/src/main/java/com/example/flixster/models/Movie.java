package com.example.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// will encapsulate the idea of a movie
public class Movie {
    String posterPath;
    String title;
    String overview;
    public Movie(JSONObject jsonObject) throws JSONException {
        // if any get string fails then the JSONException will handle it
        posterPath = jsonObject.getString("poster_path");
        title =  jsonObject.getString("original_title");
        overview = jsonObject.getString("overview");
    }
    // return a list of movies
    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        // iterate through all movies in movieJsonArray
        for (int i = 0; i < movieJsonArray.length(); i++) {
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
    }

    public String getPosterPath() {
        // posterPath is currently a relative url, so we will convert it into a URL
        // here we are hardcoding the width to be 342
        // TODO: fetch all available sizes then append to base url then append the posterPath
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }
}
