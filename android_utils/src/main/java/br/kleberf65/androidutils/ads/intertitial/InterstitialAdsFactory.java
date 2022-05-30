package br.kleberf65.androidutils.ads.intertitial;

import android.content.Context;

import br.kleberf65.androidutils.ads.entities.AdsSettings;

public class InterstitialAdsFactory {

    public static AdsInterstitial build(Context context, AdsSettings adsSettings) {

        switch (adsSettings.getAdsType()) {
            case ADMOB:
                return AdmobAdsInterstitial.getInstance(context, adsSettings);
            case UNITY:
                return UnityAdsInterstitial.getInstance(context, adsSettings);
            case APP_LOVIN:
                return AppLovinAdsInterstitial.getInstance(context, adsSettings);
            default:
                return new EmptyAdsInterstitial();
        }
    }
}
