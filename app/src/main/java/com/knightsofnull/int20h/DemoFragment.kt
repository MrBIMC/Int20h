package com.knightsofnull.int20h

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.view_bottom_sheet.*

/**
 * Created by mrbimc on 27.02.16.
 */
class DemoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.view_bottom_sheet, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = listOf("Computers", "Cloth", "Phones", "Stuff", "Another stuff", "Different kind of stuff")
        chipList.addChips(list)
        chipList.setOnChipClickListener { Toast.makeText(activity, "$it clicked!", Toast.LENGTH_SHORT).show() }
    }
}