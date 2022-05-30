package br.kleberf65.androidutils.ads.entities;


public class AppLovin {

    private String bannerId;
    private String interstitialId;

    public AppLovin() {
    }

    public AppLovin(String bannerId, String interstitialId) {
        this.bannerId = bannerId;
        this.interstitialId = interstitialId;
    }

    public String getBannerId() {
        return bannerId;
    }

    public String getInterstitialId() {
        return interstitialId;
    }
}
