package br.kleberf65.androidutils.ads.intertitial;

public interface AdsInterstitial {

    void loadAds();

    void showAds();

    void setOnLoadListener(AdsInterstitialListener adsInterstitialListener);
}
