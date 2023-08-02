package com.example.travel_danang_app.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class UtilsProgressDialog {
    private Context context;
    private ProgressDialog progressDialog;

    public UtilsProgressDialog(Context context) {
        this.context = context;
    }

    public void showLoadingDialog(String title) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(title);
        progressDialog.show();
    }

    public void hideLoadingDialog() {
        if (progressDialog != null || progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
