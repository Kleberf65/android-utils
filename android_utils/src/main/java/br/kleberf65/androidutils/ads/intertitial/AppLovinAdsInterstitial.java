package br.kleberf65.androidutils.ads.intertitial;

import android.app.Activity;
import android.content.Context;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.sdk.AppLovinSdk;

import br.kleberf65.androidutils.ads.entities.AdsSettings;

public class AppLovinAdsInterstitial implements AdsInterstitial {

    private static AdsInterstitial adsInterstitial;
    private final Context context;
    private final AdsSettings adsSettings;
    private MaxInterstitialAd interstitialAd;
    private AdsInterstitialListener adsInterstitialListener;
    private boolean initialLoading = true;

    public AppLovinAdsInterstitial(Context context, AdsSettings adsSettings) {
        this.context = context;
        this.adsSettings = adsSettings;
    }

    public static AdsInterstitial getInstance(Context context, AdsSettings adsSettings) {
        if (adsInterstitial == null) {
            adsInterstitial = new AppLovinAdsInterstitial(context, adsSettings);
        }
        return adsInterstitial;
    }

    @Override
    public void loadAds() {
        if (!AppLovinSdk.getInstance(context).isInitialized()) {
            AppLovinSdk.getInstance(context).setMediationProvider("max");
            AppLovinSdk.initializeSdk(context, config -> setupSettings());
        } else setupSettings();
    }

    private void setupSettings() {
        interstitialAd = new MaxInterstitialAd(adsSettings.getAppLovin().getInterstitialId(), (Activity) context);
        interstitialAd.setListener(new MaxAdListener() {
            @Override
            public void onAdLoaded(MaxAd ad) {
                if (existsListener()) adsInterstitialListener.onAdsLoaded(initialLoading);
                initialLoading = false;
            }

            @Override
            public void onAdDisplayed(MaxAd ad) {

            }

            @Override
            public void onAdHidden(MaxAd ad) {
                if (existsListener()) adsInterstitialListener.onAdsDismissed();
                interstitialAd.loadAd();
            }

            @Override
            public void onAdClicked(MaxAd ad) {

            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {
                if (existsListener()) adsInterstitialListener.onAdsError(error.getMessage());
                loadAds();
            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                if (existsListener()) adsInterstitialListener.onAdsError(error.getMessage());
                loadAds();
            }
        });
        interstitialAd.loadAd();

    }


    @Override
    public void showAds() {
        if (interstitialAd != null && interstitialAd.isReady()) {
            interstitialAd.showAd();
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
