package br.kleberf65.androidutils.ads.banner;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.BannerCallbacks;
import com.appodeal.ads.BannerView;

import br.kleberf65.androidutils.ads.entities.AdsSettings;

public final class AppodealAdsBanner implements AdsBannerView.AdsBanner {

    private final Context context;
    private final AdsSettings adsSettings;
    private final AdsBannerView.AdsBannerListener listener;
    private BannerView bannerView;

    public AppodealAdsBanner(Context context, AdsSettings adsSettings, AdsBannerView.AdsBannerListener listener) {
        this.context = context;
        this.adsSettings = adsSettings;
        this.listener = listener;
    }

    @Override
    public void loadAd() {

        if (!Appodeal.isInitialized(Appodeal.BANNER)) {
            Appodeal.initialize((Activity) context, adsSettings.getAppodeal().getAppKey(), Appodeal.BANNER, false);
            Appodeal.setTesting(adsSettings.isDebugMode());
        }

        bannerView = Appodeal.getBannerView(context);
        Appodeal.setBannerCallbacks(new BannerCallbacks() {
            @Override
            public void onBannerLoaded(int height, boolean isPrecache) {
                if (listener != null) listener.onAdsLoaded();
            }

            @Override
            public void onBannerFailedToLoad() {
                if (listener != null) listener.onAdsError("onBannerFailedToLoad");
            }

            @Override
            public void onBannerShown() {
                // Called when banner is shown
            }

            @Override
            public void onBannerShowFailed() {
                if (listener != null) listener.onAdsError("onBannerShowFailed");
            }

            @Override
            public void onBannerClicked() {
                // Called when banner is clicked
            }

            @Override
            public void onBannerExpired() {
                // Called when banner is expired
            }
        });

    }

    @Override
    public View getAdView() {
        return this.bannerView;
    }

}
