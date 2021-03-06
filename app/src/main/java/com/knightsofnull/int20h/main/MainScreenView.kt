package com.knightsofnull.int20h.main

/**
 * Created by yarolegovich on 27.02.2016.
 */
interface MainScreenView {

    fun clearSearchBar()

    fun hideCategories()
    fun collapseCategories()
    fun expandCategories()

    fun changeBottomSheetState()

    fun setCurrentCategory(category: String)

    fun setChips(chips: List<String>)
}