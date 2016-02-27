package com.knightsofnull.int20h.main

import android.support.design.widget.BottomSheetBehavior
import com.knightsofnull.int20h.event.OnScrollInChildEvent
import com.knightsofnull.int20h.event.SearchQueryEnteredEvent
import com.knightsofnull.int20h.util.ScrollDirection
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * Created by yarolegovich on 27.02.2016.
 */
class MainScreenPresenterImpl(var view: MainScreenView?) : MainScreenPresenter {

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

    }

    override fun onPageChanged() {
        view?.collapseCategories()
        view?.clearSearchBar()
    }

    override fun onDestroy() {
        view = null
        EventBus.getDefault().unregister(this)
    }

}