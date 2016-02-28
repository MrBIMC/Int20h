package com.knightsofnull.int20h.util

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
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

fun blendColors(color1: Int, color2: Int, ratio: Float): Int {
    val inverseRation = 1f - ratio
    val r = Color.red(color1) * ratio + Color.red(color2) * inverseRation
    val g = Color.green(color1) * ratio + Color.green(color2) * inverseRation
    val b = Color.blue(color1) * ratio + Color.blue(color2) * inverseRation
    return Color.rgb(r.toInt(), g.toInt(), b.toInt())
}

fun darkenColor(color: Int): Int {
    val hsv = FloatArray(3)
    Color.colorToHSV(color, hsv)
    hsv[2] *= 0.8f
    return Color.HSVToColor(hsv)
}

fun colorStatusBar(activity: AppCompatActivity, color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        activity.window.statusBarColor = color
    }
}

//public static void setButtonTint(Button button, ColorStateList tint) {
//    if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP && button instanceof AppCompatButton) {
//        ((AppCompatButton) button).setSupportBackgroundTintList(tint);
//    } else {
//        ViewCompat.setBackgroundTintList(button, tint);
//    }
//}