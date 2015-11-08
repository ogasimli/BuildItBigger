package org.ogasimli.joker;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import org.ogasimli.joker.backend.myApi.MyApi;

import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.util.Log;

import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 * AsyncTask class to retrieve jokes form GCE
 */
public class JokeAsyncTask extends AsyncTask<Void, Void, Pair<IOException, String>> {

    private static String TAG = JokeAsyncTask.class.getSimpleName();

    private static MyApi myApiService = null;
    private WeakReference<Callback> mCallback;

    public JokeAsyncTask(Callback c) {
        mCallback = new WeakReference<>(c);
    }

    @Override
    protected Pair<IOException, String> doInBackground(Void... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://joker-1986.appspot.com/_ah/api/");
            // end options for devappserver

            myApiService = builder.build();
        }
        try {
            Log.d(TAG, "Executing.");
            String joke = myApiService.tellJoke().execute().getData();
            Log.d(TAG, "Executed.");
            return new Pair<>(null, joke);
        } catch (IOException e) {
            return new Pair<>(e, null);
        }
    }

    @Override
    protected void onPostExecute(Pair<IOException, String> result) {
        if (mCallback == null || mCallback.get() == null) {
            Log.w(TAG, "Task is detached.");
            return;
        }

        if (result.first != null) {
            mCallback.get().onJokeError(result.first);
            Log.e(TAG, "Exception: " + result.first.getMessage());
        } else if (result.second != null) {
            mCallback.get().onJokeSuccess(result.second);
            Log.d(TAG, "Joke: " + result.second);
        }
    }

    public interface Callback {
        void onJokeSuccess(String joke);

        void onJokeError(IOException e);
    }
}
