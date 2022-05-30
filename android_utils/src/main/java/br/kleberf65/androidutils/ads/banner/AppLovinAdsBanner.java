package br.kleberf65.androidutils.ads.banner;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.sdk.AppLovinSdk;

import br.kleberf65.androidutils.ads.entities.AdsSettings;

public final class AppLovinAdsBanner implements AdsBannerView.AdsBanner {

    private final Context context;
    private final AdsSettings adsSettings;
    private final AdsBannerView.AdsBannerListener listener;
    private final RelativeLayout baseView;

    public AppLovinAdsBanner(Context context, AdsSettings adsSettings, AdsBannerView.AdsBannerListener listener) {
        this.context = context;
        this.adsSettings = adsSettings;
        this.listener = listener;
        this.baseView = new RelativeLayout(context);
    }


    @Override
    public void loadAd() {
        if (!AppLovinSdk.getInstance(context).isInitialized()) {
            AppLovinSdk.getInstance(context).setMediationProvider("max");
            AppLovinSdk.initializeSdk(context, configuration -> setupSettings());
        } else setupSettings();

    }

    private void setupSettings() {
        MaxAdView adView = new MaxAdView(adsSettings.getAppLovin().getBannerId(), context);
        adView.setListener(new MaxAdViewAdListener() {
            @Override
            public void onAdExpanded(MaxAd ad) {
            }

            @Override
            public void onAdCollapsed(MaxAd ad) {

            }

            @Override
            public void onAdLoaded(MaxAd ad) {
                if (listener != null) listener.onAdsLoaded();
            }

            @Override
            public void onAdDisplayed(MaxAd ad) {

            }

            @Override
            public void onAdHidden(MaxAd ad) {

            }

            @Override
            public void onAdClicked(MaxAd ad) {

            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {
                if (listener != null) listener.onAdsError(error.getMessage());
            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                if (listener != null) listener.onAdsError(error.getMessage());
            }
        });
        baseView.addView(adView);
        adView.loadAd();
    }

    @Override
    public View getAdView() {
        return baseView;
    }
}
