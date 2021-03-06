package com.knightsofnull.int20h.shop

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.knightsofnull.int20h.R
import com.knightsofnull.int20h.itempage.ItemPageActivity
import com.knightsofnull.int20h.main.MainActivity
import com.knightsofnull.int20h.model.Item
import com.knightsofnull.int20h.model.Request
import com.knightsofnull.int20h.util.ScrollDirection
import com.knightsofnull.int20h.util.logD

import kotlinx.android.synthetic.main.fragment_shop.*
import kotlin.properties.Delegates

/**
 * Created by yarolegovich on 27.02.2016.
 */
class ShopFragment : Fragment(), ShopView {

    var presenter by Delegates.notNull<ShopPresenter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = ShopPresenterImpl(this, arguments.getInt(TYPE))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list.layoutManager = LinearLayoutManager(activity)
        list.adapter = ShopAdapter(listOf()) { position -> presenter.onItemClicked(position) }
        list.addOnScrollListener(RecyclerScrollListener(presenter))

        createRequestButton.setOnClickListener { presenter.onRequestButtonClicked() }
    }

    override fun showItems(items: List<Item>) {
        addRequestContainer.visibility = View.GONE
        list.visibility = View.VISIBLE
        val adapter = list.adapter as ShopAdapter
        adapter.type = ShopAdapter.TYPE_ITEM
        adapter.replaceData(items)
    }

    override fun showRequests(requests: List<Request>) {
        addRequestContainer.visibility = View.GONE
        list.visibility = View.VISIBLE
        val adapter = list.adapter as ShopAdapter
        adapter.type = ShopAdapter.TYPE_REQUEST
        adapter.replaceData(requests)
    }

    override fun showNewRequestForm() {
        list.visibility = View.GONE
        addRequestContainer.visibility = View.VISIBLE
    }

    override fun showRequestDialog() {
        RequestDialog(activity).onAcceptButtonClicked { request ->
            presenter.onRequestCreated(request)
        }.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun navigateToItemPreview(item: Item) {
        logD("item = $item")
        val intent = Intent(activity, ItemPageActivity::class.java)
        if (activity is MainActivity) {
            intent.putExtra(ItemPageActivity.KEY_COLOR, (activity as MainActivity).getPageColor())
        }
        intent.putExtra(ItemPageActivity.KEY_ITEM, item)
        startActivity(intent)
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

    companion object {
        val TYPE = "arg.type"
        fun create(typeId: Int): ShopFragment {
            val args = Bundle()
            args.putInt(TYPE, typeId)
            val fragment = ShopFragment()
            fragment.arguments = args
            return fragment
        }
    }
}