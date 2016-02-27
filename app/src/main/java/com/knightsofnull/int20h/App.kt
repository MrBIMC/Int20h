package com.knightsofnull.int20h

import android.app.Application
import kotlin.properties.Delegates

/**
 * Created by yarolegovich on 27.02.2016.
 */

class App : Application() {

    companion object {
        var instance: App by Delegates.notNull<App>()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}