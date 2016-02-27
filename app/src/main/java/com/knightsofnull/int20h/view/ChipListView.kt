package com.knightsofnull.int20h.view

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.knightsofnull.int20h.R
import java.util.*

/**
 * Created by mrbimc on 27.02.16.
 */
class ChipListView : LinearLayout {

    private var chips = ArrayList<String>()

    private var onChipClick: (v: String) -> Unit = { }

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(attrs)
    }

    private fun addRow() {
        val row = LinearLayout(context)
        row.orientation = LinearLayout.HORIZONTAL
        row.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
        addView(row)
        invalidate()
    }

    private fun init(attrs: AttributeSet? = null) {
        orientation = LinearLayout.VERTICAL
//        updateChipViews()
    }

    private fun updateChipViews() {
        val inflater = LayoutInflater.from(context)
        chips.forEach { chipName ->
            val chipView = inflater.inflate(R.layout.view_chip, this, false)

            with(chipView) {
                val text = findViewById(R.id.chipText) as TextView
                text.text = chipName
                setOnClickListener { onChipClick(chipName) }
                addNewChip(chipView)
            }
        }
    }

    private fun addNewChip(chip: View) {
        if(childCount == 0) addRow()
        else if(getChildAt(childCount - 1).measuredWidth + chip.measuredWidth >= measuredWidth) addRow()

        val row: LinearLayout = getChildAt(childCount - 1) as LinearLayout
        row.addView(chip)
        invalidate()

        Log.d(javaClass.simpleName, "childViewWidth = ${chip.measuredWidth} + row width = ${getChildAt(childCount - 1).measuredWidth} parent width = $measuredWidth")
    }

    fun addChips(chips: List<String>) {
        this.chips.addAll(chips)
        updateChipViews()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        updateChipViews()
    }

    fun setOnChipClickListener(onChipClick: (v: String) -> Unit) {
        this.onChipClick = onChipClick
    }
}