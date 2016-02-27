package com.knightsofnull.int20h.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.view.ViewPager
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import com.knightsofnull.int20h.R
import com.knightsofnull.int20h.authentication.login.LoginActivity
import com.knightsofnull.int20h.event.OnScrollInChildEvent
import com.knightsofnull.int20h.event.SearchQueryEnteredEvent
import com.knightsofnull.int20h.util.ScrollDirection

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_bottom_sheet.*
import kotlinx.android.synthetic.main.view_searchbar.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.find
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), TextWatcher, MainScreenView, ViewPager.OnPageChangeListener {

    var presenter by Delegates.notNull<MainScreenPresenter>()
    var bottomSheetBehavior by Delegates.notNull<BottomSheetBehavior<View>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainScreenPresenterImpl(this)

        setupToolbar()
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        searchBar.addTextChangedListener(this)
        viewpager.addOnPageChangeListener(this)
    }

    override fun collapseCategories() {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    override fun expandCategories() {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun clearSearchBar() {
        searchBar.setText("")
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
        presenter.onDestroy()
    }

    fun setupToolbar() {
        setSupportActionBar(toolbar)
        viewpager.adapter = ViewPagerAdapter(fragmentManager)
        tabs.setupWithViewPager(viewpager)

        (0..tabs.tabCount - 1).map {
            when (it) {
                0 -> it to R.drawable.ic_factory_white_48dp
                1 -> it to R.drawable.ic_tshirt_v_white_48dp
                2 -> it to R.drawable.ic_account_star_white_48dp
                else -> it to R.mipmap.ic_launcher
            }
        }
        .forEach { tabs.getTabAt(it.first)?.apply {
            if(android.os.Build.VERSION.SDK_INT >= 21){
                icon = resources.getDrawable(it.second, theme)
            } else {
                icon = resources.getDrawable(it.second)
            }

        } }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.account -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        return false
    }

    override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
        presenter.onQueryChanged(text.toString())
    }

    override fun beforeTextChanged(text: CharSequence, p1: Int, p2: Int, p3: Int) {
    }

    override fun onPageScrollStateChanged(p0: Int) {
    }

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
    }

    override fun afterTextChanged(editable: Editable) {
    }

    override fun hideCategories() {
    }

    override fun onPageSelected(p0: Int) {
    }
}
