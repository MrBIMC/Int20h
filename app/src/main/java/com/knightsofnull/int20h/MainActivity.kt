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

        (0..tabs.tabCount - 1).map {
            when(it) {
                0 -> it to R.drawable.ic_factory_white_48dp
                1 -> it to R.drawable.ic_tshirt_v_white_48dp
                2 -> it to R.drawable.ic_account_star_white_48dp
                else -> it to R.mipmap.ic_launcher
            }
        }
        .forEach { tabs.getTabAt(it.first)?.icon = resources.getDrawable(it.second, theme) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }
}
