package br.kleberf65.androidutils.ads.entities;


import br.kleberf65.androidutils.ads.enums.AdsType;

public class AdsSettings {

    private AdsType adsType;
    private String[] devicesIds = new String[]{};

    private Admob admob;
    private Unity unity;
    private AppLovin appLovin;
    private Appodeal appodeal;

    private boolean debugMode;

    public AdsSettings() {
    }

    public AdsSettings(Admob admob, boolean debugMode) {
        this.adsType = AdsType.ADMOB;
        this.admob = admob;
        this.debugMode = debugMode;
    }

    public AdsSettings(Unity unity, boolean debugMode) {
        this.adsType = AdsType.UNITY;
        this.unity = unity;
        this.debugMode = debugMode;
    }

    public AdsSettings(Appodeal appodeal, boolean debugMode) {
        this.adsType = AdsType.APPODEAL;
        this.appodeal = appodeal;
        this.debugMode = debugMode;
    }

    public AdsSettings(AppLovin appLovin, boolean debugMode) {
        this.adsType = AdsType.APP_LOVIN;
        this.appLovin = appLovin;
        this.debugMode = debugMode;
    }

    public AdsSettings(AdsType adsType, String[] devicesIds, Admob admob, Unity unity, AppLovin appLovin, boolean debugMode) {
        this.adsType = adsType;
        this.devicesIds = devicesIds;
        this.admob = admob;
        this.unity = unity;
        this.appLovin = appLovin;
        this.debugMode = debugMode;
    }

    public void setDevicesIds(String[] devicesIds) {
        this.devicesIds = devicesIds;
    }

    public AdsType getAdsType() {
        return adsType;
    }

    public void setAdsType(AdsType adsType) {
        this.adsType = adsType;
    }

    public String[] getDevicesIds() {
        return devicesIds;
    }

    public Admob getAdmob() {
        return admob;
    }

    public Unity getUnity() {
        return unity;
    }

    public AppLovin getAppLovin() {
        return appLovin;
    }

    public Appodeal getAppodeal() {
        return appodeal;
    }

    public boolean isDebugMode() {
        return debugMode;
    }
}
