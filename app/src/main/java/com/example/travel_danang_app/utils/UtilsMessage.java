package com.example.travel_danang_app.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.LayoutInflater;

import com.example.travel_danang_app.databinding.MessageCustomBinding;

public class UtilsMessage {

    private Context context;
    private Dialog dialog;
    private MessageCustomBinding messageCustomBinding;

    public UtilsMessage(Context context) {
        this.context = context;
    }

    public void showMessage(String title) {
        messageCustomBinding = MessageCustomBinding.inflate(LayoutInflater.from(context));
        dialog = new Dialog(context);
        dialog.setContentView(messageCustomBinding.getRoot());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.show();
        messageCustomBinding.message.setText(title);

        new Handler().postDelayed(() -> {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        }, 800);
    }
}
