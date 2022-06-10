package br.kleberf65;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.kleberf65.androidutils.ads.banner.AdsBannerView;
import br.kleberf65.androidutils.ads.entities.Admob;
import br.kleberf65.androidutils.ads.entities.AdsSettings;
import br.kleberf65.androidutils.ads.entities.AppLovin;
import br.kleberf65.androidutils.ads.entities.Appodeal;
import br.kleberf65.androidutils.ads.entities.Unity;
import br.kleberf65.androidutils.ads.intertitial.AdsInterstitial;
import br.kleberf65.androidutils.ads.intertitial.AdsInterstitialListener;
import br.kleberf65.androidutils.ads.intertitial.InterstitialAdsFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AdsBannerView bannerView;
    private AdsInterstitial interstitial;
    private AdsSettings admobSettings, unitySettings, lovinSettings, appodealSettings;

    private final AdsInterstitialListener listener = new AdsInterstitialListener() {
        @Override
        public void onAdsLoaded(boolean initialLoading) {
            Toast.makeText(MainActivity.this, "Loaded", Toast.LENGTH_SHORT).show();

            interstitial.showAds();
        }

        @Override
        public void onAdsDismissed() {
            Toast.makeText(MainActivity.this, "Finished", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAdsError(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Admob admob = new Admob("ca-app-pub-3940256099942544/6300978111", "ca-app-pub-3940256099942544/1033173712");

        admobSettings = new AdsSettings(admob, true);

        Unity unity = new Unity("4405657", "Banner_Android", "Interstitial_Android");
        unitySettings = new AdsSettings(unity, true);

        AppLovin appLovin = new AppLovin("1da5411f90f52228", "bf9fdd6b1659c2d0");
        lovinSettings = new AdsSettings(appLovin, true);

        Appodeal appodeal = new Appodeal("");
        appodealSettings = new AdsSettings(appodeal, true);

        bannerView = findViewById(R.id.banner_view);
        bannerView.setAdsBannerListener(new AdsBannerView.AdsBannerListener() {
            @Override
            public void onAdsLoaded() {
                Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdsError(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadInterstitial(AdsSettings settings) {
        interstitial = InterstitialAdsFactory.build(this, settings);
        interstitial.setOnLoadListener(listener);
        interstitial.loadAds();
    }

    @Override
    @SuppressLint("NonConstantResourceId")
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.banner_admob:
                bannerView.loadAds(this, admobSettings);
                break;
            case R.id.inter_admob:
                loadInterstitial(admobSettings);
                break;

            case R.id.banner_unity:
                bannerView.loadAds(this, unitySettings);
                break;
            case R.id.inter_unity:
                loadInterstitial(unitySettings);
                break;

            case R.id.banner_lovin:
                bannerView.loadAds(this, lovinSettings);
                break;
            case R.id.inter_lovin:
                loadInterstitial(lovinSettings);
                break;

            case R.id.banner_appodeal:
                bannerView.loadAds(this, appodealSettings);
                break;
            case R.id.inter_appodeal:
                loadInterstitial(appodealSettings);
                break;

        }
    }
}