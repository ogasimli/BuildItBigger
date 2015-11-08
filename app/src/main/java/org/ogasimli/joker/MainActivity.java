package org.ogasimli.joker;

import com.udacity.ogasimli.joker.R;

import org.ogasimli.joker.jokeview.JokeActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;


public abstract class MainActivity extends AppCompatActivity implements JokeAsyncTask.Callback {

    private JokeAsyncTask mAsyncTask;
    private ProgressDialog mProgressDialog;
    private ProgressBar mProgressBar;
    private Toast mToast;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    public void tellJoke(View view){
        loadJoke();
    }

    protected final void loadJoke(){
        //mProgressBar.setVisibility(View.VISIBLE);
        showProgressDialog(true);
        if (mAsyncTask != null) {
            mAsyncTask.cancel(true);
        }

        mAsyncTask = new JokeAsyncTask(this);
        mAsyncTask.execute();
    }

    @Override
    public void onJokeSuccess(String joke) {
        //mProgressBar.setVisibility(View.GONE);
        showProgressDialog(false);
        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_KEY, joke);
        startActivity(intent);
    }

    @Override
    public void onJokeError(IOException e) {
        //mProgressBar.setVisibility(View.GONE);
        showProgressDialog(false);
        Log.d("MainActivity", "ProgressBar is " + mProgressBar.getVisibility());
        showToast(e.getMessage());
    }

    private void showToast(String message) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        mToast.show();
    }

    //Helper method to show and hide ProgressDialog
    private void showProgressDialog(boolean show) {
        if (show) {
            mProgressDialog = ProgressDialog.show(this,
                    getString(R.string.progress_dialog_title),
                    getString(R.string.progress_dialog_content),true, true);
        }else if (mProgressDialog != null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }
}
