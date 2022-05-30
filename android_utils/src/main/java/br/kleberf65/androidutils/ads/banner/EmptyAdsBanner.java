package br.kleberf65.androidutils.ads.banner;

import android.content.Context;
import android.view.View;

public final class EmptyAdsBanner implements AdsBannerView.AdsBanner {

    private final Context context;

    public EmptyAdsBanner(Context context) {
        this.context = context;
    }

    @Override
    public void loadAd() {

    }

    @Override
    public View getAdView() {
        return new View(context);
    }

}
