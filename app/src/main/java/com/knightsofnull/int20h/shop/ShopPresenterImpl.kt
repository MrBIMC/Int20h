package com.knightsofnull.int20h.shop

/**
 * Created by yarolegovich on 27.02.2016.
 */
class ShopPresenterImpl(var view: ShopView) : ShopPresenter {

    override fun onSearchQuery(query: String) {

    }

    override fun onCategoriesFabClicked() {
    }

    override fun onCategorySelected(position: Int) {
    }

    override fun onItemClicked(position: Int) {
    }

    override fun onItemsListScrolled(direction: ScrollDirection) {
        when (direction) {
            ScrollDirection.UP -> view.collapseCategories()
            ScrollDirection.DOWN -> view.hideCategories()
        }
    }

    override fun onDestroy() {
    }

}