package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String titleInput, descriptionInput, linkInput;
    private EditText titleBox, descriptionBox, linkBox;
    private ArrayList<MovieInfo> watchlist;
    private LinearLayout watchlistContainer;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        watchlistContainer = findViewById(R.id.container_movie_cards);
        titleBox = findViewById(R.id.title_editText);
        descriptionBox = findViewById(R.id.decription_editText);
        linkBox = findViewById(R.id.link_editText);
        watchlist = new ArrayList<>();

        // Write a message to the database
        database = FirebaseDatabase.getInstance();
    }

    public void createMovie(View view) {
    /*
    Implements series of actions after the user clicks the button.
     */
        InputMethodManager mgr =
                (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(view.getWindowToken(), 0);

        titleInput = titleBox.getText().toString();
        descriptionInput = descriptionBox.getText().toString();
        linkInput = linkBox.getText().toString();
        MovieInfo movie = new MovieInfo(titleInput, descriptionInput, linkInput);
        watchlist.add(movie);

        this.writeToDatabase();
        this.addMovieCardToList(movie);
        this.resetEditText();
    }

    private void resetEditText() {
    /*
    Clears edit text.
     */
        titleBox.getText().clear();
        descriptionBox.getText().clear();
        linkBox.getText().clear();
    }

    private LinearLayout createHorizontalLinearLayout() {
    /*
    Returns a LinearLayout.
    return: LinearLayout
     */
        LinearLayout newLinearLayout = new LinearLayout(this);
        newLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        newLinearLayout.setId(View.generateViewId());
        return newLinearLayout;
    }

    private TextView createTextView(String text) {
    /*
    Returns a TextView.
    return: TextView
     */
        TextView newTextView = new TextView(this);
        newTextView.setText(text);
        newTextView.setTextColor(Color.WHITE);
        newTextView.setTextSize(20);
        newTextView.setGravity(Gravity.CENTER_VERTICAL);
        newTextView.setGravity(Gravity.START);
        newTextView.setId(View.generateViewId());
        return newTextView;
    }

    private CardView createMovieCard() {
    /*
    Returns a movie CardView.
    return: CardView
     */
        CardView newMovieCard = new CardView(this);
        newMovieCard.setId(View.generateViewId());
        newMovieCard.setBackgroundColor(Color.WHITE);
        newMovieCard.setContentPadding(10,10,10,10);
        return newMovieCard;
    }

    private void addMovieCardToList(MovieInfo movie) {
    /*
    Adds CardView dynamically to the container that holds the movie items.
    param movie: MovieInfo object
     */
        TextView movieInput = this.createTextView(movie.getMovieInfo());
        LinearLayout infoContainer = this.createHorizontalLinearLayout();
        infoContainer.addView(movieInput, LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);

        CardView newMovieCard = this.createMovieCard();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(5, 10, 5, 0);
        newMovieCard.addView(infoContainer, layoutParams);

        watchlistContainer.addView(newMovieCard, LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
    }

    private void writeToDatabase() {
    /*
    Writes data to database.
     */
        DatabaseReference myRef = database.getReference(titleInput + "/title/");
        myRef.setValue(titleInput);
        myRef = database.getReference(titleInput + "/description/");
        myRef.setValue(descriptionInput);
        myRef = database.getReference(titleInput + "/link/");
        myRef.setValue(linkInput);
    }

}
