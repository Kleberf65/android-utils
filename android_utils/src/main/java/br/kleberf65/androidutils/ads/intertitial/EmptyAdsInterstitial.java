package br.kleberf65.androidutils.ads.intertitial;

public class EmptyAdsInterstitial implements AdsInterstitial {

    private AdsInterstitialListener adsInterstitialListener;

    @Override
    public void loadAds() {
        if (adsInterstitialListener != null) adsInterstitialListener.onAdsLoaded(true);
    }

    @Override
    public void showAds() {
        if (adsInterstitialListener != null) adsInterstitialListener.onAdsDismissed();
    }

    @Override
    public void setOnLoadListener(AdsInterstitialListener adsInterstitialListener) {
        this.adsInterstitialListener = adsInterstitialListener;
    }
}
