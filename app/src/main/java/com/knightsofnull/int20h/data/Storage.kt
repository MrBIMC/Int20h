package com.knightsofnull.int20h.data

import com.knightsofnull.int20h.model.Category
import com.knightsofnull.int20h.model.Item
import com.knightsofnull.int20h.model.Producer
import com.knightsofnull.int20h.model.Type

/**
 * Created by yarolegovich on 27.02.2016.
 */
interface Storage {

    fun getTypes(): List<Type>

    fun getCategories(): List<Category>

    fun getItems(): List<Item>

    fun getProducers(): List<Producer>

    fun putItem(item: Item)
}