package com.knightsofnull.int20h.util

import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
import android.support.v7.widget.AppCompatButton
import android.util.Log
import android.widget.Button

/**
 * Created by yarolegovich on 27.02.2016.
 */

val LOG_ON = true

fun Any.logD(mes: String) {
    if (LOG_ON) {
        Log.d(javaClass.simpleName, mes)
    }
}

//public static void setButtonTint(Button button, ColorStateList tint) {
//    if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP && button instanceof AppCompatButton) {
//        ((AppCompatButton) button).setSupportBackgroundTintList(tint);
//    } else {
//        ViewCompat.setBackgroundTintList(button, tint);
//    }
//}