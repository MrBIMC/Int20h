package com.knightsofnull.int20h.shop

import com.knightsofnull.int20h.data.DataProvider
import com.knightsofnull.int20h.data.PromDataProvider
import com.knightsofnull.int20h.event.OnCategorySelectedEvent
import com.knightsofnull.int20h.event.OnScrollInChildEvent
import com.knightsofnull.int20h.event.SearchQueryEnteredEvent
import com.knightsofnull.int20h.model.Category
import com.knightsofnull.int20h.model.Request
import com.knightsofnull.int20h.util.ScrollDirection
import com.knightsofnull.int20h.util.logD
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * Created by yarolegovich on 27.02.2016.
 */
class ShopPresenterImpl(var view: ShopView?, val typeId: Int,
                        val provider: DataProvider = PromDataProvider()) : ShopPresenter {

    var currentCategory = 0

    @Subscribe
    fun onSearchQuery(queryEvent: SearchQueryEnteredEvent) {
        showItems(queryEvent.query)
    }

    @Subscribe
    fun onCategorySelected(category: OnCategorySelectedEvent) {
        if (category.receiverType == typeId) {
            currentCategory = category.catId
            showItems()
        }
    }

    override fun onItemClicked(position: Int) {
        val catId = provider.getCategories(typeId)[currentCategory].catId
        val item = provider.getItems(typeId, catId)[position]
        view?.navigateToItemPreview(item)
    }

    override fun onItemsListScrolled(direction: ScrollDirection) {
        EventBus.getDefault().post(OnScrollInChildEvent(direction))
    }

    override fun onRequestCreated(request: Request) {
        provider.requestItem(request)
    }

    override fun onRequestButtonClicked() {
        view?.showRequestDialog()
    }

    override fun onResume() {
        currentCategory = 0
        EventBus.getDefault().register(this)
        showItems()
    }

    override fun onPause() {
        EventBus.getDefault().unregister(this)
    }

    override fun onDestroy() {
        view = null
    }

    private fun showItems(query: String = "") {
        val catId = provider.getCategories(typeId)[currentCategory].catId
        val items = provider.getItems(typeId, catId, query)
        if (!items.isEmpty()) {
            view?.showItems(items)
        } else {
            val requests = provider.getRequestsFor(catId, query)
            if (requests.isEmpty()) {
                view?.showNewRequestForm()
            } else {
                view?.showRequests(requests)
            }
        }
    }


}