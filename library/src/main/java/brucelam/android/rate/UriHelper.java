package brucelam.android.rate;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.util.List;

final class UriHelper {

    private static final String GOOGLE_PLAY_APP = "market://details?id=";
    private static final String GOOGLE_PLAY_WEB = "https://play.google.com/store/apps/details?id=";
    private static final String AMAZON_APPSTORE = "amzn://apps/android?p=";

    private UriHelper() {}

    static Uri getGooglePlayApp(String packageName) {
        return packageName == null ? null : Uri.parse(GOOGLE_PLAY_APP + packageName);
    }

    static Uri getGooglePlayWeb(String packageName) {
        return packageName == null ? null : Uri.parse(GOOGLE_PLAY_WEB + packageName);
    }

    static Uri getAmazonAppstore(String packageName) {
        return packageName == null ? null : Uri.parse(AMAZON_APPSTORE + packageName);
    }
}