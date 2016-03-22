package com.example.john.barcode.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by JKH on 2016-03-23.
 */
public class ProgressUtils {
    public static ProgressDialog showProgress(Context context, String title, String message)
    {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        if(progressDialog.isShowing()){
            return progressDialog;
        }
        progressDialog.show();
        return progressDialog;
    }
}
