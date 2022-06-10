package br.kleberf65.androidutils.ads.banner;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.BannerCallbacks;
import com.appodeal.ads.BannerView;
import com.appodeal.ads.initializing.ApdInitializationCallback;
import com.appodeal.ads.initializing.ApdInitializationError;

import java.util.List;

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

        Appodeal.initialize((Activity) context, adsSettings.getAppodeal().getAppKey(), Appodeal.BANNER, list -> {

        });
        Appodeal.setTesting(adsSettings.isDebugMode());
        bannerView = Appodeal.getBannerView(context);
        bannerView.setId(ViewCompat.generateViewId());
        Appodeal.setBannerViewId(bannerView.getId());

        Appodeal.setBannerCallbacks(new BannerCallbacks() {
            @Override
            public void onBannerLoaded(int height, boolean isPrecache) {
                if (listener != null) listener.onAdsLoaded();
            }

            @Override
            public void onBannerFailedToLoad() {
                if (listener != null)
                    listener.onAdsError("onBannerFailedToLoad");
            }

            @Override
            public void onBannerShown() {
                // Called when banner is shown
            }

            @Override
            public void onBannerShowFailed() {
                if (listener != null)
                    listener.onAdsError("onBannerShowFailed");
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
        Appodeal.show((Activity) context, Appodeal.BANNER);
    }

    @Override
    public View getAdView() {
        return this.bannerView;
    }

}