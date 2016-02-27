package com.knightsofnull.int20h.shop

import android.app.Fragment
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.design.widget.BottomSheetBehavior
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import com.knightsofnull.int20h.R
import com.knightsofnull.int20h.data.MockStorage
import com.knightsofnull.int20h.model.Category
import com.knightsofnull.int20h.model.Item

import kotlinx.android.synthetic.main.fragment_shop.*
import kotlin.properties.Delegates

/**
 * Created by yarolegovich on 27.02.2016.
 */
class ShopFragment : Fragment(), ShopView {

    val presenter = ShopPresenterImpl(this)

    var bottomSheetBehaviour by Delegates.notNull<BottomSheetBehavior<View>>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomSheetBehaviour = BottomSheetBehavior.from(bottomSheet)

        list.layoutManager = LinearLayoutManager(activity)
        list.adapter = ShopAdapter(MockStorage().getItems()) { }
        list.addOnScrollListener(RecyclerScrollListener(presenter))
    }

    override fun openCategories() {
        bottomSheetBehaviour.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun hideCategories() {
        bottomSheetBehaviour.state = BottomSheetBehavior.STATE_HIDDEN
    }

    override fun setItems(items: List<Item>) {

    }

    override fun setCategories(categories: List<Category>) {

    }

    override fun collapseCategories() {
        bottomSheetBehaviour.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    class RecyclerScrollListener(val presenter: ShopPresenter) : RecyclerView.OnScrollListener() {

        private var mPreviousScroll: Int = 0
        private var mCurrentScroll: Int = 0

        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            mCurrentScroll += dy

            if (mCurrentScroll > mPreviousScroll) {
                presenter.onItemsListScrolled(ScrollDirection.DOWN)
            } else if (mCurrentScroll < mPreviousScroll) {
                presenter.onItemsListScrolled(ScrollDirection.UP)
            }
            mPreviousScroll = mCurrentScroll
        }
    }
}