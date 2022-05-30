package br.kleberf65.androidutils.ads.intertitial;

import android.app.Activity;
import android.content.Context;

import com.unity3d.ads.IUnityAdsInitializationListener;
import com.unity3d.ads.IUnityAdsLoadListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;

import br.kleberf65.androidutils.ads.entities.AdsSettings;

public class UnityAdsInterstitial implements AdsInterstitial {

    private static AdsInterstitial adsInterstitial;
    private final Context context;
    private final AdsSettings adsSettings;
    private AdsInterstitialListener adsInterstitialListener;
    private IUnityAdsShowListener iUnityAdsShowListener;
    private boolean initialLoading = true;

    private final IUnityAdsInitializationListener listener = new IUnityAdsInitializationListener() {
        @Override
        public void onInitializationComplete() {
            UnityAds.load(adsSettings.getUnity().getInterstitialId(), new IUnityAdsLoadListener() {
                @Override
                public void onUnityAdsAdLoaded(String placementId) {
                    if (existsListener())
                        adsInterstitialListener.onAdsLoaded(initialLoading);
                    initialLoading = false;
                }

                @Override
                public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {
                    if (existsListener())
                        adsInterstitialListener.onAdsError(message);
                }
            });
        }

        @Override
        public void onInitializationFailed(UnityAds.UnityAdsInitializationError error, String message) {
            if (existsListener()) adsInterstitialListener.onAdsError(message);
        }
    };

    public UnityAdsInterstitial(Context context, AdsSettings adsSettings) {
        this.context = context;
        this.adsSettings = adsSettings;
    }

    public static AdsInterstitial getInstance(Context context, AdsSettings adsSettings) {
        if (adsInterstitial == null) {
            adsInterstitial = new UnityAdsInterstitial(context, adsSettings);
        }
        return adsInterstitial;
    }

    @Override
    public void loadAds() {
        if (!UnityAds.isInitialized()) {
            UnityAds.initialize(context, adsSettings.getUnity().getGameId(), adsSettings.isDebugMode(), listener);
        } else if (adsInterstitialListener != null)
            adsInterstitialListener.onAdsLoaded(initialLoading);

    }

    @Override
    public void showAds() {
        if (iUnityAdsShowListener == null) {
            iUnityAdsShowListener = new IUnityAdsShowListener() {
                @Override
                public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {
                    if (existsListener()) adsInterstitialListener.onAdsError(message);

                }

                @Override
                public void onUnityAdsShowStart(String placementId) {

                }

                @Override
                public void onUnityAdsShowClick(String placementId) {
                }

                @Override
                public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {
                    if (existsListener()) {
                        adsInterstitialListener.onAdsDismissed();
                        adsInterstitialListener.onAdsLoaded(initialLoading);
                    }

                }
            };
        }
        UnityAds.show((Activity) context, adsSettings.getUnity().getInterstitialId(),
                iUnityAdsShowListener);
    }

    @Override
    public void setOnLoadListener(AdsInterstitialListener adsInterstitialListener) {
        this.adsInterstitialListener = adsInterstitialListener;
    }

    private boolean existsListener() {
        return adsInterstitialListener != null;
    }

}
