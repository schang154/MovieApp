package com.example.movieapp;

import com.google.firebase.database.FirebaseDatabase;

class MovieInfo {
    /*
    This is a class that contains information about the movie.
     */

    private String title;
    private String description;
    private String link;

    MovieInfo(String title, String description, String link) {
        this.title = title;
        this.description = description;
        this.link = link;
    }


    String getTitle() {
        return this.title;
    }

    String getDescription() {
        return this.description;
    }

    String getLink() {
        return link;
    }

    protected String getMovieInfo() {
        return String.format("%s\n\nDescription:\n%s\n\nLink: \n%s",
                getTitle(), getDescription(), getLink());
    }
}

