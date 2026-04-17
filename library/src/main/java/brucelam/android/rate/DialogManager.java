package brucelam.android.rate;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;


import static brucelam.android.rate.PreferenceHelper.setAgreeShowDialog;
import static brucelam.android.rate.PreferenceHelper.setRemindInterval;

final class DialogManager {

    private DialogManager() {}

    static Dialog create(final Context context, final DialogOptions options) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage(options.getMessageText(context));

        if (options.shouldShowTitle()) {
            builder.setTitle(options.getTitleText(context));
        }

        builder.setCancelable(options.getCancelable());

        if (options.getView() != null) {
            builder.setView(options.getView());
        }

        final OnClickButtonListener listener = options.getListener();

        // POSITIVE (Rate now)
        builder.setPositiveButton(options.getPositiveText(context), (dialog, which) -> {

            IntentHelper.openStore(context, options.getStoreType());

            setAgreeShowDialog(context, false);

            if (listener != null) {
                listener.onClickButton(which);
            }
        });

        // NEUTRAL (Remind later)
        if (options.shouldShowNeutralButton()) {
            builder.setNeutralButton(options.getNeutralText(context), (dialog, which) -> {

                setRemindInterval(context);

                if (listener != null) {
                    listener.onClickButton(which);
                }
            });
        }

        // NEGATIVE (No thanks)
        if (options.shouldShowNegativeButton()) {
            builder.setNegativeButton(options.getNegativeText(context), (dialog, which) -> {

                setAgreeShowDialog(context, false);

                if (listener != null) {
                    listener.onClickButton(which);
                }
            });
        }

        return builder.create();
    }
}