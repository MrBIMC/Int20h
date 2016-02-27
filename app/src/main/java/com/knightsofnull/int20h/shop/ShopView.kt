package com.knightsofnull.int20h.shop

import com.knightsofnull.int20h.model.Category
import com.knightsofnull.int20h.model.Item
import com.knightsofnull.int20h.model.Request

/**
 * Created by yarolegovich on 27.02.2016.
 */
interface ShopView {
    fun showItems(items: List<Item>)
    fun showRequests(requests: List<Request>)
    fun showNewRequestForm()

}