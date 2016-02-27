package com.knightsofnull.int20h.shop

import com.knightsofnull.int20h.data.DataProvider
import com.knightsofnull.int20h.data.PromDataProvider
import com.knightsofnull.int20h.event.OnCategorySelectedEvent
import com.knightsofnull.int20h.event.OnScrollInChildEvent
import com.knightsofnull.int20h.event.SearchQueryEnteredEvent
import com.knightsofnull.int20h.model.Category
import com.knightsofnull.int20h.util.ScrollDirection
import com.knightsofnull.int20h.util.logD
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * Created by yarolegovich on 27.02.2016.
 */
class ShopPresenterImpl(var view: ShopView?, val typeId: Int,
                        val provider: DataProvider = PromDataProvider()) : ShopPresenter {

    init {
        EventBus.getDefault().register(this)
    }

    @Subscribe
    fun onSearchQuery(query: SearchQueryEnteredEvent) {

    }


    @Subscribe
    fun onCategorySelected(category: OnCategorySelectedEvent) {

    }

    override fun onItemClicked(position: Int) {

    }

    override fun onItemsListScrolled(direction: ScrollDirection) {
        EventBus.getDefault().post(OnScrollInChildEvent(direction))
    }

    override fun onResume() {
        val items = provider.getItems(typeId, Category.ALL.catId)
        view?.showItems(items)
    }

    override fun onDestroy() {
        view = null
        EventBus.getDefault().unregister(this)
    }

}