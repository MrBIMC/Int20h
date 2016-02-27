package com.knightsofnull.int20h

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()
    }

    fun setupToolbar() {
        setSupportActionBar(toolbar)
        viewpager.adapter = ViewPagerAdapter(fragmentManager)
        tabs.setupWithViewPager(viewpager)
        tabs.requestFocus()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }
}
