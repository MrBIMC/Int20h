package com.knightsofnull.int20h.main

/**
 * Created by yarolegovich on 27.02.2016.
 */
interface MainScreenPresenter {

    fun onPageChanged(pageIndex: Int)
    fun onCategorySelected(position: Int)
    fun onQueryChanged(query: String)

    fun onFiltersFabClicked()

    fun onResume()
    fun onDestroy()
}