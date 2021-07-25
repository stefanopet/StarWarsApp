package com.stefano.starwars.ui;

import android.content.Context;
import android.content.DialogInterface;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.stefano.starwars.R;


public class AlertDialogManager {

    public static void showAlertDialog(
            @NonNull Context context,
            @Nullable String title,
            @Nullable String message,
            @Nullable DialogInterface.OnClickListener closeListener){
        final String defaultBtnText = context.getString(R.string.close);
        showAlertDialog(context, title, message, defaultBtnText, closeListener);
    }

    private static void showAlertDialog(
            @NonNull Context context,
            @Nullable String title,
            @Nullable String message,
            @Nullable String button,
            @Nullable DialogInterface.OnClickListener closeListener){

        new AlertDialog.Builder(context)
                .setCancelable(false)
                .setTitle(title)
                .setMessage(message)
                .setNeutralButton(
                        button,
                        closeListener != null ? closeListener : (dialog, which) -> dialog.dismiss())
                .show();
    }
}