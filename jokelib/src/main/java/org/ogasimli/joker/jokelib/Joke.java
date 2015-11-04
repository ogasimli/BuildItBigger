package org.ogasimli.joker.jokelib;

import java.util.Random;

/**
 * This class provides random jokes.
 * <p/>
 * Jokes was taken from <http://www.laughfactory.com/jokes/">here</a>
 */
public class Joke {

    public String tellJoke(){
        return mJokes[mRandom.nextInt(mJokes.length)];
    }

    private final Random mRandom = new Random();

    private final String[] mJokes = {
                "A husband and wife are trying to set up a new password for their computer. " +
                        "The husband puts, \"Mypenis,\" and the wife falls on the ground laughing" +
                        "because on the screen it says, \"Error. Not long enough.\"",

                "The teacher asked Jimmy, \"Why is your cat at school today Jimmy?\" Jimmy " +
                        "replied crying, \"Because I heard my daddy tell my mommy, 'I am going " +
                        "to eat that p*ssy once Jimmy leaves for school today!'\"",

                "Yo momma is so fat, I took a picture of her last Christmas and it's still " +
                        "printing.",

                "Yo momma is so fat when she got on the scale it said, \"I need your weight not " +
                        "your phone number.\"",

                "I had a broken vacuum, then I put a One Direction sticker on it and it suddenly " +
                        "sucked again.",

                "What did God say when he made the first black man? \"Damn, I burnt one.\""
    };
}
