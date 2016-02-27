package com.knightsofnull.int20h

import android.app.Fragment
import android.app.FragmentManager
import android.support.v13.app.FragmentPagerAdapter
import com.knightsofnull.int20h.shop.ShopFragment
import java.util.*

/**
 * Created by mrbimc on 27.02.16.
 */
class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> return ShopFragment()
            1 -> return DemoFragment()
            2 -> return DemoFragment()
        }
        throw Exception("Critical Error!")
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