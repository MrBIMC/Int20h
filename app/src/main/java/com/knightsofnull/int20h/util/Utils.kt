package com.knightsofnull.int20h.util

import android.util.Log

/**
 * Created by yarolegovich on 27.02.2016.
 */

val LOG_ON = true

fun Any.logD(mes: String) {
    if (LOG_ON) {
        Log.d(javaClass.simpleName, mes)
    }
}