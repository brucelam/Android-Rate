package brucelam.android.rate;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;


final class IntentHelper {

    private static final String GOOGLE_PLAY_PACKAGE = "com.android.vending";

    private IntentHelper() {}

    static void openStore(Context context, StoreType storeType) {
        String packageName = context.getPackageName();

        try {
            if (storeType == StoreType.GOOGLEPLAY) {

                // Try Google Play app first
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        UriHelper.getGooglePlayApp(packageName));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);

            } else {
                // Amazon
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        UriHelper.getAmazonAppstore(packageName));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);
            }

        } catch (ActivityNotFoundException e) {

            // Fallback to web (VERY IMPORTANT)
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        UriHelper.getGooglePlayWeb(packageName));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}