package com.knightsofnull.int20h.itempage

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.knightsofnull.int20h.R
import com.knightsofnull.int20h.model.Item
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
        logD("got item: $item")

        supportActionBar?.title = item.name
        price.append(" ${item.price}")
        rating.append(" ${item.itemRating.toString()}")

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
    }
}