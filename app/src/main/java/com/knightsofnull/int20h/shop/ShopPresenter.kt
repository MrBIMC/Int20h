package com.knightsofnull.int20h.shop

import com.knightsofnull.int20h.util.ScrollDirection

/**
 * Created by yarolegovich on 27.02.2016.
 */
interface ShopPresenter {

    fun onItemClicked(position: Int)
    fun onItemsListScrolled(direction: ScrollDirection)

    fun onDestroy()
    fun onResume()
    fun onPause()
}