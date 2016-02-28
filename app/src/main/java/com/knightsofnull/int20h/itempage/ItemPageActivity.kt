package com.knightsofnull.int20h.itempage

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.knightsofnull.int20h.R
import com.knightsofnull.int20h.model.Item
import com.knightsofnull.int20h.util.colorStatusBar
import com.knightsofnull.int20h.util.darkenColor
import com.knightsofnull.int20h.util.logD
import kotlinx.android.synthetic.main.activity_item.*

/**
 * Created by mrbimc on 27.02.16.
 */
class ItemPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val item = intent.extras.getSerializable(KEY_ITEM) as Item
        val color = intent.extras.getInt(KEY_COLOR, R.color.primary)

        collapsingToolbar.setContentScrimColor(color)

        supportActionBar?.title = item.name
        price.text = item.price
        rating.text = item.itemRating.toString()

        colorStatusBar(this, darkenColor(color))

        Glide.with(this).load(item.itemImage).into(image)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return false
    }

    companion object {
        const val KEY_ITEM = "item"
        const val KEY_COLOR = "color"
    }
}