package com.knightsofnull.int20h.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.view.Menu
import android.view.View
import com.knightsofnull.int20h.R
import com.knightsofnull.int20h.event.OnScrollInChildEvent
import com.knightsofnull.int20h.util.ScrollDirection
import android.view.MenuItem
import com.knightsofnull.int20h.authentication.login.LoginActivity

import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.find
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    var bottomSheetBehavior by Delegates.notNull<BottomSheetBehavior<View>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

        EventBus.getDefault().register(this)
    }

    @Subscribe
    fun onScrollInChildEvent(event: OnScrollInChildEvent) {
        when (event.direction) {
            ScrollDirection.DOWN -> bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            ScrollDirection.UP -> bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    fun setupToolbar() {
        setSupportActionBar(toolbar)
        viewpager.adapter = ViewPagerAdapter(fragmentManager)
        tabs.setupWithViewPager(viewpager)

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
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.account -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
