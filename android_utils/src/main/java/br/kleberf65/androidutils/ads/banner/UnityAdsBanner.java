package br.kleberf65.androidutils.ads.banner;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.BannerErrorInfo;
import com.unity3d.services.banners.BannerView;
import com.unity3d.services.banners.UnityBannerSize;

import br.kleberf65.androidutils.ads.entities.AdsSettings;

public final class UnityAdsBanner implements AdsBannerView.AdsBanner {

    private final Context context;
    private final AdsSettings adsSettings;
    private final AdsBannerView.AdsBannerListener listener;
    private BannerView bannerView;

    public UnityAdsBanner(Context context, AdsSettings adsSettings, AdsBannerView.AdsBannerListener listener) {
        this.context = context;
        this.adsSettings = adsSettings;
        this.listener = listener;
    }

    @Override
    public void loadAd() {

        if (!UnityAds.isInitialized()) {
            UnityAds.initialize(context, adsSettings.getUnity().getGameId(), adsSettings.isDebugMode(), null);
        }
        bannerView = new BannerView((Activity) context, adsSettings.getUnity().getBannerId(), UnityBannerSize.getDynamicSize(context));
        bannerView.setListener(new BannerView.Listener() {
            @Override
            public void onBannerLoaded(BannerView bannerAdView) {
                if (listener != null) listener.onAdsLoaded();
            }

            @Override
            public void onBannerFailedToLoad(BannerView bannerAdView, BannerErrorInfo errorInfo) {
                if (listener != null) listener.onAdsError(errorInfo.errorMessage);
            }
        });
        bannerView.load();
    }

    @Override
    public View getAdView() {
        return bannerView;
    }

}
