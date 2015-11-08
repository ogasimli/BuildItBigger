package org.ogasimli.joker;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import com.udacity.ogasimli.joker.R;

import android.os.Bundle;
import android.view.View;


public class ExtendedMainActivity extends MainActivity {

    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instantiate InterstitialAd object
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_banner_ad_unit_id));

        //Create InterstitialAd AdListener
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                loadAd(null, mInterstitialAd);
                loadJoke();
            }
        });

        //Load banner ad
        AdView mAdView = (AdView) findViewById(R.id.adView);
        loadAd(mAdView, mInterstitialAd);
    }

    @Override
    public void tellJoke(View view) {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            super.tellJoke(view);
        }
    }

    //Helper method to load ad banner
    private void loadAd(AdView adView, InterstitialAd interstitialAd){
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        if (adView != null) {
            adView.loadAd(adRequest);
        } else {
            interstitialAd.loadAd(adRequest);
        }
    }
}
