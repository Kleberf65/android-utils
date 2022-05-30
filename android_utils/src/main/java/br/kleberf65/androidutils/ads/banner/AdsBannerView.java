package br.kleberf65.androidutils.ads.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import br.kleberf65.androidutils.ads.entities.AdsSettings;

public class AdsBannerView extends LinearLayout {

    private AdsBannerListener listener;

    public AdsBannerView(Context context) {
        super(context);
    }

    public AdsBannerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AdsBannerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void loadAds(Context context, AdsSettings adsSettings) {
        this.removeAllViews();
        AdsBanner adsBanner = BannerAdsFactory.build(context, adsSettings, listener);
        adsBanner.loadAd();
        this.addView(adsBanner.getAdView());
    }

    public void setAdsBannerListener(AdsBannerListener listener) {
        this.listener = listener;
    }

    interface LoadSuccess {
        void onSuccess();
    }

    public interface AdsBanner {

        void loadAd();

        View getAdView();

        default void setDevicesIds(String[] devicesIds) {

        }
    }

    public interface AdsBannerListener {

        default void onAdsLoaded() {

        }

        default void onAdsError(String message) {

        }
    }
}
