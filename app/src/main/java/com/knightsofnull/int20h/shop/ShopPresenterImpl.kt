package com.knightsofnull.int20h.shop

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
class ShopPresenterImpl(var view: ShopView?, val typeId: Int,
                        val provider: DataProvider = PromDataProvider()) : ShopPresenter {

    var currentCategory = Category.ALL.catId

    @Subscribe
    fun onSearchQuery(queryEvent: SearchQueryEnteredEvent) {
        showItems(queryEvent.query)
    }

    @Subscribe
    fun onCategorySelected(category: OnCategorySelectedEvent) {
        currentCategory = category.catId
        showItems()
    }

    override fun onItemClicked(position: Int) {
        val item = provider.getItems(typeId, currentCategory)[position]
        view?.navigateToItemPreview(item)
    }

    override fun onItemsListScrolled(direction: ScrollDirection) {
        EventBus.getDefault().post(OnScrollInChildEvent(direction))
    }

    override fun onResume() {
        showItems()
        EventBus.getDefault().register(this)
    }

    override fun onPause() {
        EventBus.getDefault().unregister(this)
    }

    override fun onDestroy() {
        view = null
    }

    private fun showItems(query: String = "") {
        val items = provider.getItems(typeId, currentCategory, query)
        if (!items.isEmpty()) {
            view?.showItems(items)
        } else {
            val requests = provider.getRequestsFor(currentCategory, query)
            if (requests.isEmpty()) {
                view?.showNewRequestForm()
            } else {
                view?.showRequests(requests)
            }
        }
    }


}