package com.knightsofnull.int20h.data

import com.knightsofnull.int20h.model.Category
import com.knightsofnull.int20h.model.Item
import com.knightsofnull.int20h.model.Producer
import com.knightsofnull.int20h.model.Type

/**
 * Created by yarolegovich on 27.02.2016.
 */
interface DataProvider {
    fun getTypes(): List<Type>
    fun getCategories(typeId: Int): List<Category>
    fun getItems(categoryId: Int): List<Item>
    fun getProducer(producerId: Int): Producer

    fun requestItem(item: Item)
}