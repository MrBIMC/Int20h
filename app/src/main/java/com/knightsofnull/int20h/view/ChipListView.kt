package com.knightsofnull.int20h.view

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.knightsofnull.int20h.R
import com.knightsofnull.int20h.util.logD
import java.util.*

/**
 * Created by mrbimc on 27.02.16.
 */
class ChipListView : LinearLayout {

    private var chips = ArrayList<String>()
    private var onChipClick: (v: Int) -> Unit = { }

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        orientation = LinearLayout.VERTICAL
    }

    private fun addChipViews() {
        val inflater = LayoutInflater.from(context)
        chips.forEach { chipName ->
            val chipView = inflater.inflate(R.layout.view_chip, this, false)

            with(chipView) {
                val text = findViewById(R.id.chipText) as TextView
                text.text = chipName
                setOnClickListener { onChipClick(chips.indexOf(chipName)) }
                addNewChip(chipView)
            }
        }
    }

    private fun addNewChip(chip: View) {
        val empty = childCount == 0
        val lastChild = getChildAt(childCount - 1) as ViewGroup?
        val lastRowIsFull = lastChild?.let { it.childCount == 3 } ?: false

        if(empty || lastRowIsFull) {
            addRow()
        }

        val row: LinearLayout = getChildAt(childCount - 1) as LinearLayout
        row.addView(chip)
    }

    fun setChips(chips: List<String>) {
        this.chips.clear()
        this.chips.addAll(chips)
        removeAllViews()
        addChipViews()
    }

    private fun addRow() {
        val row = LinearLayout(context)
        row.orientation = LinearLayout.HORIZONTAL
        row.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
        addView(row)
    }


    fun setOnChipClickListener(onChipClick: (v: Int) -> Unit) {
        this.onChipClick = onChipClick
    }
}