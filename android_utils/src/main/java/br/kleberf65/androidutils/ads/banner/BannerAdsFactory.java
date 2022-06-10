package br.kleberf65.androidutils.ads.banner;

import android.content.Context;

import br.kleberf65.androidutils.ads.entities.AdsSettings;

public class BannerAdsFactory {

    public static AdsBannerView.AdsBanner build(Context context, AdsSettings adsSettings, AdsBannerView.AdsBannerListener listener) {
        switch (adsSettings.getAdsType()) {
            case ADMOB:
                return new AdmobAdsBanner(context, adsSettings, listener);
            case UNITY:
                return new UnityAdsBanner(context, adsSettings, listener);
            case APP_LOVIN:
                return new AppLovinAdsBanner(context, adsSettings, listener);
            case APPODEAL:
                return new AppodealAdsBanner(context, adsSettings, listener);
            default:
                return new EmptyAdsBanner(context);
        }
    }
}
