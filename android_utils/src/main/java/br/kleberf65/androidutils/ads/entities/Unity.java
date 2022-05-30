package br.kleberf65.androidutils.ads.entities;


public class Unity {

    private String gameId;
    private String bannerId;
    private String interstitialId;

    public Unity() {
    }

    public Unity(String gameId, String bannerId, String interstitialId) {
        this.gameId = gameId;
        this.bannerId = bannerId;
        this.interstitialId = interstitialId;
    }

    public String getGameId() {
        return gameId;
    }

    public String getBannerId() {
        return bannerId;
    }

    public String getInterstitialId() {
        return interstitialId;
    }
}
