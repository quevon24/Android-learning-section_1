package com.example.android_app_test;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by quevon24 on 21/11/2017.
 */

public class Test {

    public static void myToast(Context context, String mensaje) {
        Toast.makeText(context, mensaje, Toast.LENGTH_LONG).show();
    }
}
