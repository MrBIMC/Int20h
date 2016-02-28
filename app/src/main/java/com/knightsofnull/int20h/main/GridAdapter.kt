package com.knightsofnull.int20h.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.knightsofnull.int20h.R
import com.knightsofnull.int20h.util.logD
import org.jetbrains.anko.find
import java.util.*

/**
 * Created by yarolegovich on 28.02.2016.
 */


class GridAdapter(context: Context, data: ArrayList<String>) : ArrayAdapter<String>(context, 0, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var v = convertView
        val vh: ViewHolder
        if (v == null) {
            v = LayoutInflater.from(context).inflate(R.layout.view_chip, parent, false)
            vh = ViewHolder(v)
            v.tag = vh
        } else vh = v.tag as ViewHolder

        vh.text.text = getItem(position)

        return v
    }

    fun replaceData(newData: List<String>) {
        clear()
        addAll(newData)
    }

    class ViewHolder(view: View) {
        val text by lazy { view.find<TextView>(R.id.chipText) }
    }

}