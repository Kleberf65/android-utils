package br.kleberf65.androidutils.ads.intertitial;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.Arrays;
import java.util.List;

import br.kleberf65.androidutils.ads.entities.AdsSettings;

public class AdmobAdsInterstitial implements AdsInterstitial {

    private static AdsInterstitial adsInterstitial;
    private final AdsSettings adsSettings;
    private final Context context;
    private AdsInterstitialListener adsInterstitialListener;
    private InterstitialAd mInterstitialAd;
    private FullScreenContentCallback fullScreenContentCallback;
    private boolean initialLoading = true;

    public AdmobAdsInterstitial(Context context, AdsSettings adsSettings) {
        this.context = context;
        this.adsSettings = adsSettings;
    }

    public static AdsInterstitial getInstance(Context context, AdsSettings adsSettings) {
        if (adsInterstitial == null) {
            adsInterstitial = new AdmobAdsInterstitial(context, adsSettings);
        }
        return adsInterstitial;
    }

    @Override
    public void setOnLoadListener(AdsInterstitialListener adsInterstitialListener) {
        this.adsInterstitialListener = adsInterstitialListener;
    }

    @Override
    public void loadAds() {
        List<String> testDeviceIds = Arrays.asList(adsSettings.getDevicesIds());
        RequestConfiguration configuration = new RequestConfiguration.Builder()
                .setTestDeviceIds(testDeviceIds).build();
        MobileAds.setRequestConfiguration(configuration);

        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(context, adsSettings.getAdmob().getInterstitialId(), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                        if (existsListener()) adsInterstitialListener.onAdsLoaded(initialLoading);
                        initialLoading = false;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mInterstitialAd = null;
                        if (existsListener())
                            adsInterstitialListener.onAdsError(loadAdError.getMessage());
                    }
                });

    }

    @Override
    public void showAds() {
        if (fullScreenContentCallback == null) {
            fullScreenContentCallback = new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    if (existsListener()) adsInterstitialListener.onAdsDismissed();
                    loadAds();
                }

                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    if (existsListener()) adsInterstitialListener.onAdsError(adError.getMessage());
                    loadAds();
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    mInterstitialAd = null;
                }
            };
        }
        if (mInterstitialAd != null) {
            mInterstitialAd.setFullScreenContentCallback(fullScreenContentCallback);
            mInterstitialAd.show((Activity) context);
        } else {
            if (existsListener()) adsInterstitialListener.onAdsDismissed();
        }
    }

    private boolean existsListener() {
        return adsInterstitialListener != null;
    }
}
