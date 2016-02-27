package com.knightsofnull.int20h.model

import java.io.Serializable

/**
 * Created by yarolegovich on 27.02.2016.
 */
data class Item(
        val id: Int,
        val name: String,
        val price: String,
        val catId: Int,
        val producerId: Int,
        val itemRating: Int
) : ShopModel, Serializable