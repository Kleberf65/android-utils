package br.kleberf65.androidutils.ads.banner;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;

import java.util.Arrays;
import java.util.List;

import br.kleberf65.androidutils.ads.entities.AdsSettings;

public final class AdmobAdsBanner implements AdsBannerView.AdsBanner {

    private final Context context;
    private final AdsSettings adsSettings;
    private final AdsBannerView.AdsBannerListener listener;
    private String[] devicesIds = new String[]{};
    private AdView adView;

    public AdmobAdsBanner(Context context, AdsSettings adsSettings, AdsBannerView.AdsBannerListener listener) {
        this.context = context;
        this.adsSettings = adsSettings;
        this.listener = listener;
    }

    @Override
    public void setDevicesIds(String[] devicesIds) {
        this.devicesIds = devicesIds;
    }


    @Override
    public void loadAd() {
        adView = new AdView(context);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(adsSettings.getAdmob().getBannerId());
        List<String> testDeviceIds = Arrays.asList(devicesIds);
        RequestConfiguration configuration = new RequestConfiguration.Builder()
                .setTestDeviceIds(testDeviceIds).build();
        MobileAds.setRequestConfiguration(configuration);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                listener.onAdsError(loadAdError.getMessage());
            }

            @Override
            public void onAdLoaded() {
                if (listener != null) listener.onAdsLoaded();
            }
        });
    }

    @Override
    public View getAdView() {
        return adView;
    }
}
