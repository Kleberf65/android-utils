package br.kleberf65.androidutils.ads.intertitial;

import android.app.Activity;
import android.content.Context;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.InterstitialCallbacks;

import br.kleberf65.androidutils.ads.entities.AdsSettings;

public class AppodealInterstitial implements AdsInterstitial {

    private final Context context;
    private final AdsSettings adsSettings;
    private static AdsInterstitial adsInterstitial;
    private AdsInterstitialListener adsInterstitialListener;
    private boolean initialLoading = true;

    private final InterstitialCallbacks callbacks = new InterstitialCallbacks() {
        @Override
        public void onInterstitialLoaded(boolean isPrecache) {
            if (existsListener()) adsInterstitialListener.onAdsLoaded(initialLoading);
            initialLoading = false;
        }

        @Override
        public void onInterstitialFailedToLoad() {
            if (existsListener()) adsInterstitialListener.onAdsError("onInterstitialFailedToLoad");
        }

        @Override
        public void onInterstitialShown() {
            // Called when interstitial is shown
        }

        @Override
        public void onInterstitialShowFailed() {
            if (existsListener()) adsInterstitialListener.onAdsError("onInterstitialShowFailed");
            loadAds();
        }

        @Override
        public void onInterstitialClicked() {
            // Called when interstitial is clicked
        }

        @Override
        public void onInterstitialClosed() {
            if (existsListener()) adsInterstitialListener.onAdsDismissed();
        }

        @Override
        public void onInterstitialExpired() {
            // Called when interstitial is expired
        }
    };

    public AppodealInterstitial(Context context, AdsSettings adsSettings) {
        this.context = context;
        this.adsSettings = adsSettings;
    }

    public static AdsInterstitial getInstance(Context context, AdsSettings adsSettings) {
        if (adsInterstitial == null)
            adsInterstitial = new AppodealInterstitial(context, adsSettings);
        return adsInterstitial;
    }


    @Override
    public void loadAds() {
        if (!Appodeal.isInitialized(Appodeal.INTERSTITIAL)) {
            Appodeal.initialize((Activity) context, adsSettings.getAppodeal().getAppKey(), Appodeal.INTERSTITIAL, false);
            Appodeal.setTesting(adsSettings.isDebugMode());
        }
        Appodeal.setInterstitialCallbacks(callbacks);
    }

    @Override
    public void showAds() {
        if (Appodeal.isInitialized(Appodeal.INTERSTITIAL)) {
            Appodeal.show((Activity) context, Appodeal.INTERSTITIAL);
        } else if (existsListener()) adsInterstitialListener.onAdsDismissed();

    }

    @Override
    public void setOnLoadListener(AdsInterstitialListener adsInterstitialListener) {
        this.adsInterstitialListener = adsInterstitialListener;
    }

    private boolean existsListener() {
        return adsInterstitialListener != null;
    }
}
