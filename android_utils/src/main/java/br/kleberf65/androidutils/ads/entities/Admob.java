package br.kleberf65.androidutils.ads.entities;


public class Admob {

    private String bannerId;
    private String interstitialId;

    public Admob() {
    }

    public Admob(String bannerId, String interstitialId) {
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
