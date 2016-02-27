package com.knightsofnull.int20h.shop

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.knightsofnull.int20h.R
import com.knightsofnull.int20h.model.Item

/**
 * Created by yarolegovich on 27.02.2016.
 */
class ShopAdapter(val data: List<Item>, val onItemClick: (Int) -> Unit) : RecyclerView.Adapter<ShopAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shop, parent, false)
        return ItemViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = data[position]

        holder.name.text = item.name
        holder.rating.text = "Rating: ${item.itemRating}"
    }

    override fun getItemCount() = data.size

    class ItemViewHolder(view: View, val onItemClick: (Int) -> Unit) : RecyclerView.ViewHolder(view), View.OnClickListener {

        init { view.setOnClickListener(this) }

        val name by lazy { view.findViewById(R.id.name) as TextView }
        val rating by lazy { view.findViewById(R.id.rating) as TextView }
        val image by lazy { view.findViewById(R.id.image) as ImageView }

        override fun onClick(p0: View?) {
            onItemClick(0)
        }
    }
}