package br.kleberf65.androidutils.ads.intertitial;

public interface AdsInterstitialListener {

    default void onAdsLoaded(boolean initialLoading) {

    }

    default void onAdsDismissed() {

    }

    default void onAdsError(String message) {

    }
}
