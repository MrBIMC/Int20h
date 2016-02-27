package com.knightsofnull.int20h.main

import android.app.Fragment
import android.app.FragmentManager
import android.support.v13.app.FragmentPagerAdapter
import com.knightsofnull.int20h.data.DataProvider
import com.knightsofnull.int20h.data.PromDataProvider
import com.knightsofnull.int20h.shop.ShopFragment
import java.util.*

/**
 * Created by mrbimc on 27.02.16.
 */
class ViewPagerAdapter(fm: FragmentManager, dataProvider: DataProvider = PromDataProvider()) : FragmentPagerAdapter(fm) {

    private val types = dataProvider.getTypes()

    override fun getItem(position: Int) = when (position) {
        0 -> ShopFragment.create(types[position].id)
        1 -> ShopFragment.create(types[position].id)
        2 -> ShopFragment.create(types[position].id)
        else -> throw RuntimeException("Page not supported")
    }

    override fun getCount() = 3

    override fun getPageTitle(position: Int): CharSequence? {
        val l = ""
        when (position) {
            0 -> return l
            1 -> return l
            2 -> return l
        }
        return null
    }

}