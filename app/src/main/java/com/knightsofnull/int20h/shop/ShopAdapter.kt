package com.knightsofnull.int20h.shop

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.knightsofnull.int20h.R
import com.knightsofnull.int20h.model.Item
import com.knightsofnull.int20h.model.Request
import com.knightsofnull.int20h.model.ShopModel
import org.jetbrains.anko.find
import java.util.*

/**
 * Created by yarolegovich on 27.02.2016.
 */
class ShopAdapter(items: List<ShopModel>, val onItemClick: (Int) -> Unit) :
        RecyclerView.Adapter<ShopAdapter.ShopViewHolder>() {

    var type: Int = TYPE_ITEM

    private val data = ArrayList(items)

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): ShopViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(getLayout(), parent, false)
        return if (type == TYPE_ITEM) {
            ItemHolder(view, onItemClick)
        } else RequestHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        if (type == TYPE_ITEM && holder is ItemHolder) {
            val item = data[position] as Item

            holder.name.text = item.name
            holder.rating.text = "Rating: ${item.itemRating}"
            Glide.with(holder.itemView.context).load(item.itemImage).into(holder.image)
        } else if (holder is RequestHolder) {
            val item = data[position] as Request

            holder.title.text = item.name
            holder.description.text = item.description
        }
    }

    override fun getItemCount() = data.size

    override fun getItemViewType(position: Int) = type

    private fun getLayout() = if (type == TYPE_ITEM) {
        R.layout.item_shop
    } else R.layout.item_request


    fun replaceData(items: List<ShopModel>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    abstract class ShopViewHolder(view: View, val onItemClick: (Int) -> Unit) :
            RecyclerView.ViewHolder(view), View.OnClickListener {
        init { view.setOnClickListener(this) }

        override fun onClick(view: View) { }
    }

    class ItemHolder(view: View, onItemClick: (Int) -> Unit) : ShopViewHolder(view, onItemClick) {

        val name by lazy { view.findViewById(R.id.name) as TextView }
        val rating by lazy { view.findViewById(R.id.rating) as TextView }
        val image by lazy { view.findViewById(R.id.image) as ImageView }

        override fun onClick(view: View) {
            onItemClick(adapterPosition)
        }
    }

    class RequestHolder(view: View, onItemClick: (Int) -> Unit) : ShopViewHolder(view, onItemClick) {
        val title by lazy { view.find<TextView>(R.id.title) }
        val description by lazy { view.find<TextView>(R.id.description) }
    }

    companion object {
        const val TYPE_ITEM = 0
        const val TYPE_REQUEST = 1
    }
}