package com.knightsofnull.int20h.shop

import android.app.Fragment
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.design.widget.BottomSheetBehavior
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    var bottomSheetBehaviour by Delegates.notNull<BottomSheetBehavior<View>>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomSheetBehaviour = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehaviour.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(p0: View, p1: Float) {
                bottomSheetBehaviour.state = BottomSheetBehavior.STATE_COLLAPSED
            }

            override fun onStateChanged(p0: View, p1: Int) {

            }
        })

        list.layoutManager = LinearLayoutManager(activity)
        list.adapter = ShopAdapter(MockStorage().getItems()) { position ->
            bottomSheetBehaviour.peekHeight = bottomSheet.height
            bottomSheet.requestLayout()
        }

    }

    override fun openCategories() {

    }

    override fun hideCategories() {

    }

    override fun setItems(items: List<Item>) {

    }

    override fun setCategories(categories: List<Category>) {

    }
}