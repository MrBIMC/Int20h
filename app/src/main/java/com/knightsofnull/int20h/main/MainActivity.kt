package com.knightsofnull.int20h.main

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import com.knightsofnull.int20h.R
import com.knightsofnull.int20h.authentication.login.LoginActivity
import com.knightsofnull.int20h.util.AnimationEndListener
import com.knightsofnull.int20h.util.logD
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_bottom_sheet.*
import kotlinx.android.synthetic.main.view_searchbar.*
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
        filtersFab.setOnClickListener { presenter.onFiltersFabClicked() }
        chipsView.setOnChipClickListener { presenter.onCategorySelected(it) }
    }

    override fun collapseCategories() {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        arrowToFilter()
    }

    override fun expandCategories() {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        filterToArrow()
    }

    private fun filterToArrow() {
        filtersFab.animate().rotation(180f).setDuration(500).setListener(AnimationEndListener {
            filtersFab.setImageResource(R.drawable.ic_arrow_upward_white_24dp)
        }).start()
    }

    private fun arrowToFilter() {
        filtersFab.animate().rotation(0f).setDuration(500).setListener(AnimationEndListener {
            filtersFab.setImageResource(R.drawable.ic_filter_variant_white_48dp)
        }).start()
    }

    override fun clearSearchBar() {
        searchBar.setText("")
    }

    override fun changeBottomSheetState() {
        val hidden = bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED
        if (hidden) expandCategories() else collapseCategories()
    }

    override fun setCurrentCategory(category: String) {
        currentCategory.text = getString(R.string.category, category)
    }

    override fun setChips(chips: List<String>) {
        chipsView.setChips(chips)
    }


    override fun hideCategories() {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
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
        }.forEach {
            tabs.getTabAt(it.first)?.apply {
                if (android.os.Build.VERSION.SDK_INT >= 21) {
                    icon = resources.getDrawable(it.second, theme)
                } else {
                    icon = resources.getDrawable(it.second)
                }
            }
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.main, menu)
//        return true
//    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.account -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        return false
    }

    override fun onPageSelected(position: Int) {
        presenter.onPageChanged(position)
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
}
