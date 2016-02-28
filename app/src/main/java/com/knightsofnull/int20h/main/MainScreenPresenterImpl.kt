package com.knightsofnull.int20h.main

import android.support.design.widget.BottomSheetBehavior
import com.knightsofnull.int20h.data.DataProvider
import com.knightsofnull.int20h.data.PromDataProvider
import com.knightsofnull.int20h.event.OnCategorySelectedEvent
import com.knightsofnull.int20h.event.OnScrollInChildEvent
import com.knightsofnull.int20h.event.SearchQueryEnteredEvent
import com.knightsofnull.int20h.model.Category
import com.knightsofnull.int20h.util.ScrollDirection
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * Created by yarolegovich on 27.02.2016.
 */
class MainScreenPresenterImpl(
        var view: MainScreenView?,
        val provider: DataProvider = PromDataProvider()) : MainScreenPresenter {

    private var currentCategory = Category.ALL.catId
    private var currentType = 1

    init {
        EventBus.getDefault().register(this)
    }

    @Subscribe
    fun onScrollInChildEvent(event: OnScrollInChildEvent) {
        when (event.direction) {
            ScrollDirection.DOWN -> view?.hideCategories()
            ScrollDirection.UP -> view?.collapseCategories()
        }
    }

    override fun onQueryChanged(query: String) {
        EventBus.getDefault().post(SearchQueryEnteredEvent(query))
    }

    override fun onCategorySelected(position: Int) {
        currentCategory = position
        val currentCatName = provider.getCategories(currentType)[currentCategory].name
        view?.setCurrentCategory(currentCatName)
        EventBus.getDefault().post(OnCategorySelectedEvent(position))
    }

    override fun onResume() {
        view?.let { v ->
            val categories = provider.getCategories(currentType)
            val currentCatName = categories[currentCategory].name
            v.setChips(categories.map { it.name })
            v.setCurrentCategory(currentCatName)
        }
    }

    override fun onPageChanged(pageIndex: Int) {
        currentType = pageIndex + 1
        currentCategory = 0
        view?.let { v ->
            v.clearSearchBar()
            val categories = provider.getCategories(currentType)
            v.setChips(categories.map { it.name })
            v.setCurrentCategory(categories[currentCategory].name)
        }
    }

    override fun onFiltersFabClicked() {
        view?.changeBottomSheetState()
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        view = null
    }

}