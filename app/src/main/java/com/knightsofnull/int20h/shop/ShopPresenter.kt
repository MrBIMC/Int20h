package com.knightsofnull.int20h.shop

/**
 * Created by yarolegovich on 27.02.2016.
 */
interface ShopPresenter {

    fun onSearchQuery(query: String)
    fun onCategoriesFabClicked()
    fun onCategorySelected(position: Int)
    fun onItemClicked(position: Int)
    fun onItemsListScrolled(direction: ScrollDirection)
    fun onDestroy()

}