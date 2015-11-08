package org.ogasimli.joker;

import com.udacity.ogasimli.joker.R;

import org.ogasimli.joker.jokeview.JokeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;


public abstract class MainActivity extends AppCompatActivity implements JokeAsyncTask.Callback {

    private JokeAsyncTask mAsyncTask;
    private Toast mToast;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void tellJoke(View view){
        loadJoke();
    }

    protected final void loadJoke(){
        if (mAsyncTask != null) {
            mAsyncTask.cancel(true);
        }

        mAsyncTask = new JokeAsyncTask(this);
        mAsyncTask.execute();
    }

    @Override
    public void onJokeSuccess(String joke) {
        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_KEY, joke);
        startActivity(intent);
    }

    @Override
    public void onJokeError(IOException e) {
        showToast(e.getMessage());
    }

    private void showToast(String message) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        mToast.show();
    }
}
