package com.knightsofnull.int20h

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tryConnectToDb()
    }


    fun tryConnectToDb() {
        async() {
            DbManager.tryConnect2().let { value ->
                Log.d(localClassName, "here!")
                uiThread {
                    textView.text = value
                    Log.d(localClassName, "here!2")
                }
            }
        }
    }
}
