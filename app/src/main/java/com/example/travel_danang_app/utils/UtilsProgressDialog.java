package com.example.travel_danang_app.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class UtilsProgressDialog {
    public static final String DIALOG_MESSAGE = "Vui lòng chờ...";
    private Context context;
    private ProgressDialog progressDialog;

    public UtilsProgressDialog(Context context) {
        this.context = context;
    }

    public void showLoadingDialog() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(DIALOG_MESSAGE);
        progressDialog.show();
    }

    public void hideLoadingDialog() {
        if (progressDialog != null || progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
