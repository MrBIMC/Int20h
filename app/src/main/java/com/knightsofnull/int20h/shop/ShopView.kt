package com.knightsofnull.int20h.shop

import com.knightsofnull.int20h.model.Category
import com.knightsofnull.int20h.model.Item

/**
 * Created by yarolegovich on 27.02.2016.
 */
interface ShopView {
    fun openCategories()
    fun hideCategories()
    fun collapseCategories()
    fun setItems(items: List<Item>)
    fun setCategories(categories: List<Category>)
}