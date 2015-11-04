package org.ogasimli.joker.jokeview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Main activity class to show jokes
 * Created by ogasimli on 04.11.2015.
 */
public class JokeActivity extends AppCompatActivity {

    public static final String JOKE_KEY = "Joke key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String jokeLine = intent.getStringExtra(JOKE_KEY);
        TextView jokeText = (TextView) findViewById(R.id.joke_text);

        if (jokeLine != null && jokeLine.length() != 0) {
            jokeText.setText(jokeLine);
        }

        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home == item.getItemId()) {
            super.onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
