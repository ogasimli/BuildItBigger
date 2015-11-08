package org.ogasimli.joker;

import android.test.AndroidTestCase;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class JokeAsyncTaskTest extends AndroidTestCase {

    private CountDownLatch mCountDownLatch = new CountDownLatch(1);
    private String mJoke;

    public void jokeTest() {
        JokeAsyncTask task = new JokeAsyncTask(new JokeAsyncTask.Callback() {
            @Override
            public void onJokeSuccess(String joke) {
                mJoke = joke;
                mCountDownLatch.countDown();
            }

            @Override
            public void onJokeError(IOException e) {
                fail(e.getMessage());
            }
        });
        task.execute();

        try {
            mCountDownLatch.await(20, TimeUnit.SECONDS);
            assertNotNull("Retrieved joke is:", mJoke);
        } catch (InterruptedException e) {
            fail(e.getMessage());
        }
    }
}