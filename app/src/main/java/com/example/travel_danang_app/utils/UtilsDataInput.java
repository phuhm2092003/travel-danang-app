package com.example.travel_danang_app.utils;

import android.widget.EditText;

public class UtilsDataInput {
    public static final int MAX_LENGTH = 6;

    public static boolean isEmptyData(String... data) {
        for (String str : data) {
            if (str.isEmpty()) return true;
        }
        return false;
    }

    public static boolean isEmailValid(String email) {
        String regexEmail = "^[a-zA-Z\\d._%+-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(regexEmail);
    }

    public static boolean isString(String fullname) {
        String regex = "^\\D+$";
        return fullname.matches(regex);
    }

    public static boolean isPasswordLengthValid(String password) {
        return password.length() >= MAX_LENGTH;
    }

    public static void clearDataEditText(EditText... arrEditText) {
        for (EditText edt : arrEditText) {
            edt.setText(null);
        }
    }
}
